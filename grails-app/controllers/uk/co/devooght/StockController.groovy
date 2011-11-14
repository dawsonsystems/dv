package uk.co.devooght

import org.apache.shiro.authz.annotation.RequiresPermissions
import org.apache.shiro.grails.annotations.PermissionRequired
import org.apache.shiro.grails.ShiroBasicPermission

class StockController {

  def productService

  @PermissionRequired(type=ShiroBasicPermission, target="itchy", actions="silly")
  def index = {
    println "Arse"
  }

}
