package uk.co.devooght.stock

class Product {

  String name
  String productCode
  String category

  static hasMany = [skus: Sku]

  static constraints = {
    productCode blank:false
    name nullable:true
    category blank:false
  }
}
