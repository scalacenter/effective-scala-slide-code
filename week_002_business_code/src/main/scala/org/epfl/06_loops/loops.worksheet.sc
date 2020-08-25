

// Calculating factorial using `foldLeft`
def factorial(n: Int): Int =
  (1 to n).foldLeft(1)((result, x) => result * x)

factorial(4)
factorial(12)

// Calculating factorial using `product` on a collection
def factorial_1(n: Int): Int = (1 to n).product

factorial_1(4)
factorial_1(12)

// Calculating factorial imperatively
def factorial_2(n: Int): Int =
  var acc = 1
  var i = 1
  while (i < n) {
    i = i + 1
    acc = acc * i
  }
  acc

factorial_2(4)
factorial_2(12)

// Calculating factorial recursively
def factorial_3(n: Int): Int =
  if n == 0 then 1
  else n * factorial_3(n - 1)

factorial_3(4)
factorial_3(12)