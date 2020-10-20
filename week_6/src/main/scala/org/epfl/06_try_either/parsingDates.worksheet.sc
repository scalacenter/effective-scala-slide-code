import java.time.LocalDate

import scala.io.Source
import scala.util.{Failure, Success, Try, Using}

type Errors = Seq[String]
type Validated[A] = Either[Errors, A]

def validateBoth[A, B](
  validatedA: Validated[A],
  validatedB: Validated[B]
): Validated[(A, B)] =
  (validatedA, validatedB) match {
    case (Right(a), Right(b)) => Right((a, b))
    case (Left(e),  Right(_)) => Left(e)
    case (Right(_), Left(e))  => Left(e)
    case (Left(e1), Left(e2)) => Left(e1 ++ e2)
  }

def validateEach[A, B](
  as: List[A]
)(
  validate: A => Validated[B]
): Validated[List[B]] =
  as.foldLeft[Validated[List[B]]](Right(Nil)) {
    (validatedBs, a) =>
      val validatedB: Validated[B] = validate(a)
      validateBoth(validatedB, validatedBs)
        .map((b, bs) => b :: bs)
  }

def parseDate(str: String): Validated[LocalDate] =
  Try(LocalDate.parse(str)) match {
    case Failure(exception) => Left(Seq(exception.toString()))
    case Success(date)      => Right(date)
  }

def readDateStrings(fileName: String): Try[List[String]] =
  Using(Source.fromFile(fileName)) { source =>
    source.getLines().toList
  }

def readAndParseDates(fileName: String): Try[Validated[List[LocalDate]]] =
  readDateStrings(fileName).map { dateStrings =>
    validateEach(dateStrings)(parseDate)
  }

def run(fileName: String): Unit =
  readAndParseDates(fileName) match {
    case Failure(exception) =>
      System.err.println(s"Unable to parse dates file: $exception")
    case Success(validatedDates) =>
      validatedDates match {
        case Left(errors) =>
          println(s"Invalid data: ${errors.mkString(", ")}")
        case Right(dates) =>
          println(s"Successfully parsed dates: ${dates.mkString(", ")}")
      }
  }
