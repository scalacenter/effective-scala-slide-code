
trait X {
  val x = 5
}
given loop(using a: X) as X = a

// given X = new X {}

val x = summon[X]

x.x