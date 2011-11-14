import com.daureos.facebook.FacebookGraphService
import grails.converters.JSON

import org.apache.shiro.authc.SimpleAccount

import uk.co.devooght.auth.FacebookToken
import uk.co.devooght.auth.FacebookLogon
import uk.co.devooght.auth.Profile
import uk.co.devooght.auth.Role

class FacebookRealm {
    static authTokenClass = FacebookToken

    def credentialMatcher
    def shiroPermissionResolver
    FacebookGraphService facebookGraphService

    def authenticate(authToken) {

      def user
      def account

      try {
        def facebookInfo = getFacebookUser()

        FacebookLogon login = FacebookLogon.findByIdentifier(facebookInfo.id)

        if (!login) {

          login = new FacebookLogon(identifier:facebookInfo.id)

          Profile profile = new Profile()

          //TODO, email?
          profile.displayName = facebookInfo.name
          profile.picture = facebookGraphService.getProfilePhotoSrc(login.identifier)

          profile.addToLogins(login)

          if (profile.validate()) {
            profile.save(flush: true)
          } else {
            profile.errors.allErrors.each {
              log.error it
            }
          }

          //TODO, populate with the user role on signup instead....
          def role = Role.findByName("user")

          profile.addToRoles(role)

          account = new SimpleAccount(login.identifier, "facebookFakepassword", FacebookRealm.name)
          return account;
        } else {
          account = new SimpleAccount(login.identifier, "facebookFakepassword", FacebookRealm.name)
          return account;
        }

      } catch (Exception e) {
        e.printStackTrace()
      }
      return null
    }

    def getFacebookUser() {
      return JSON.parse (facebookGraphService.getFacebookProfile().toString() )

//      String userId = myInfo.id
//      java.lang.Iterable<java.lang.Long> userIds = new ArrayList<java.lang.Long>()
//      userIds.add(Long.parseLong(userId))
//
//
//      FacebookLogin user = new FacebookLogin()
//      user.facebookId = userId
//
//
//
//      println "Found ${myInfo.first_name}-${myInfo.last_name}/ $userId"
//
//      def role = ShiroRole.findByName("user")
//
//      user.firstName = myInfo.first_name
//      user.lastName = myInfo.first_name
//      user.displayName = myInfo.name
//      user.pictureUrl = facebookGraphService.getProfilePhotoSrc(myInfo.id)
//
//      role.addToUsers(user)

//      return user
    }
}
