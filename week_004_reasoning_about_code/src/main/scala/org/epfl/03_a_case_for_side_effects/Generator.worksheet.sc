class Generator(previous: Int):
  def nextInt(): (Int, Generator) =
    val result = previous * 22_695_477 + 1
    (result, Generator(result))

  def between(min: Int, max: Int): (Int, Generator) =
   val delta = max - min
   val (randomValue, nextGenerator) = nextInt()
   (scala.math.abs(randomValue % delta) + min, nextGenerator)

end Generator

object Generator:
  def init: Generator = Generator(42)

val gen1 = Generator.init
val (x, gen2) = gen1.nextInt()
println(x)
val (y, _) = gen1.nextInt()
println(y)
val (z, gen3) = gen2.nextInt()
println(z)

val (r, gen4) = gen3.between(-100, 100)
val (s, gen5) = gen4.between(-100, 100)
gen5.nextInt()
val (t, gen6) = gen5.between(1, 4)

val (windowSide, gen7) = gen5.between(1, 4)
val windowArea = windowSide * windowSide
