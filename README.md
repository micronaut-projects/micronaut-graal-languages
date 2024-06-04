<!-- Checklist: https://github.com/micronaut-projects/micronaut-core/wiki/New-Module-Checklist -->

# Micronaut graal-languages

[![Maven Central](https://img.shields.io/maven-central/v/io.micronaut.graal-languages/micronaut-project-template.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.micronaut.project-template%22%20AND%20a:%22micronaut-project-template%22)
[![Build Status](https://github.com/micronaut-projects/micronaut-graal-languages/workflows/Java%20CI/badge.svg)](https://github.com/micronaut-projects/micronaut-project-template/actions)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=micronaut-projects_micronaut-template&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=micronaut-projects_micronaut-template)
[![Revved up by Develocity](https://img.shields.io/badge/Revved%20up%20by-Develocity-06A0CE?logo=Gradle&labelColor=02303A)](https://ge.micronaut.io/scans)

Micronaut graal-languages

## Documentation

See the [Documentation](https://micronaut-projects.github.io/micronaut-graal-languages/latest/guide/) for more information.

See the [Snapshot Documentation](https://micronaut-projects.github.io/micronaut-graal-languages/snapshot/guide/) for the current development docs.

<!-- ## Examples

Examples can be found in the [examples](https://github.com/micronaut-projects/micronaut-graal-languages/tree/master/examples) directory. -->

## Snapshots and Releases

Snapshots are automatically published to [Sonatype Snapshots](https://s01.oss.sonatype.org/content/repositories/snapshots/io/micronaut/) using [GitHub Actions](https://github.com/micronaut-projects/micronaut-graal-languages/actions).

See the documentation in the [Micronaut Docs](https://docs.micronaut.io/latest/guide/index.html#usingsnapshots) for how to configure your build to use snapshots.

Releases are published to Maven Central via [GitHub Actions](https://github.com/micronaut-projects/micronaut-graal-languages/actions).

Releases are completely automated. To perform a release use the following steps:

* [Publish the draft release](https://github.com/micronaut-projects/micronaut-graal-languages/releases). There should be already a draft release created, edit and publish it. The Git Tag should start with `v`. For example `v1.0.0`.
* [Monitor the Workflow](https://github.com/micronaut-projects/micronaut-graal-languages/actions?query=workflow%3ARelease) to check it passed successfully.
* If everything went fine, [publish to Maven Central](https://github.com/micronaut-projects/micronaut-graal-languages/actions?query=workflow%3A"Maven+Central+Sync").
* Celebrate!
