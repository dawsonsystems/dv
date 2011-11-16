package uk.co.devooght

import uk.co.devooght.stock.Product
import uk.co.devooght.stock.ProductDTO
import org.apache.commons.beanutils.BeanUtils

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
      product = Product.get(id)
    } else {
      product = new Product()
    }

    product.name = productDTO.name
    product.productCode = productDTO.productCode
    product.costPrice = productDTO.costPrice

    product.save()

    return true
  }
}