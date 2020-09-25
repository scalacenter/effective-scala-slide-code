trait A:
  given x as Int = 0

trait B extends A:
  given y as Int = 1

object C extends B:
  val n = summon[Int]

C.n

given x as Int = 0
def foo() =
  given y as Int = 1
  summon[Int]

foo()

class General()
class Specific() extends General()

given general as General = General()
given specific as Specific = Specific()

summon[General]
