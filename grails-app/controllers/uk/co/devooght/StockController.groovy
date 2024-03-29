package uk.co.devooght

import org.apache.shiro.authz.annotation.RequiresPermissions
import org.apache.shiro.grails.annotations.PermissionRequired
import org.apache.shiro.grails.ShiroBasicPermission
import uk.co.devooght.stock.Product
import uk.co.devooght.stock.ProductImage
import org.springframework.web.multipart.MultipartFile
import grails.converters.JSON
import org.codehaus.groovy.grails.commons.ConfigurationHolder

class StockController {

  def productService

  def index = {

  }

  def upload = {

    String serverPath = ConfigurationHolder.config.images.serverPath
    String localPath = ConfigurationHolder.config.images.localPath

    MultipartFile img = request.getFile("file")

    log.info("Image upload requested...${img.contentType}")

    Product product = Product.get(params.productId)
    String name = "${UUID.randomUUID().toString()}.${createExtension(img.contentType)}"

    log.info("Saved file to local storage ${name}")

    new File(localPath).mkdirs()

    img.transferTo(new File(localPath, name))

    def image = new ProductImage(product: product, location: "${serverPath}/$name")
    if (!image.save()) {
      image.errors.allErrors.each {
        println it
      }
    }
    render "{name:'${img.getOriginalFilename()}',state:'OK',message:'ok'}"
  }

  def createExtension(String contentType) {
    def extensions = ["image/png":"png", "image/jpeg":"jpg"]

    def ret = extensions[contentType]
    log.debug("Selected type ${ret}")
    return ret
  }

}
