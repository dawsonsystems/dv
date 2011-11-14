package uk.co.devooght.auth

import org.apache.shiro.authc.AuthenticationToken
import org.openid4java.discovery.Identifier

public class OpenIDToken implements AuthenticationToken {
  Identifier principal;

  def fullName, nickName, email, gender

  public OpenIDToken(Identifier identifier) {
    this.principal = identifier
  }

  /**
   * Returns the users validated OpenID identifier
   */
  public Object getPrincipal() {
    return this.principal
  }

  /**
   * Returns null for OpenID
   */
  public Object getCredentials() {
    return null
  }

  public String getUserID() {
    return this.principal.getIdentifier()
  }

  public Identifier getOpenIDIdentifier() {
    return this.principal
  }
}