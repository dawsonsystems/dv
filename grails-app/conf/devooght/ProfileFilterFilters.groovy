package devooght

import org.apache.shiro.SecurityUtils
import uk.co.devooght.auth.Profile
import uk.co.devooght.auth.Logon

class ProfileFilterFilters {

  def filters = {
    all(controller: '*', action: '*') {
      before = {
        if (SecurityUtils.subject.authenticated) {
          request.profile = Logon.findByIdentifier(SecurityUtils.subject.principal).profile
        }
      }
      after = {

      }
      afterView = {

      }
    }
  }

}
