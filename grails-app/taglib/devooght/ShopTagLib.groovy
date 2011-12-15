package devooght

import uk.co.devooght.stock.Product
import uk.co.devooght.stock.ProductImage
import java.text.NumberFormat

class ShopTagLib {
  static namespace = "shop"
  static format = NumberFormat.getCurrencyInstance(Locale.UK)

  def asCurrency = { attrs ->
    if (attrs.value == null) {
			attrs.value = 0.0
		}
		out << format.format(attrs.value)
  }

  def productUrl = { attrs ->
    def productCode = attrs.productCode

    if (productCode) {
      def product = Product.findByProductCode(productCode)
      def name = uk.co.devooght.UrlSanitizer.sanitize(product.name)
      out << g.createLink(uri:"/p/${product.productCode}/${name}")
    } else {
      out << "PRODUCT ${productCode} NOT FOUND"
    }
  }

  def productImageUrl = { attrs ->
    Product product = attrs.product

    def image = ProductImage.findByProduct(product)

    if (image) {
      out << image.location
    }
  }

  def addToBasketUrl = { attrs ->
    def sku = attrs.sku

  }

  def removeFromBasketUrl = { attrs ->
    def sku = attrs.sku

  }

}
