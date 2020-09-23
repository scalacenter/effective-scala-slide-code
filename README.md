# _Effective Scala_ slide deck code samples

This repo containis the code in the _Effective Scala_ course
as fully functional examples and mostly in worksheet format.

# Maintaining Slide Code samples

This repository contains working code samples for the
Effective Scala MOOC.

The code samples fall in two categories:

- Self-contained Scala 3 (Dotty) programs with, in some
  case tests
- Scala Worksheets that can be used interactively

# Repository structure

The repository contains one folder per week named `week_<d>`,
with `<d>` being a single digit week number.

Each of these folders holds an sbt build common to all sections
in the course for the corresponding week.

# Current known issues

## Week 4

At the moment, loading of the `MyScalaJSPlugin` custom sbt plugin
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
