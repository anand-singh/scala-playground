package com.playground.chimney.util

object PasswordHash {

  def apply(s: String): String = {
    import java.math.BigInteger
    import java.security.MessageDigest
    val md = MessageDigest.getInstance("MD5")
    val digest = md.digest(s.getBytes)
    val bigInt = new BigInteger(1,digest)
    bigInt.toString(16)
  }

}
