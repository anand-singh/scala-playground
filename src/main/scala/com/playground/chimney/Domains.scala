package com.playground.chimney

object Domains {

  val rawProfile: Profile.Raw = Profile.Raw(
    name = "Hulk",
    email = "h@h.com",
    age = 35,
    address = Address.Raw(
      street = Street.Raw("3rd Ave", "55"),
      city = City.Raw("Amsterdam", "1111ZE"),
    ),
  )

  final case class Profile(
      name: Profile.Name,
      email: Profile.Email,
      age: Profile.Age,
      address: Address,
    )

  case object Profile {
    final case class Name(value: String) extends AnyVal {
      override def productPrefix: String = s"$Profile.$Name"
    }

    final case class Email(value: String) extends AnyVal {
      override def productPrefix: String = s"$Profile.$Email"
    }

    final case class Age(value: Int) extends AnyVal {
      override def productPrefix: String = s"$Profile.$Age"
    }

    final case class Raw(
        name: String,
        email: String,
        age: Int,
        address: Address.Raw,
      ) {
      override def productPrefix: String = s"$Profile.$Raw"
    }
  }

  final case class Address(street: Street, city: City)
  case object Address {
    final case class Raw(street: Street.Raw, city: City.Raw) {
      override def productPrefix: String = s"$Address.$Raw"
    }
  }

  final case class Street(name: Street.Name, number: Street.Number)
  case object Street {
    final case class Name(value: String) extends AnyVal {
      override def productPrefix: String = s"$Street.$Name"
    }

    final case class Number(value: String) extends AnyVal {
      override def productPrefix: String = s"$Street.$Number"
    }

    final case class Raw(name: String, number: String) {
      override def productPrefix: String = s"$Street.$Raw"
    }
  }

  final case class City(name: City.Name, zipCode: City.ZipCode)
  case object City {
    final case class Name(value: String) extends AnyVal {
      override def productPrefix: String = s"$City.$Name"
    }

    final case class ZipCode(value: String) extends AnyVal {
      override def productPrefix: String = s"$City.$ZipCode"
    }

    final case class Raw(name: String, zipCode: String) {
      override def productPrefix: String = s"$City.$Raw"
    }
  }

}
