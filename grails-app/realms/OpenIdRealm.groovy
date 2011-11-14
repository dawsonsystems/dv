import com.daureos.facebook.FacebookGraphService
import grails.converters.JSON
import org.apache.shiro.authc.SimpleAccount
import org.apache.shiro.crypto.hash.Sha1Hash
import uk.co.devooght.auth.FacebookToken
import uk.co.devooght.auth.OpenIDToken
import org.apache.shiro.authc.DisabledAccountException
import org.apache.shiro.authc.UnknownAccountException
//import uk.co.devooght.core.UserBase
//import uk.co.devooght.core.Role
//import uk.co.devooght.auth.InstanceGenerator
import uk.co.devooght.auth.OpenIDService

class OpenIdRealm {
  static authTokenClass = OpenIDToken

  def grailsApplication
  def openIDService
  def userService

  def authenticate(OpenIDToken authToken) {

    println "AUTHENTICATING VIA OPENID!!!"
    log.info "Attempting to authenticate user via OpenID"

    def userID = authToken.getUserID()
    def user = ShiroUser.findByUsername(userID + OpenIDService.federationProviderDiscriminator)
    if (!user) {

      log.info("No account representing user $userID$OpenIDService.federationProviderDiscriminator exists")
//
      def openidFederationProvider = uk.co.devooght.core.FederationProvider.findByUid(OpenIDService.federationProviderUid)
//
      if (openidFederationProvider && openidFederationProvider.autoProvision) {

//        uk.co.devooght.core.UserBase newUser = InstanceGenerator.user()
        newUser.username = userID + OpenIDService.federationProviderDiscriminator
        newUser.enabled = true
        newUser.external = true
        newUser.federated = true
        newUser.federationProvider = openidFederationProvider

        newUser.profile = InstanceGenerator.profile()
        newUser.profile.owner = newUser

        newUser.profile.fullName = authToken.fullName
        newUser.profile.nickName = authToken.nickName
        newUser.profile.email = authToken.email


//        user = new UserBase()
        user.username = userID + "openid"
        //user.facebookId = userId
        println "Found ${myInfo.first_name}-${myInfo.last_name}/ $userId"

        def role = Role.findByName("user")

        user.firstName = myInfo.first_name
        user.lastName = myInfo.first_name
        user.displayName = myInfo.name
        user.pictureUrl = facebookGraphService.getProfilePhotoSrc(myInfo.id)

        user = userService.createUser(newUser)

        if (user.hasErrors()) {
          log.warn("Error creating user account from openID credentials for $userID$OpenIDService.federationProviderDiscriminator")
          user.errors.each {
            log.error(it)
          }
          throw new RuntimeException("Account creation exception for new openID based account");
        }
        log.info("Created new user [$user.id]$user.username from openID credentials")
      }
      else
        throw new UnknownAccountException("No account found for openID federated user ${userID}")
    }

    if (!user.enabled) {
      log.warn("Attempt to authenticate using using Facebook Account with locally disabled account [$user.id]$user.username")
      throw new DisabledAccountException("The openID federated account ${userID} is currently disabled")
    }

    def account = new SimpleAccount(user.id, authToken.principal, "OpenIDRealm")

    log.info("Successfully logged in user [$user.id]$user.username using OpenID")
    return account
  }
}
