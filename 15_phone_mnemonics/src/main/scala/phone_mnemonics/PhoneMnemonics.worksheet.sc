/**
 * Returns all the ways to split a sequence of digits into sub-sequences
 * of digits
 */
def split(digits: String): Seq[Seq[String]] =
  if digits.isEmpty then Seq(Seq.empty)
  else
    for
      splitPoint <- 1 to digits.length
      (firstDigits, remainingDigits) = digits.splitAt(splitPoint)
      remainingDigitsSplit <- split(remainingDigits)
    yield firstDigits +: remainingDigitsSplit

split("8379")

val index: Map[String, Seq[String]] = Map(
  "63" -> Seq("of", "me"),
  "66" -> Seq("on", "no")
)

def phrases(digits: String): Seq[Seq[String]] =
  if digits.isEmpty then Seq(Seq.empty)
  else
    for
      splitPoint <- 1 to digits.length
      (firstDigits, remainingDigits) = digits.splitAt(splitPoint)
      word  <- index.getOrElse(firstDigits, Nil)
      words <- phrases(remainingDigits)
    yield word +: words

phrases("63")
phrases("636")
phrases("6366")

class Mnemonics(dictionary: Set[String]):

  /** Maps every key of the phone to their assigned letters */
  private val keys: Map[Char, String] = Map(
    '2' -> "ABC",
    '3' -> "DEF",
    '4' -> "GHI",
    '5' -> "JKL",
    '6' -> "MNO",
    '7' -> "PQRS",
    '8' -> "TUV",
    '9' -> "WXYZ"
  )

  /** Maps a letter to the digit it is assigned to */
  private val letterToDigit: Map[Char, Char] =
    for
      (digit, letters) <- keys
      letter           <- letters
    yield letter -> digit

    /** Maps a word to the sequence of digits it can represent:
     *
     * {{{
     *   wordToDigits("hello") == "43556"
     *   wordToDigits("Scala") == "72252"
     * }}}
     */
  private def wordToDigits(word: String): String =
    word.toUpperCase.map(letterToDigit)

  /**
   * Maps sequences of digits to the collection of words of the dictionary
   * that they represent
   */
  private val index: Map[String, Set[String]] =
    dictionary.groupBy(wordToDigits)

  /**
   * All ways to encode the given phone number as a list of words from
   * the dictionary
   */
  def ofPhoneNumber(digits: String): Seq[Seq[String]] =
    if digits.isEmpty then Seq(Nil)
    else
      for
        splitPoint <- 1 to digits.length
        (firstDigits, remainingDigits) = digits.splitAt(splitPoint)
        word  <- index.getOrElse(firstDigits, Nil)
        words <- ofPhoneNumber(remainingDigits)
      yield word +: words

end Mnemonics

// --- Usage example

val dictionary = Set(
  "Scala",
  "rocks",
  "is",
  "fun",
  "love",
  "thank",
  "me",
  "you",
  "of"
)

val mnemonics = Mnemonics(dictionary)

mnemonics.ofPhoneNumber("7225276257")
mnemonics.ofPhoneNumber("7225247386")
mnemonics.ofPhoneNumber("7225284265968")
mnemonics.ofPhoneNumber("968568363")
