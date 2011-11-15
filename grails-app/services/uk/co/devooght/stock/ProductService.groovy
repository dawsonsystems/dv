package uk.co.devooght.stock

class ProductService {
  static expose = [ 'gwt:uk.co.devooght' ]
  static transactional = true

  List<ProductDTO> getTree() {
    return Product.list(['order':"name"]).toDTO(ProductDTO)
  }
}
