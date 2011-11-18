package uk.co.devooght

import uk.co.devooght.stock.Product
import uk.co.devooght.stock.ProductDTO
import org.apache.commons.beanutils.BeanUtils
import uk.co.devooght.stock.SkuDTO
import uk.co.devooght.stock.Sku
import uk.co.devooght.stock.ProductImageDTO
import uk.co.devooght.stock.ProductImage

class ProductService implements uk.co.devooght.stock.ProductService {
  static expose = [ 'gwt:uk.co.devooght' ]

  static transactional = true

//  List getTree() {
//    return Product.list(['order':"name"])
//  }
//
  List<ProductDTO> getTree() {
    return Product.list(['order':"name"]).toDTO(ProductDTO)
  }
  Boolean saveProduct(ProductDTO productDTO) {

    Product product

    if (productDTO.id) {
      product = Product.get(productDTO.id)
    } else {
      product = new Product()
    }

    product.name = productDTO.name
    product.productCode = productDTO.productCode
    product.costPrice = productDTO.costPrice

    product.save()

    return true
  }

  List<SkuDTO> getSkus(ProductDTO productDTO) {
    (Product.get(productDTO.id).skus.toDTO(SkuDTO) as List).sort { it.stockCode }
  }

  Boolean saveSku(ProductDTO productdto, SkuDTO sku) {

    if (sku.id) {
      //update an existing SKU
      Sku existingSku = Sku.get(sku.id)
      println "SKU PRICE==${sku.price}"
      existingSku.inventoryLevel = sku.inventoryLevel
      existingSku.price = sku.price as BigDecimal
      existingSku.stockCode = sku.stockCode
      return true
    }

    //save a new SKU
    Product product = Product.get(productdto.id)

    Sku newSku = new Sku(product: product, inventoryLevel:sku.inventoryLevel,
                         price: sku.price as BigDecimal, stockCode: sku.stockCode)

    if (!newSku.save()) {
      newSku.errors.allErrors.each {
        println it
      }
      return false
    }
    return true
  }

  List<ProductImageDTO> getImages(ProductDTO product) {
    def ret = ProductImage.findAllByProduct(Product.get(product.id))

    def dtos = (ret.toDTO(ProductImageDTO) as List)

    return dtos.sort {
      it.id
    }

  }
}