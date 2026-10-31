# Overview

<div style="text-align: center">

[![Java CI with Gradle](https://github.com/astrapi69/swing-table-components/actions/workflows/gradle.yml/badge.svg)](https://github.com/astrapi69/swing-table-components/actions/workflows/gradle.yml)
[![Open Issues](https://img.shields.io/github/issues/astrapi69/swing-table-components.svg?style=flat)](https://github.com/astrapi69/swing-table-components/issues)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.astrapi69/swing-table-components/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.astrapi69/swing-table-components)
[![Javadocs](http://www.javadoc.io/badge/io.github.astrapi69/swing-table-components.svg)](http://www.javadoc.io/doc/io.github.astrapi69/swing-table-components)
[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)
[![Donate](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)
[![Hits Of Code](https://hitsofcode.com/github/astrapi69/swing-table-components?branch=main)](https://hitsofcode.com/github/astrapi69/swing-table-components/view?branch=main)
[![Lines Of Code](https://tokei.rs/b1/github/astrapi69/swing-table-components)](https://github.com/astrapi69/swing-table-components)

</div>

This repository provides table components like JXTable and ShuffleTable for swing applications.

> Please support this project by simply putting a Github <!-- Place this tag where you want the button to render. -->
<a class="github-button" href="https://github.com/astrapi69/swing-table-components" data-icon="octicon-star" aria-label="Star astrapi69/swing-table-components on GitHub">Star ⭐</a>
>
> Share this library with friends on Twitter and everywhere else you can
>
> If you love this project [![donation](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)

## Note

No animals were harmed in the making of this library.

## License

The source code comes under the liberal MIT License, making swing-table-components great for all types of applications.

## gradle dependency

You can first define the version in the ext section and add than the following gradle dependency to
your project `build.gradle` if you want to import the core functionality of swing-table-components:

define version in file gradle.properties
```
swingTableComponentsVersion=3.1
```

or in build.gradle ext area

```
    swingTableComponentsVersion = "3.1"
```

then add the dependency to the dependencies area

```
    implementation("io.github.astrapi69:swing-table-components:$swingTableComponentsVersion")
```

## Maven dependency

Maven dependency is now on sonatype.
Check out [sonatype repository](https://oss.sonatype.org/index.html#nexus-search;gav~io.github.astrapi69~swing-table-components~~~) for latest snapshots and releases.

Add the following maven dependency to your project `pom.xml` if you want to import the core
functionality of swing-table-components:

Than you can add the dependency to your dependencies:

    <properties>
            ...
        <!-- swing-table-components version -->
        <swing-table-components.version>3.1</swing-table-components.version>
            ...
    </properties>
            ...
        <dependencies>
            ...
            <!-- swing-table-components DEPENDENCY -->
            <dependency>
                <groupId>io.github.astrapi69</groupId>
                <artifactId>swing-table-components</artifactId>
                <version>${swing-table-components.version}</version>
            </dependency>
            ...
        </dependencies>

## Support this project

If swing-table-components is useful to you, a star on [GitHub](https://github.com/astrapi69/swing-table-components) helps others find it.
Sharing it or reporting a bug helps just as much.

If you would like to contribute financially, the channels are on the repository's Sponsor button, which
reads [.github/FUNDING.yml](.github/FUNDING.yml): GitHub Sponsors, Liberapay, Ko-fi and PayPal.

## Semantic Versioning

The versions of swing-table-components are maintained with the Semantic Versioning guidelines.

Release version numbers will be incremented in the following format:

`<major>.<minor>.<patch>`

For detailed information on versioning you can visit the [wiki page](https://github.com/lightblueseas/mvn-parent-projects/wiki/Semantic-Versioning).

## Want to Help and improve it? ###

The source code for swing-table-components are on GitHub. Please feel free to fork and send pull requests!

Create your own fork of [astrapi69/swing-table-components/fork](https://github.com/astrapi69/swing-table-components/fork)

To share your changes, [submit a pull request](https://github.com/astrapi69/swing-table-components/pull/new/develop).

Don't forget to add new units tests on your changes.

## Contacting the Developers

Do not hesitate to contact the swing-table-components developers with your questions, concerns, comments, bug reports, or feature requests.
- Feature requests, questions and bug reports can be reported at the [issues page](https://github.com/astrapi69/swing-table-components/issues).

## Similar projects

## Credits

## Git hooks

The repository's git hooks live in `.githooks`: `commit-msg` where present, and `pre-commit`, which refuses a
commit whose files Spotless would format. Activate them once per clone or worktree:

```bash
git config core.hooksPath .githooks
```
