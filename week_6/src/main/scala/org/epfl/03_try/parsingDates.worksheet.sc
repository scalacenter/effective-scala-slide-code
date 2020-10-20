import java.time.{LocalDate, Period}

import scala.io.Source
import scala.util.{Success, Try}

def parseDate(str: String): Try[LocalDate] =
  Try(LocalDate.parse(str))

def tryPeriod(str1: String, str2: String): Try[Period] =
  parseDate(str1).flatMap { date1 =>
    parseDate(str2).map { date2 =>
      Period.between(date1, date2)
    }
  }

tryPeriod("2020-07-27", "2020-12-25")
tryPeriod("2020-19-27", "2020-12-25")
tryPeriod("2020-07-27", "2020-22-25")
tryPeriod("2020-19-27", "2020-22-25")

def readDateStrings(fileName: String): Try[List[String]] = Try {
  val source = Source.fromFile(fileName)
  val lines = source.getLines()
  val numberOfDates = lines.next().toInt
  val dateStrings: List[String] =
    List.fill(numberOfDates)(lines.next())
  source.close()
  dateStrings
}

def parseDates(fileName: String): Try[List[LocalDate]] =
  readDateStrings(fileName).flatMap { dateStrings =>
    val tryDates =
      dateStrings.foldLeft[Try[List[LocalDate]]](Success(Nil)) {
        case (tryDates, dateString) =>
          tryDates.flatMap { dates =>
            parseDate(dateString).map { date =>
              date :: dates
            }
          }
      }
    tryDates
  }

parseDates("/tmp/dates-file.txt")
