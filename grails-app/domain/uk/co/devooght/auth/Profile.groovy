package uk.co.devooght.auth

class Profile {

  String displayName
  String picture
  String email

  static hasMany = [logins:Logon, permissions:String, roles:Role]

  static constraints = {
    displayName(blank:false)
    picture(nullable:true)
    email(nullable:true)
  }
}
