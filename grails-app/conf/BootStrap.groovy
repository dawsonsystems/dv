import org.apache.shiro.crypto.hash.Sha256Hash
import uk.co.devooght.auth.Role
import grails.util.Environment
import uk.co.devooght.stock.Product
import uk.co.devooght.stock.ProductImage

class BootStrap {

    def init = { servletContext ->

//        def user = new ShiroUser(username: "user123", passwordHash: new Sha256Hash("password").toHex())
//        user.addToPermissions("*:*")
//        user.save(flush:true)
      if (!Role.findByName("user")) {
        log.info("Generated User Role")
        Role userRole = new Role(name:"user")

        //TODO, fix the permissions.
        userRole.addToPermissions("*:*")
        userRole.save()
      }

      if (!Role.findByName("admin")) {
        log.info("Generated Admin Role")
        Role userRole = new Role(name:"admin")
        userRole.addToPermissions("*:*")
        userRole.save()
      }

      if (Environment.current == Environment.DEVELOPMENT) {

        if (!Product.findByProductCode("12345")) {
          Product prod = new Product(name: "Testing Product", productCode: "12345", category: "ring")
          if (!prod.save()) {
            prod.errors.allErrors.each {
              println it
            }
          }

          prod.addToSkus(stockCode:"12345-stock",
                        price:45.99,
                        costPrice:13.99,
                        inventoryLevel:2)

          prod.addToSkus(stockCode:"12345-stock2",
                        price:47.99,
                        costPrice:13.99,
                        inventoryLevel:4)

          prod = new Product(name: "Wibble hello", productCode: "45679", category: "necklace")

          prod.addToSkus(stockCode:"igglestock",
                        price:12.99,
                        costPrice:13.99,
                        inventoryLevel:0)

          prod.addToSkus(stockCode:"igglestock13",
                        price:14.99,
                        costPrice:13.99,
                        inventoryLevel:99)

          if (!prod.save()) {
            prod.errors.allErrors.each {
              println it
            }
          }


          new ProductImage(product: prod, location: "/devooght/images/grails_logo.jpg").save()
          new ProductImage(product: prod, location: "/devooght/images/spinner.gif").save()

        }
      }
    }

    def destroy = {
    }
}
