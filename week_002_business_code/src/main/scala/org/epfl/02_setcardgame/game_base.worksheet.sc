case class Card(shape: Shape, number: Int, color: Color, shading: Shading)

enum Shape:
  case Diamond, Squiggle, Oval
import Shape._

enum Color:
  case Red, Green, Purple
import Color._

enum Shading:
  case Open, Striped, Solid
import Shading._

val deck = List(
  Card(Diamond,  1, Purple, Striped),
  Card(Squiggle, 2, Red,    Open),
  Card(Oval,     3, Green,  Solid)
)

val nonsensicalCard = Card(Diamond, 0, Purple, Striped)

