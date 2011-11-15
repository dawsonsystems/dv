package uk.co.devooght.stock

class Product {

  String name
  String productCode

  BigDecimal costPrice

  static hasMany = [skus: Sku]

  static constraints = {
  }
}
