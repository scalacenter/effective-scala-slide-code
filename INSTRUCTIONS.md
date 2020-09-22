# Maintaining Slide Code samples

This repository contains working code samples for the
Effective Scala MOOC.

The code samples fall in two categories:

- Self-contained Scala 3 (Dotty) programs with, in some
  case tests
- Scala Worksheets that can be used interactively

# Repository structure

The repository is organised as a multi-project sbt build,
with one project per week. Each project has subfolders
that contain code samples from the different chapter sections.

Adding a chapter is as simple as creating a new folder in the
repo's root folder and naming it using a simple convention:
the name of the repo should start with `week_ddd_` where `ddd`
is a 3 digit sequence number which should be unique across al
projects. If new dependencies are introduced, they should be 
added in the `project/Dependencies.scala` file.

After adding a new chapter, the following command should be run
to update the root `build.sbt` file:

```
cmt-mainadm -b -dot <absolute path to repo root folder>
```

# Current known issues

At the moment, loading of the ``MyScalaJSPlugin` custom`` sbt plugin
is disabled. It is expected that the core issue that prevents us from
enabling the plugin will be resolved soon (in principle, it can be
used already, but it requires one to do a `publishLocal` of an
artifact from the Dotty Compiler project).

# Other

The worksheets have been tested extensively using VCS/Metals. The
worksheet functionality in Metals is still relatively new, but it's
gaining functionality and the Metals team is very active in resolving
issues as soon as they're identified.

_One important thing to note is that, in Metals, Scala worksheets have to
use the `.worksheet.sc` file extension_