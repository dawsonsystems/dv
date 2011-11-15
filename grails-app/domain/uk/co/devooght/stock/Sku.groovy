package uk.co.devooght.stock

class Sku {
  Product product

  static belongsTo = [Product]

  String stockCode
  BigDecimal price

  static constraints = {
    stockCode blank:false
    price:nullable:false
  }
}
