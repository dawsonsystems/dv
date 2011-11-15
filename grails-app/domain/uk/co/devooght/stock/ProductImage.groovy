package uk.co.devooght.stock

class ProductImage {

  //TODO, use a CDN?
  String location

  static constraints = {
    location(blank:false)
  }
}
