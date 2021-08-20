package com.playground.chimney

import com.playground.chimney.CatsIntegration._
import com.playground.chimney.util.PasswordHash
import io.scalaland.chimney._
import io.scalaland.chimney.dsl._

object EitherTransformation {

  type EitherVecStr[+X] = Either[Vector[String], X]

  implicit val eitherTransformer: TransformerF[EitherVecStr, RegistrationForm, RegisteredUser] =
    Transformer
      .defineF[EitherVecStr, RegistrationForm, RegisteredUser]
      .withFieldComputedF(
        _.email,
        form =>
          if (form.email.contains('@'))
            Right(form.email)
          else
            Left(
              Vector(
                s"${form.username}'s email: does not contain '@' character"
              )
            ),
      )
      .withFieldComputed(_.passwordHash, form => PasswordHash(form.password))
      .withFieldComputedF(
        _.age,
        form =>
          form.age.toIntOption match {
            case Some(value) if value >= 18 => Right(value)
            case Some(_) =>
              Left(
                Vector(s"${form.username}'s age: must have at least 18 years")
              )
            case None => Left(Vector(s"${form.username}'s age: invalid number"))
          },
      )
      .buildTransformer

  val regForm: RegistrationForm =
    RegistrationForm("john@example.com", "John", "password", "20")
  val regUser: EitherVecStr[RegisteredUser] = regForm.transformIntoF[EitherVecStr, RegisteredUser]
  println(regForm)
  println(regUser)

}
