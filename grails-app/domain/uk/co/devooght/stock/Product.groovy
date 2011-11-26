package uk.co.devooght.stock

class Product {

  String name
  String productCode
  String category
  String shape
  String pattern
  String altMaterial

  static hasMany = [skus: Sku]
  static searchable = {
    skus component:true
  }

  static constraints = {
    productCode blank:false, unique:true
    name nullable:true
    category blank:false
    shape nullable:true
    pattern nullable:true
    altMaterial nullable: true
  }
}
