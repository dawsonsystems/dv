package uk.co.devooght.stock

class Sku {
  Product product

  static belongsTo = [Product]

  String stockCode

  BigDecimal costPrice
  BigDecimal price
  Integer inventoryLevel

  BigDecimal weight
  BigDecimal length
  BigDecimal diameter
  String ringSize

  static constraints = {
    costPrice(nullable:false)
    stockCode blank:false
    price nullable:true
    inventoryLevel nullable:false

    weight(nullable:true)
    length(nullable:true)
    diameter(nullable:true)
    ringSize(nullable:true)
  }
}
