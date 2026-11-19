## Change log
----------------------

Version 4.0
-------------

4.0 is a major release: swing-table-components needs Java 25, it removes the 15 classes no project of the family used
(#33), and its table models of pairs, triples and quadruples take `Map.Entry` and records of their own instead of the
types of silly-bean, which is archived. 4.0 no longer brings silly-bean along, neither directly (#28) nor through
silly-collection (#38): its POM and its Gradle module metadata name no silly-bean, and no class of it references a
silly-bean type. It is published through the Central Portal, loads on the module path, and a build of the same commit
on the JDK `.sdkmanrc` names gives the same bytes, in any directory, locale and time zone. Measured by the release gate
(Temurin 25.0.4+7, the JDK `.sdkmanrc` names, on the tree of this release): 32 tests in 9 test classes, none skipped;
178 of 483 lines and 46 of 135 branches covered (JaCoCo; the repository has no coverage floor and no mutation testing
yet).

BREAKING CHANGES:

- Java 25 is the minimum, up from 17: the family builds, tests and releases on JDK 25
  (astrapi69/mystic-crypt#234), so this release is 4.0 instead of 3.3 (#13). The class files are of version 69, the
  module descriptor requires `java.base` 25 and the Gradle module metadata a JVM of 25. **Note:** a project on an older
  Java stays on 3.2
- removed, as no repository of the family used them (decided by the maintainer on 2026-11-07, #33); a caller of one of
  them stays on 3.2. With them go the exports of the packages `model.suffle`, `model.suffle.action`, `panel`,
  `panel.shuffle` and `shuffle`, and the defect of `PropertiesTableModel` (#27):
  - `io.github.astrapi69.swing.table.model.NoRowsSelectedException`
  - `io.github.astrapi69.swing.table.model.properties.PropertiesColumns`
  - `io.github.astrapi69.swing.table.model.properties.PropertiesTableModel`
  - `io.github.astrapi69.swing.table.model.suffle.GenericShuffleTableModel`
  - `io.github.astrapi69.swing.table.model.suffle.GenericShuffleTableModelExtensions`
  - `io.github.astrapi69.swing.table.model.suffle.action.AddAction`
  - `io.github.astrapi69.swing.table.model.suffle.action.AddAllAction`
  - `io.github.astrapi69.swing.table.model.suffle.action.RemoveAction`
  - `io.github.astrapi69.swing.table.model.suffle.action.RemoveAllAction`
  - `io.github.astrapi69.swing.table.model.triple.TripleStringTableModel`
  - `io.github.astrapi69.swing.table.panel.TablePanel`
  - `io.github.astrapi69.swing.table.panel.shuffle.AbstractShuffleTablePanel`
  - `io.github.astrapi69.swing.table.panel.shuffle.ShuffleTablePanel`
  - `io.github.astrapi69.swing.table.renderer.DateRenderer`
  - `io.github.astrapi69.swing.table.shuffle.GenericShuffleJTable`
- the table models no longer use the types of silly-bean, and swing-table-components no longer depends on it (#28):
  - `io.github.astrapi69.swing.table.model.properties.KeyValueTableModel<K, V>` and `StringKeyValueTableModel` list
    rows of `java.util.Map.Entry<K, V>` instead of `io.github.astrapi69.collection.pair.KeyValuePair<K, V>`
  - `io.github.astrapi69.swing.table.model.triple.TripleTableModel<L, M, R>` lists rows of the new record
    `io.github.astrapi69.swing.table.model.triple.Triple(left, middle, right)` instead of
    `io.github.astrapi69.collection.pair.Triple`
  - `io.github.astrapi69.swing.table.model.quattro.QuattroTableModel<TL, TR, BL, BR>` lists rows of the new record
    `io.github.astrapi69.swing.table.model.quattro.Quattro(topLeft, topRight, bottomLeft, bottomRight)` instead of
    `io.github.astrapi69.collection.pair.Quattro`

  **Note:** a caller builds its rows with `Map.entry` (or `AbstractMap.SimpleEntry` for null keys or values) and the
  new records, whose accessors are `left()`, `topLeft()` and so on instead of `getLeft()`, `getTopLeft()`

CHANGED:

- the runtime dependency silly-collection is 30.0 instead of 28.1 (#38). 30.0 needs Java 25 and no longer depends on
  silly-bean; it replaces the `KeyValuePair` parameters of ten `MapFactory` methods by `Map.Entry`, which no code here
  calls (its CHANGELOG: BREAKING of 29.0, BREAKING CHANGES and REMOVED of 30.0). swing-table-components calls
  `ListFactory.newArrayList`, whose signatures 30.0 keeps. **Note:** a project that got silly-bean through
  swing-table-components and uses one of its types, such as `KeyValuePair`, declares silly-bean itself; a project that
  uses silly-collection through swing-table-components reads the breaking changes of silly-collection 29.0 (the list
  classes `SortedList`, `SortedUniqueList` and `UniqueList`) and 30.0 (`MapFactory`)
- the runtime dependency jobj-core is 10.0 instead of 9.1 (#37, merged on the maintainer's decision of 2026-11-18).
  jobj-core 10.0 needs Java 25, as this library does, removes twelve types and changes what seven methods answer, in
  five entries (its CHANGELOG, BREAKING CHANGES); swing-table-components uses `ThreadDataBean` and `ThreadExtensions`,
  both kept with the same public signatures, and links against none of the removed types or changed methods
  (`jar-ref-scan.py`, 0 hits). `ThreadDataBean.hashCode` values may differ in 10.0, which no code here relies on (no
  hash-based collection holds a `ThreadDataBean`); and `ThreadsTableModel`, which calls
  `ThreadExtensions.newThreadData()` once a second, gets the fix of 10.0 that `newThreadData()` no longer throws when
  a thread ends while it collects. **Note:** a project that gets jobj-core through swing-table-components and uses one
  of the removed types or changed methods itself reads the BREAKING CHANGES of jobj-core 10.0
- dependencies, against 3.2: commons-lang3 3.20.0 -> 3.21.0, jobj-core 9.1 -> 10.0, jobj-reflect 2.4 -> 3 (#14),
  menu-action 4.1 -> 5.1, silly-collection 28.1 -> 30.0, swing-base-components 5.1 -> 5.2.1 (#14), model-data stays
  at 3.2.1, and silly-bean 3.0 is gone (#28); lombok, for compiling only, 1.18.46 -> 1.18.48. For the tests
  junit-jupiter 5.11.0-M2 -> 6.1.3 and test-object 9 -> 9.1; awt-extensions 2.0, throwable 3 and meanbean 2.0.3 stay;
  silly-io and file-worker are gone, as no test used them (#22). For the build Gradle 9.7.0 -> 9.8.2, its wrapper jar
  checked against the published `gradle-9.8.2-wrapper.jar.sha256`, and `.gitattributes` keeps `gradlew` with LF,
  `*.bat` with CRLF and the jar binary (#13); JaCoCo 0.8.15 -> 0.8.16 (#40); the plugins spotless 8.10.0 -> 8.11.0
  (#40), lombok 9.5.0 -> 9.8.3, versions 0.61.0 -> 0.67.0 under its new id `io.github.ben-manes.versions`,
  version-catalog-update 1.1.1 -> 1.1.2; nmcp 1.6.2 (#17) and test-results-complete 1.0 (#23) are new. **Note:**
  menu-action 5.1 no longer brings data-api, gen-tree, id-generate, tree-api and visitor-pattern, which came through
  3.2 (menu-action's CHANGELOG, 5.0); a project that uses one of them declares it itself. jobj-reflect 3 names its
  module `io.github.astrapisixtynine.jobj.reflect` instead of `jobj.reflect.main`, and commons-collections4 moves from
  4.5.0-M2 to 4.6.0, which silly-collection 30.0 asks for
- `versionCatalogUpdate` chooses versions through a selector of its own: a stable version moves only to a stable one,
  and nothing to a snapshot (#13)
- releases go to Maven Central through the Central Portal, as in the rest of the family: the `com.gradleup.nmcp`
  plugin uploads, the maintainer releases each deployment in the Portal, and a `RELEASE-*` tag runs `publish.yml`,
  which signs with the signing subkey that the secret `GPG_KEY_ID` names. An upload leaves only from CI, a release
  only from its tag; a half-configured key and a release without a key are refused. The OSSRH staging API settings,
  the switch that held the checksums to md5 and sha1, and the Makefile targets `central-upload`, `central-list`,
  `central-upload-repository`, `central-drop`, `publish` and `release` are gone; `make tag-release` refuses a
  SNAPSHOT version and asks for `CONFIRM=yes` (#17)
- a build of the same commit on the JDK `.sdkmanrc` names gives the same bytes, wherever it runs (#42): the manifest
  no longer carries the build time, the JDK patch level or the operating system and names the feature release as
  `Build-Jdk-Spec: 25` instead, javac compiles with `--release 25`, so that the module descriptor requires `java.base`
  25 instead of the 25.0.4 of the compiling JDK, and every archive has fixed entry times and order; the workflows
  build on Temurin 25.0.4+7, the JDK `.sdkmanrc` names, and check that they do. `scripts/check-reproducible.sh`
  builds the five published artifacts twice, the second time from `git archive` in another directory, language and
  time zone, and compares them.
  **Note:** the manifest attributes `Build-Timestamp`, `Build-Jdk` and `Build-OS` are gone, `Build-Jdk-Spec` is new
- the javadoc jar is the same in every locale, in English, where a build on a machine in German wrote German pages,
  and a javadoc warning fails the build (#39). Every public and protected member has its Javadoc: `TableColumnsModel`
  and the `equals`, `hashCode` and `canEqual` of `DynamicTableColumnsModel` are written out from what Lombok
  generated, the constructor without arguments of `BaseTableModel` and the default constructor of
  `TableCellButtonRendererFactory` as well; their signatures, the `toString` texts and the `hashCode` values stay
  the same
- the build reads the local Maven repository only with `-PuseMavenLocal`: `mavenLocal()` came before Maven Central,
  so an artifact in `~/.m2` replaced the published one of the same version, and a local build tested against other
  bytes than CI and every user (#25)
- Spotless enforces the MIT license header again (`licenseHeaderFile` with `src/main/resources/license-header.txt`),
  which the release 3.2 migration had dropped with the license plugin; `module-info.java` and the model's
  `package-info.java` carry the header now too. Spotless leaves those two files out of its header check by design
  (#20)
- the README names how a release is made, in seven steps, `main` following the tag and the bump drafts in the
  consumers included (#41)

FIXED:

- the module resolves on the module path (#14): it required `lombok`, which is only there at compile time, and
  requires it `static` now; swing-base-components 5.2.1 does the same, and jobj-reflect 3 requires the module name
  of jobj-core 9 and later, `io.github.astrapisixtynine.jobj.core`, where 2.4 required `jobj.core.main`
- the default `QuattroTableModel` has an editable flag for each of its four columns: the fourth, "Bottom Right", could
  not be edited, and every check whether it could logged an `ArrayIndexOutOfBoundsException` as SEVERE (#34)

Version 3.2
-------------

ADDED:

- new delegate method deselectAll in class GenericJTable
- gradle/libs.versions.toml, the version catalog, with versionCatalogUpdate to raise its versions

CHANGED:

- built with Gradle 9.7.0, up from 8.4-rc-1; still Java 17
- published to Maven Central through the Central Portal's OSSRH Staging API
  (ossrh-staging-api.central.sonatype.com, then make central-upload into the Portal) instead of the
  legacy OSSRH at oss.sonatype.org; a Makefile and .github/workflows/publish.yml are new
- module-info.java requires the new module names of jobj-core, silly-collection, menu-action,
  silly-bean and swing-base-components (io.github.astrapisixtynine.*)
- dependencies raised: commons-lang3 3.13.0 -> 3.20.0, swing-base-components 4.1 -> 5.1, model-data
  3.1 -> 3.2.1, menu-action 3.3 -> 4.1, silly-collection 27 -> 28.1, silly-bean 2 -> 3.0, jobj-core
  8 -> 9.1, jobj-reflect 2 -> 2.4, lombok 1.18.30 -> 1.18.46; for the tests awt-extensions 1.1 -> 2.0,
  file-worker 17 -> 19.0, silly-io 2.2 -> 3.6, test-object 7.2 -> 9, throwable 2.3 -> 3, junit-jupiter
  5.10.0 -> 5.11.0-M2; the plugins spotless 6.21.0 -> 8.10.0, lombok 8.3 -> 9.5.0, versions 0.48.0 ->
  0.61.0, and version-catalog-update 1.1.1 is new
- the license and grgit plugins are gone; the license header was no longer checked (fixed in 4.0, #20)
- replaced the obsolete package.html of the model package with a package-info.java

**Note:** 3.2 does not load on the module path: jobj-reflect 2.4 requires the module jobj.core.main, which
jobj-core 9 renamed, and module-info.java requires lombok without static, and so do swing-base-components
5.1 and jobj-reflect 2.4 (fixed in 4.0, #14).

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
