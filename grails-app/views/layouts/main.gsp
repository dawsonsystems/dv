<%@ page import="uk.co.devooght.auth.Profile; org.apache.shiro.SecurityUtils" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:fb="http://www.facebook.com/2008/fbml">
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" />
        <fbg:resources/>
    </head>
    <body>
        <script type="text/javascript">
        <!--
        function delayer() {
            window.location = "${createLink(controller:'auth', action: 'signIn')}"
        }
        //-->
        function facebookLogin() {
            FB.getLoginStatus(function(response) {
                if (response.session) {
                    // logged in and connected user, someone you know
                    window.location ="${createLink(controller:'auth', action:'facebookSignin', params:params)}";
                }
            });
        }
        </script>
         <shiro:isLoggedIn>
             You are logged in :${profile.displayName}!!
             <g:link controller="auth" action="signOut">Log out</g:link>
        </shiro:isLoggedIn>
        <shiro:isNotLoggedIn>
        <div id="login_section">
            <h4>Login using Facebook</h4>
            <div class="login_form">
                <fb:login-button perms="email" onlogin="facebookLogin();" size="large">
                    Login With Facebook
	            </fb:login-button>

            </div>

        </div> <!--Login Form Ends-->
          <%--<div id="google" class="loginmethod externalloginmethod">
            <h2><g:message code="nimble.template.login.google.heading" /></h2>
            <p>
              %{--<n:socialimg name="google" size="64" alt="Login using Google"/>--}%
			  <g:message code="nimble.template.login.google.descriptive" />
            </p>
            <p>
              <a href="${createLink(controller: "auth", action: "googlereq")}" class="button icon icon_user_green"><g:message code="nimble.link.login.basic" /></a>
            </p>
          </div>

          <div id="yahoo" class="loginmethod externalloginmethod">
            <h2><g:message code="nimble.template.login.yahoo.heading" /></h2>
            <p>
              %{--<n:socialimg name="yahoo" size="64" alt="Login using Yahoo"/>--}%
			  <g:message code="nimble.template.login.yahoo.descriptive" />
            </p>
            <p>
              <a href="${createLink(controller: "auth", action: "yahooreq")}" class="button icon icon_user_green"><g:message code="nimble.link.login.basic" /></a>
            </p>
          </div>

          <div id="openid" class="loginmethod externalloginmethod">
            <h2><g:message code="nimble.template.login.openid.heading" /></h2>
            <p>
              %{--<n:socialimg name="openid" size="64" alt="openid"/>--}%
			  <g:message code="nimble.template.login.openid.descriptive" />
            </p>
            <g:form controller="auth" action="openidreq">
              <strong><g:message code="nimble.template.login.openid.identifier" /></strong>
              <input id="openiduri" type="text" name="openiduri" class="easyinput" value="http://"/>
              <button type="submit" class="button icon icon_user_green"><g:message code="nimble.link.login.basic" /></button>
            </g:form>
          </div>

          <div id="blogger" class="loginmethod externalloginmethod">
            <h2><g:message code="nimble.template.login.blogger.heading" /></h2>
            <p>
              %{--<n:socialimg name="blogger" size="64" alt="Login using Blogger"/>--}%
              <g:message code="nimble.template.login.blogger.descriptive" />
            </p>
            <g:form controller="auth" action="bloggerreq">
              <label for="openiduri" class="append-1"><g:message code="nimble.template.login.blogger.identifier" /></label>
              <input id="openiduri" type="text" name="openiduri" class="easyinput"/>
              <fieldset>
                <button type="submit" class="button darkbutton icon icon_user_green"><g:message code="nimble.link.login.basic" /></button>
              </fieldset>
            </g:form>
          </div>

          <div id="wordpress" class="loginmethod externalloginmethod">
            <h2><g:message code="nimble.template.login.wordpress.heading" /></h2>
            <p>
              %{--<n:socialimg name="wordpress" size="64" alt="Login using Wordpress"/>--}%
              <g:message code="nimble.template.login.wordpress.descriptive" />
            </p>
            <g:form controller="auth" action="wordpressreq">
              <label for="openiduri" class="append-1"><g:message code="nimble.template.login.wordpress.identifier" /></label>
              <input id="openiduri" type="text" name="openiduri" class="easyinput"/>
              <fieldset>
                <button type="submit" class="button darkbutton icon icon_user_green"><g:message code="nimble.link.login.basic" /></button>
              </fieldset>
            </g:form>
          </div>

          <div id="technorati" class="loginmethod externalloginmethod">
            <h2><g:message code="nimble.template.login.technorati.heading" /></h2>
            <p>
              %{--<n:socialimg name="technorati" size="64" alt="Login using Technorati"/>--}%
              <g:message code="nimble.template.login.technorati.descriptive" />
            </p>
            <g:form controller="auth" action="technoratireq">
              <label for="openiduri" class="append-1"><g:message code="nimble.template.login.technorati.identifier" /></label>
              <input id="openiduri" type="text" name="technoratiusername" class="easyinput"/>
              <fieldset>
                <button type="submit" class="button darkbutton icon icon_user_green"><g:message code="nimble.link.login.basic" /></button>
              </fieldset>
            </g:form>
          </div>

          <div id="flickr" class="loginmethod externalloginmethod">
            <h2><g:message code="nimble.template.login.flickr.heading" /></h2>
            <p>
              %{--<n:socialimg name="flickr" size="64" alt="Login using Flickr"/>--}%
              <g:message code="nimble.template.login.flickr.descriptive" />
            </p>
            <p>
              <a href="${createLink(controller: "auth", action: "flickreq")}" class="button icon icon_user_green"><g:message code="nimble.link.login.basic" /></a>
            </p>
          </div>--%>
        </shiro:isNotLoggedIn>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        <div id="grailsLogo"><a href="http://grails.org"><img src="${resource(dir:'images',file:'grails_logo.png')}" alt="Grails" border="0" /></a></div>
        <g:layoutBody />
        <div id="versionInfo">
             <table>
               <tr><td>Build</td><td><g:meta name="system.build"/></td></tr>
               <tr><td>Branch</td><td><g:meta name="system.branch"/></td></tr>
             </table>
        </div>
    </body>
</html>
