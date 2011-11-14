package uk.co.devooght.auth

class LocalLogon extends Logon {

  String username
  String passwordHash

  static constraints = {
    username blank:false
    passwordHash blank:false
  }
}
