package com.playground

import io.scalaland.chimney.dsl._

object Main extends App {
  import com.playground.Domains._
  println("─" * 100)

  //Normal Transformation
  val form = Form("Jams", "Ward", "j@w.com", 28)
  val user = form.into[User].transform
  println(s"${form}")
  println(s"${user}")

  //Computed Transformation
  //Constant Transformation


  println("─" * 100)
}

object Domains {

  final case class Form(
      firstName: String,
      lastName: String,
      email: String,
      age: Int
    )

  final case class User(
      firstName: String,
      lastName: String,
      email: String,
      age: Int
    )

}
