#!/usr/bin/env bash
#
# Builds the five artifacts this project publishes twice from the checked-out commit - each time from a clean
# build directory into a fresh local Maven repository - and compares them by SHA-256 (the script of
# astrapi69/silly-collection#99, astrapi69/file-worker#97 and astrapi69/jobj-core). A release can be rebuilt bit for
# bit from its tag only if two builds of the same commit give the same bytes, wherever they run.
#
# The second build therefore runs in a deliberately different environment: another language and region, another
# time zone and another directory (RL-094). Two builds in the same environment miss what the environment writes into
# the bytes; the javadoc jar of mystic-crypt 13.10 was the same in two builds on one machine and still differed from
# the one CI built, in the language of the machine (astrapi69/mystic-crypt#304).
#
#   build 1   this checkout, LANG and LC_ALL de_DE.UTF-8, TZ Europe/Berlin
#   build 2   the same commit from git archive in a temporary directory, LANG and LC_ALL en_US.UTF-8,
#             TZ Pacific/Auckland
#
# Usage:
#   scripts/check-reproducible.sh                     both builds on the toolchain Gradle finds
#   scripts/check-reproducible.sh <jdk>               both builds on that JDK only
#   scripts/check-reproducible.sh <jdk> <other jdk>   the second build on the other JDK, to show that the JDK's
#                                                     patch level does not reach the artifacts
#
# Exit codes: 0 all five identical; 1 an artifact differs (the archive entries that differ are listed);
# 2 a build failed, the checkout has uncommitted changes, a locale is missing or the arguments are wrong.
set -uo pipefail

[[ $# -le 2 ]] || { echo "usage: scripts/check-reproducible.sh [<jdk home> [<jdk home of the second build>]]" >&2; exit 2; }
cd "$(dirname "$0")/.."
# Build 2 builds the commit, build 1 the checkout: a change that is not committed would make them differ
git diff --quiet HEAD -- || { echo "the checkout has uncommitted changes; commit them, the script proves a commit" >&2; exit 2; }
# A locale the machine does not have falls back to C without a word, and the two builds would not differ in it
for wanted in de_DE.UTF-8 en_US.UTF-8; do
	normal=$(printf '%s' "$wanted" | tr 'A-Z' 'a-z' | tr -d '-')
	locale -a 2>/dev/null | tr 'A-Z' 'a-z' | tr -d '-' | grep -qx "$normal" \
		|| { echo "the locale $wanted is not generated on this machine" >&2; exit 2; }
done
work="$(mktemp -d "${TMPDIR:-/tmp}/check-reproducible.XXXXXX")"
trap 'rm -rf "$work"' EXIT
# The signatures are not compared, and signing needs the key: on a machine with signing.gnupg.keyName the gpg
# command could ask for its passphrase. The two builds therefore sign nothing.
printf 'allprojects { tasks.withType(Sign).configureEach { enabled = false } }\n' > "$work/no-signing.gradle"
mkdir "$work/tree2"
git archive HEAD | tar -x -C "$work/tree2" || { echo "git archive of HEAD failed" >&2; exit 2; }

build()
{
	local n="$1" dir="$2" lang="$3" tz="$4" jdk="${5:-}" args=()
	if [[ -n "$jdk" ]]; then
		[[ -x "$jdk/bin/javac" ]] || { echo "no JDK at $jdk" >&2; exit 2; }
		# Gradle properties, not project properties: Gradle 9 reports -P for these as deprecated and Gradle 10
		# removes it (upgrading guide 9, "toolchain-project-properties")
		args=(-Dorg.gradle.java.installations.auto-detect=false -Dorg.gradle.java.installations.auto-download=false
			"-Dorg.gradle.java.installations.paths=$jdk")
	fi
	echo "build $n in $dir, $lang, $tz${jdk:+, on $jdk}"
	# Gradle prefers the JVM it runs on as the toolchain when it matches, so the build runs on that JDK as well;
	# --no-daemon, so that the JVMs of the build start in this environment instead of a daemon's
	(cd "$dir" && env ${jdk:+JAVA_HOME="$jdk"} LANG="$lang" LC_ALL="$lang" TZ="$tz" \
		./gradlew --no-daemon --no-build-cache --console=plain -q --init-script "$work/no-signing.gradle" \
		"-Dmaven.repo.local=$work/repo$n" "${args[@]}" \
		clean publishMavenJavaPublicationToMavenLocal > "$work/build$n.log" 2>&1) \
		|| { echo "build $n failed:" >&2; tail -20 "$work/build$n.log" >&2; exit 2; }
	find "$work/repo$n" -path '*/io/github/astrapi69/*' \( -name '*.jar' -o -name '*.pom' -o -name '*.module' \) \
		-exec sha256sum {} + | sed "s|$work/repo$n/||" | sort -k2 > "$work/sha$n.txt"
}

build 1 "$PWD" de_DE.UTF-8 Europe/Berlin "${1:-}"
build 2 "$work/tree2" en_US.UTF-8 Pacific/Auckland "${2:-${1:-}}"

if [[ $(wc -l < "$work/sha1.txt") -ne 5 ]]; then
	echo "expected the five published artifacts, found $(wc -l < "$work/sha1.txt"):" >&2
	sed 's/^/  /' "$work/sha1.txt" >&2
	exit 2
fi
if diff -q "$work/sha1.txt" "$work/sha2.txt" > /dev/null; then
	echo "identical, $(wc -l < "$work/sha1.txt") artifacts:"
	sed 's/^/  /' "$work/sha1.txt"
	exit 0
fi
echo "different:"
join -j 2 "$work/sha1.txt" "$work/sha2.txt" | awk '$2 != $3 {print "  " $1}' | while read -r file; do
	echo "  $file"
	case "$file" in
	*.jar)
		python3 -I - "$work/repo1/$file" "$work/repo2/$file" <<'PY'
import sys, zipfile
a, b = zipfile.ZipFile(sys.argv[1]), zipfile.ZipFile(sys.argv[2])
names = [i.filename for i in a.infolist()]
if names != [i.filename for i in b.infolist()]:
    print('    the entries or their order differ')
differing = [name for name in names if name in b.namelist() and a.read(name) != b.read(name)]
for name in differing[:10]:
    print('    entry ' + name)
if len(differing) > 10:
    print('    ... and %d more entries' % (len(differing) - 10))
PY
		;;
	esac
done
exit 1
