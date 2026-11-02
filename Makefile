.PHONY: build build-stacktrace build-warning clean \
	dependencies dependency-updates jacoco-coverage jacoco-report jar javadoc \
	license-format publish-local spotless-apply \
	spotless-check spotless-java spotless-misc tag-release test \
	version-catalog-format version-catalog-update

# the version a release tag would carry
VERSION := $(shell sed -n 's/^projectVersion=//p' gradle.properties)

build:
	./gradlew build

build-stacktrace:
	./gradlew build --stacktrace --warning-mode all

build-warning:
	./gradlew build --warning-mode all

clean:
	./gradlew clean

test:
	./gradlew test

# --- mirrors Gradle "Run Configurations" panel ---

dependencies:
	./gradlew dependencies

dependency-updates:
	./gradlew dependencyUpdates

jacoco-coverage:
	./gradlew jacocoTestCoverageVerification

jacoco-report:
	./gradlew jacocoTestReport

jar:
	./gradlew jar

javadoc:
	./gradlew javadoc

# license headers are managed by the spotless licenseHeaderFile step
license-format:
	./gradlew spotlessApply

publish-local:
	./gradlew publishMavenJavaPublicationToMavenLocal

spotless-apply:
	./gradlew spotlessApply

spotless-check:
	./gradlew spotlessCheck

spotless-java:
	./gradlew spotlessJavaApply

spotless-misc:
	./gradlew spotlessMiscApply

# Tagging is publishing: .github/workflows/publish.yml triggers on 'RELEASE-*' tag pushes and
# uploads to Maven Central. Decide whether to upload BEFORE the tag, not after it. An upload
# leaves only from CI, so there is no target that uploads from here (#17).
tag-release:
	@case "$(VERSION)" in *-SNAPSHOT) \
		echo "tag-release refuses $(VERSION): a release tag carries a release version"; \
		exit 1;; esac
	@test "$(CONFIRM)" = "yes" || { \
		echo "tag-release creates a RELEASE tag, and pushing it triggers publish.yml, which uploads"; \
		echo "to Maven Central. Re-run with CONFIRM=yes if that is intended."; \
		exit 1; }
	./gradlew tagRelease

version-catalog-format:
	./gradlew versionCatalogFormat

version-catalog-update:
	./gradlew versionCatalogUpdate
