package uk.co.devooght.stock

class Sku {
  Product product

  static belongsTo = [Product]

  String stockCode
  BigDecimal price
  Integer inventoryLevel

  static constraints = {
    stockCode blank:false
    price nullable:false
    inventoryLevel nullable:false
  }
}
