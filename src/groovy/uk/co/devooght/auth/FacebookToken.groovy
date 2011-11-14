package uk.co.devooght.auth

import org.apache.shiro.authc.AuthenticationToken

public class FacebookToken implements AuthenticationToken {

  /**
   * Returns the Facebook sessionID associated with the current session
   */
  public Object getPrincipal() {
    return "facebook"
  }

  /**
   * Returns a map of key/values associated with Facebook session establishment (retrieved from cookies)
   */
  public Object getCredentials() {
    return principal
  }
}