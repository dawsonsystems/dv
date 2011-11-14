import org.apache.shiro.crypto.hash.Sha256Hash
import uk.co.devooght.auth.Role

class BootStrap {

    def init = { servletContext ->
//        def user = new ShiroUser(username: "user123", passwordHash: new Sha256Hash("password").toHex())
//        user.addToPermissions("*:*")
//        user.save(flush:true)
      if (!Role.findByName("user")) {
        log.info("Generated User Role")
        Role userRole = new Role(name:"user")
        userRole.addToPermissions("stock:*")
        userRole.save()
      }

      if (!Role.findByName("admin")) {
        log.info("Generated Admin Role")
        Role userRole = new Role(name:"admin")
        userRole.addToPermissions("*:*")
        userRole.save()
      }
    }

    def destroy = {
    }
}
