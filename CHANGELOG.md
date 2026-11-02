## Change log
----------------------

Version 4.0-SNAPSHOT
-------------

BREAKING:

- Java 25 is the minimum, up from 17: the family builds, tests and releases on JDK 25
  (astrapi69/mystic-crypt#234), so the next release is 4.0 instead of 3.3 (#13)

CHANGED:

- built with Gradle 9.8.2, its wrapper jar checked against the published
  `gradle-9.8.2-wrapper.jar.sha256`; `.gitattributes` keeps `gradlew` with LF and `*.bat` with CRLF
  line endings, and the jar binary (#13)
- `versionCatalogUpdate` chooses versions through a selector of its own: a stable version moves only to
  a stable one, and nothing to a snapshot; meanbean stays at 2.0.3, as its 3.0.0-M9 is a milestone (#13)
- dependencies raised by `versionCatalogUpdate`: commons-lang3 3.20.0 -> 3.21.0, lombok 1.18.46 ->
  1.18.48, menu-action 4.1 -> 5.1, silly-collection 28.1 -> 28.2, swing-base-components 5.1 -> 5.2,
  and for the tests file-worker 19.0 -> 20.0, junit-jupiter 5.11.0-M2 -> 6.1.3, silly-io 3.6 -> 4.0,
  test-object 9 -> 9.1; the plugins spotless 8.10.0 -> 8.10.4, lombok 9.5.0 -> 9.8.3, versions 0.61.0
  -> 0.67.0 under its new id `io.github.ben-manes.versions`, and version-catalog-update 1.1.1 -> 1.1.2.
  jobj-reflect stays at 2.4 until its release 3 (#14) (#13)
- releases go to Maven Central through the Central Portal, as in the rest of the family: the
  `com.gradleup.nmcp` plugin uploads, the maintainer releases each deployment in the Portal, and a
  `RELEASE-*` tag runs `publish.yml`, which signs with the signing subkey that the secret
  `GPG_KEY_ID` names. An upload leaves only from CI, a release only from its tag; a half-configured
  key and a release without a key are refused. The OSSRH staging API settings, the switch that held
  the checksums to md5 and sha1, and the Makefile targets `central-upload`, `central-list`,
  `central-upload-repository`, `central-drop`, `publish` and `release` are gone; `make tag-release`
  refuses a SNAPSHOT version and asks for `CONFIRM=yes` (#17)

Version 3.2-SNAPSHOT
-------------

ADDED:

- new libs.versions.toml file for new automatic catalog versions update
- new delegate method deselectAll in class GenericJTable

CHANGED:

- update gradle to new version 8.8
- update of gradle-plugin dependency with id 'com.diffplug.spotless' to new beta version 7.0.0.BETA1
- replaced obsolete package.html with package-info.java files

Version 3.1
-------------

ADDED:

- missing base package export in module-info.java file

CHANGED:

- update of dependency jobj-core to new major version 8
- update of dependency jobj-reflect to new major version 2

Version 3
-------------

CHANGED:

- update of JDK to newer version 17
- update of gradle version to 8.3-rc-1
- update of gradle plugin dependency io.freefair.gradle:lombok-plugin to new version 8.1.0
- update of gradle plugin dependency com.diffplug.spotless:spotless-plugin-gradle to new version 6.20.0
- update of gradle-plugin dependency com.github.ben-manes.versions.gradle.plugin to new version 0.47.0
- update of dependency silly-collection to new minor version 21
- update of dependency jobj-core version to 7.1

Version 2
-------------

CHANGED:

- update of JDK to newer version 11
- update of gradle version to 7.5.1
- update of gradle plugin dependency io.freefair.gradle:lombok-plugin to new version 6.5.1
- update of gradle plugin dependency com.diffplug.spotless:spotless-plugin-gradle to new version 6.11.0
- update of gradle-plugin dependency com.github.ben-manes.versions.gradle.plugin to new version 0.43.0
- update of dependency silly-collection to new minor version 20.1
- update of dependency jobj-core version to 7
- update of dependency model-data to new minor module version 2.1
- update of test dependency file-worker module version to 11.4
- removed unused test dependency commons-text
- update of test dependency junit-jupiter-api to new patch version 5.9.1

Version 1.1
-------------

ADDED:

- new TablePanel with a single table in a scroll pane
- new methods for check if the table have single, multiply or no selection
- all last relevant classes from swing-components repository
- new method replaceWith that replaces the content with the given list of the GenericTableModel
- new gradle plugin dependency com.diffplug.spotless:spotless-plugin-gradle in version 6.2.1

CHANGED:

- update of gradle version to 7.3.3
- update of gradle-plugin dependency com.github.ben-manes.versions.gradle.plugin to new version 0.42.0
- update of dependency model-data version to 1.12
- update of dependency menu-actions version to 1.4
- update of dependency swing-base-components to new version 1.2
- update of dependency jobj-core to new version 5.3
- moved all suffle panels to new package 'io.github.astrapi69.swing.table.panel.shuffle'
- update of test dependency test-objects to new version 5.7
- update of test dependency silly-io to new version 1.7

Version 1
-------------

ADDED:

- new CHANGELOG.md file created
- all relevant classes from swing-components repository

Notable links:
[keep a changelog](http://keepachangelog.com/en/1.0.0/) Don’t let your friends dump git logs into changelogs
