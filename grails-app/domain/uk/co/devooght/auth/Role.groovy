package uk.co.devooght.auth

class Role {
  String name

  static hasMany = [permissions:String]

  static constraints = {
  }
}
