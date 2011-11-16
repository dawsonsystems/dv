import org.apache.shiro.crypto.hash.Sha256Hash
import uk.co.devooght.auth.Role
import grails.util.Environment
import uk.co.devooght.stock.Product

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

        Product prod = new Product(costPrice: 13.99, name: "Testing Product", productCode: "12345")
        prod.save()
        prod = new Product(costPrice: 14.99, name: "Wibble hello", productCode: "45679")
        prod.save()
      }
    }

    def destroy = {
    }
}
