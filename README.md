# _Effective Scala_ slide deck code samples

This repo contains the code in the _Effective Scala_ course
as fully functional examples and mostly in worksheet format.

# Maintaining Slide Code samples

This repository contains working code samples for the
Effective Scala MOOC.

The code samples fall in two categories:

- Self-contained Scala 3 (Dotty) programs with, in some
  case tests
- Scala Worksheets that can be used interactively

# Repository structure

The repository contains one folder per module named `<d>_module_name`,
with `<d>` being a digit module number.

Each of these folders holds code examples corresponding to a unit. Not
all units have code examples.

# Other

The worksheets have been tested extensively using VCS/Metals. The
worksheet functionality in Metals is still relatively new, but it's
gaining functionality, and the Metals team is very active in resolving
issues as soon as they're identified.

_One important thing to note is that, in Metals, Scala worksheets have to
use the `.worksheet.sc` file extension_
