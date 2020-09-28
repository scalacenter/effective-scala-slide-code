case class A(n: Int)
case class B(n: Int)
case class C(n: Int)
case class D(n: Int)

given A = A(12)
// given aToB(using a: A) as B = B(a.n * 2)
// given bToC(using b: B) as C = C(b.n + 1)
// given cToD(using c: C) as D = D(c.n * 3)

// Anonymous version
given (using a: A) as B = B(a.n * 2)
given (using b: B) as C = C(b.n + 1)
given (using c: C) as D = D(c.n * 3)

summon[D]