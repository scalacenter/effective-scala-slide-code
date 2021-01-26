package effective

// import cats.instances.all._
import doodle.java2d._
// import doodle.syntax._
import doodle.effect.Writer._
// import doodle.examples._
import doodle.image._
import doodle.image.syntax._
// import doodle.image.examples._
// import doodle.interact.syntax._
// import doodle.explore.syntax._
import doodle.core._

def house1 =
  Image.rectangle(100, 200)
    .fillColor(Color.brown)
    .at(0, -50) on
  Image.rectangle(100, 100)
    .fillColor(Color.darkGray)
    .at(-150, 50) on
  Image.rectangle(100, 100)
    .fillColor(Color.darkGray)
    .at(150, 50) on
  Image.rectangle(500, 300)
    .fillColor(Color.gray)

def house2 =
  Image.rectangle(100, 200)
    .fillColor(Color.brown)
    .at(-100, -100) on
  Image.rectangle(150, 100)
    .fillColor(Color.darkGray)
    .at(100, -50) on
  Image.rectangle(150, 100)
    .fillColor(Color.darkGray)
    .at(100, 100) on
  Image.rectangle(400, 400)
    .fillColor(Color.gray)

val picture =
  house1.at(-600, -50).on(house2)

@main def run(): Unit =
  picture.write[Pdf]("houses.pdf")
