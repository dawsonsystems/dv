package uk.co.devooght.shop

import uk.co.devooght.stock.Sku
import uk.co.devooght.front.Basket

class BasketController {

  def add = {
    def skuId = params.long("sku")

    def sku = Sku.get(skuId)

    if (!sku) {
      flash.error="Product with id ${skuId} doesn't exist"
      redirect(controller:"home", action:"basket")
      return
    }

    Basket basket = request.basket
    basket.addItem(sku)
    flash.success="Added ${sku.product.name} to your basket"
    redirect controller:"home", action:"basket"
  }
  def remove = {
    def skuId = params.long("sku")

    def sku = Sku.get(skuId)

    if (!sku) {
      redirect(controller:"home", action:"basket")
      return
    }

    Basket basket = request.basket
    basket.removeItem(sku)
    flash.success="Removed ${sku.product.name} from your basket"
    redirect controller:"home", action:"basket"
  }

  def update = {
    //spec the values here...
    Basket basket = request.basket

    basket.items.each { item ->
      item.quantity = params.int("${item.sku.stockCode}")
    }

    basket.recalculate()
  }

  def clear = {
    request.basket.clear()
  }
}
