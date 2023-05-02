package follow_the_types

import java.time.LocalDate
import java.nio.file.Path

import scala.io.Source
import scala.util.{Failure, Success, Try, Using}

type Errors = Seq[String]
type Validated[A] = Either[Errors, A]

def findFirstRowWithEvenValues(rows: Vector[Vector[Int]]): Option[Seq[Int]] =
  rows.find(row => row.forall(x => x % 2 == 0))

def increment(maybeXs: Option[Seq[Int]]): Option[Seq[Int]] =
  maybeXs.map(xs => xs.map(x => x + 1))

def read(path: Path): Try[String] =
  Using(Source.fromFile(path.toFile)) { source =>
    source.getLines().mkString("\n")
  }

// Incorrect first attempt to implement `readAllFiles`
def readAllFilesWrong(tentativePaths: Try[Seq[Path]]) =
  tentativePaths.map(paths => paths.map(path => read(path)))

case class MyResult[A](tentativeValidatedValue: Try[Validated[A]]):

  def map[B](f: A => B): MyResult[B] =
    MyResult(tentativeValidatedValue.map(validatedValue => validatedValue.map(f)))

  def flatMap[B](f: A => MyResult[B]): MyResult[B] =
    val tentativeValidatedB: Try[Validated[B]] =
      tentativeValidatedValue.flatMap {
        case Right(value) => f(value).tentativeValidatedValue
        case Left(errors) => Success(Left(errors))
      }
    MyResult(tentativeValidatedB)

end MyResult

object MyResult:
  def successfulValid[A](value: A): MyResult[A] =
    MyResult(Success(Right(value)))
  def successfulInvalid[A](errors: Errors): MyResult[A] =
    MyResult(Success(Left(errors)))
  def successfulValidated[A](validatedValue: Validated[A]): MyResult[A] =
    MyResult(Success(validatedValue))
  def failed[A](throwable: Throwable): MyResult[A] =
    MyResult(Failure(throwable))
end MyResult

def incrementDates(dates: Seq[LocalDate]): Seq[LocalDate] =
  dates.map(date => date.plusDays(1))

def incrementTentativeValidatedDates(
  tentativeValidatedDates: Try[Validated[Seq[LocalDate]]]
): Try[Validated[Seq[LocalDate]]] =
  tentativeValidatedDates
    .map(validatedDates => validatedDates.map(dates => incrementDates(dates)))

// Same as `incrementTentativeValidatedDates`, but with `Result` instead
def incrementDatesResult(datesResult: MyResult[Seq[LocalDate]]) =
  datesResult.map(dates => incrementDates(dates))

// Complete implementation of `readAllFiles`, using a functional programming style
// for the loop (with `foldLeft` on the sequence).
def readAllFilesFunctionally(tentativePaths: Try[Seq[Path]]): Try[Seq[String]] =
  tentativePaths.flatMap { paths =>
    paths.foldLeft[Try[Seq[String]]](Success(Vector.empty[String])) {
      (tentativePreviousTexts, path) =>
        for
          previousTexts <- tentativePreviousTexts
          text          <- read(path)
        yield previousTexts :+ text
    }
  }

// Another implementation of `readAllFiles` using a functional programming style,
// but the loop is implemented by a recursive method `tryReadPaths`.
// An important difference with the implementation based of `foldLeft` is that here
// we exit the loop as soon as there is a failure.
def readAllFilesFunctionally2(tentativePaths: Try[List[Path]]): Try[Seq[String]] =
  def tryReadPaths(paths: List[Path], previouslyReadTexts: Vector[String]): Try[Seq[String]] =
    paths match
      case Nil => Success(previouslyReadTexts)
      case path :: remainingPaths =>
        read(path) match
          case Success(text) =>
            tryReadPaths(remainingPaths, previouslyReadTexts :+ text)
          case Failure(error) => Failure(error)
  tentativePaths.flatMap(paths => tryReadPaths(paths, Vector.empty))

// The last implementation of `readAllFiles`, this time with an “imperative-style” loop.
def readAllFilesImperatively(tentativePaths: Try[Seq[Path]]): Try[Seq[String]] =
  tentativePaths.flatMap { paths =>
    val texts = Seq.newBuilder[String]
    var maybeFailure = Option.empty[Failure[Seq[String]]]
    val it = paths.iterator
    while it.hasNext && maybeFailure.isEmpty do
      read(it.next()) match
        case Success(text)  => texts += text
        case Failure(error) => maybeFailure = Some(Failure(error))
    maybeFailure match
      case Some(failure) => failure
      case None          => Success(texts.result())
  }

// For the sake of comparison, here is an implementation of `readAllFiles`
// that does not do any error handling, and does not show in its result type
// that there is a chance of failure.
// On the one hand, the code is simpler, but on the other hand, it does not
// fairly inform the developers of its possibilities of failure. In some cases,
// this is perfectly fine (e.g., a non-critical one-off task), but in other
// cases (e.g., a long-running resilient server), this may not be the best
// solution.
def readAllFilesWithExceptions(paths: Seq[Path]): Seq[String] =
  for path <- paths yield read(path).get

def readOptionalFile(maybePath: Option[Path]): Try[Option[String]] =
  maybePath.fold(Success(None))(path => for text <- read(path) yield Some(text))

// Same as `readOptionalFile`, but with pattern matching instead of `fold`
def readOptionalFile2(maybePath: Option[Path]): Try[Option[String]] =
  maybePath match
    case None       => Success(None)
    case Some(path) => for text <- read(path) yield Some(text)

def readIntOrFile(intOrPath: Either[Int, Path]): Try[Either[Int, String]] =
  intOrPath.fold(
    int  => Success(Left(int)),
    path => for text <- read(path) yield Right(text)
  )

// Same as `readIntOrFile`, but with pattern matching instead of `fold`
def readIntOrFile2(intOrPath: Either[Int, Path]): Try[Either[Int, String]] =
  intOrPath match
    case Left(int)   => Success(Left(int))
    case Right(path) => for text <- read(path) yield Right(text)
