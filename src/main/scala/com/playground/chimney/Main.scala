package com.playground.chimney

import io.scalaland.chimney.dsl._

object Main extends App {

  println("─" * 100)

  final case class Form(firstName: String, lastName: String, email: String, age: Int)

  final case class User(firstName: String, lastName: String, email: String, age: Int)

  val form = Form("James", "Ward", "j@w.com", 28)
  val user = form.transformInto[User]

  println(form)
  println(user)

  println("─" * 100)
}
