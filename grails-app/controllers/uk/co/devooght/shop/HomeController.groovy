package uk.co.devooght.shop

import uk.co.devooght.stock.Product

class HomeController {

  def index = {
    //load up the request with the correct information...
    request.products = Product.list()

    render(view: "/shop/render", model: [weceemView:"shop/index"])
  }

  def product = {
    println "Loading product ${params.id}"
    request.product = Product.findByProductCode(params.id)
    println "Product is ${request.product}"

    render(view: "/shop/render", model: [weceemView:"shop/product"])
  }

  def collection = {


    render(view: "/shop/render", model: [weceemView:"shop/collection"])
  }

  def basket = {

    render(view: "/shop/render", model: [weceemView:"shop/basket"])
  }
}
