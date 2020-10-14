package org.epfl.errorhandling.part9

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits._
import scala.util.Random
import scala.util.control.NonFatal

def getPagesCount(): Future[Int] = Future(42)

def getPage(page: Int): Future[String] =
  if Random.nextDouble() > 0.95 then
    Future.failed(Exception(s"Timeout when fetching page $page"))
  else Future(s"Page $page")

def getAllPages(): Future[Seq[String]] =
  getPagesCount().flatMap { totalPages =>
    Future.traverse(1 to totalPages)(getPage)
  }

def resilientGetAllPages(): Future[Seq[String]] =

  val maxAttempts = 3

  def attempt(remainingAttempts: Int): Future[Seq[String]] =
    if remainingAttempts == 0 then
      Future.failed(Exception(s"Fetching all the pages failed after $maxAttempts"))
    else
      println(s"Trying to fetch all the pages ($remainingAttempts remaining attempts)")
      getAllPages()
        .recoverWith { case NonFatal(_) =>
          System.err.println(s"Fetching all the paged failed...")
          attempt(remainingAttempts - 1)
        }

  attempt(maxAttempts)
end resilientGetAllPages

@main def run() =
  resilientGetAllPages().onComplete(println)
