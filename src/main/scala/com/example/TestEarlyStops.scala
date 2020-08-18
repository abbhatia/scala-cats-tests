package com.example

import cats.implicits._

object TestEarlyStops extends App {

  val max = 5

  val xs = List(1, 2, 3, 4, 5).foldM(0) {
    case (total, x) =>
      val ux = total + x
      if (ux < max)
        ux.asRight // implicitly creates Either.Right here
      else
        total.asLeft // implicitly creates Either.Left here
  }

  println(xs)
}
