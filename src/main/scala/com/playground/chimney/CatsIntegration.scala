package com.playground.chimney

import cats.data.{ NonEmptyChain, Validated }
import com.playground.chimney.util.PasswordHash
import io.scalaland.chimney.cats._
import io.scalaland.chimney.dsl._
import io.scalaland.chimney.{ Transformer, TransformerF }

object CatsIntegration {

  case class RegistrationForm(
      email: String,
      username: String,
      password: String,
      age: String
    )

  case class RegisteredUser(
      email: String,
      username: String,
      passwordHash: String,
      age: Int
    )

  implicit val validatedTransformer: TransformerF[Validated[NonEmptyChain[
    String
  ], +*], RegistrationForm, RegisteredUser] =
    Transformer
      .defineF[Validated[
        NonEmptyChain[String],
        +*
      ], RegistrationForm, RegisteredUser]
      .withFieldComputedF(
        _.email,
        form =>
          if (form.email.contains('@'))
            Validated.valid(form.email)
          else
            Validated.invalid(
              NonEmptyChain(
                s"${form.username}'s email: does not contain '@' character"
              )
            )
      )
      .withFieldComputed(_.passwordHash, form => PasswordHash(form.password))
      .withFieldComputedF(
        _.age,
        form =>
          form.age.toIntOption match {
            case Some(value) if value >= 18 => Validated.valid(value)
            case Some(_) =>
              Validated.invalid(
                NonEmptyChain(
                  s"${form.username}'s age: must have at least 18 years"
                )
              )
            case None =>
              Validated.invalid(
                NonEmptyChain(s"${form.username}'s age: invalid number")
              )
          }
      )
      .buildTransformer

  val regForm: RegistrationForm =
    RegistrationForm("john@example.com", "John", "password", "20")
  val regUser: Validated[NonEmptyChain[String], RegisteredUser] =
    regForm.transformIntoF[Validated[NonEmptyChain[String], +*], RegisteredUser]
  println(regForm)
  println(regUser)

}
