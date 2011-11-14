package uk.co.devooght

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.UsernamePasswordToken

import org.apache.shiro.web.util.WebUtils
import uk.co.devooght.auth.FacebookToken
import org.openid4java.message.ParameterList

class AuthController {
    def shiroSecurityManager
    def facebookGraphService
    def openIDService

    def index = { redirect(action: "login", params: params) }

    def login = {
      println "Wibble"
      log.debug("Doing something here")

        return [ username: params.username, rememberMe: (params.rememberMe != null), targetUri: params.targetUri ]
    }

    def facebookSignin = {
        try {
          facebookGraphService.getFacebookProfile()
          def authToken = new FacebookToken()
          params.rememberMe = false
          params.username = "facebook"
          params.password = "randompassword"

          // If a controller redirected to this page, redirect back
          // to it. Otherwise redirect to the root URI.
          def targetUri = params.targetUri ?: "/"

          // Handle requests saved by Shiro filters.
          def savedRequest = WebUtils.getSavedRequest(request)
          if (savedRequest) {
              targetUri = savedRequest.requestURI - request.contextPath
              if (savedRequest.queryString) targetUri = targetUri + '?' + savedRequest.queryString
          }

          try{
              // Perform the actual login. An AuthenticationException
              // will be thrown if the username is unrecognised or the
              // password is incorrect.
              SecurityUtils.subject.login(authToken)

              log.info "Redirecting to '${targetUri}'."
              redirect(uri: targetUri)
          }
          catch (AuthenticationException ex){
              // Authentication failed, so display the appropriate message
              // on the login page.
              log.info "Login via facebook failed"
              flash.message = message(code: "login.failed")

              // Remember the target URI too.
              if (params.targetUri) {
                  m["targetUri"] = params.targetUri
              }

              // Now redirect back to the login page.
              redirect(action: "login", params: m)
          }

        } catch (Exception e) {
          flash.error ="We are sorry. Please try again in a while."
          redirect(controller: 'home', action: 'index')
          return
        }

    }

    def signIn = {

        def authToken = new UsernamePasswordToken(params.username, params.password as String)

        // Support for "remember me"
        if (params.rememberMe) {
            authToken.rememberMe = true
        }

        // If a controller redirected to this page, redirect back
        // to it. Otherwise redirect to the root URI.
        def targetUri = params.targetUri ?: "/"
        
        // Handle requests saved by Shiro filters.
        def savedRequest = WebUtils.getSavedRequest(request)
        if (savedRequest) {
            targetUri = savedRequest.requestURI - request.contextPath
            if (savedRequest.queryString) targetUri = targetUri + '?' + savedRequest.queryString
        }
        
        try{
            // Perform the actual login. An AuthenticationException
            // will be thrown if the username is unrecognised or the
            // password is incorrect.
            SecurityUtils.subject.login(authToken)

            log.info "Redirecting to '${targetUri}'."
            redirect(uri: targetUri)
        }
        catch (AuthenticationException ex){
            // Authentication failed, so display the appropriate message
            // on the login page.
            log.info "Authentication failure for user '${params.username}'."
            flash.message = message(code: "login.failed")

            // Keep the username and "remember me" setting so that the
            // user doesn't have to enter them again.
            def m = [ username: params.username ]
            if (params.rememberMe) {
                m["rememberMe"] = true
            }

            // Remember the target URI too.
            if (params.targetUri) {
                m["targetUri"] = params.targetUri
            }

            // Now redirect back to the login page.
            redirect(action: "login", params: m)
        }
    }

    def signOut = {
        // Log the user out of the application.
        SecurityUtils.subject?.logout()

        // For now, redirect back to the home page.
        redirect(uri: "/")
    }

    def unauthorized = {
        render "You do not have permission to access this page."
    }

/**
     * OpenID integration
     */
    def openidreq = {
        log.debug("Performing openidreq")
        performOpenIDRequest("openid", params, request, response)
    }

    def openidresp = {
        log.debug("Performing openidresp")
        processOpenIDResponse("openid", params, request, response)
    }

    def yahooreq = {
        log.debug("Performing yahooreq")
        performOpenIDRequest("yahoo", params, request, response)
    }

    def yahooresp = {
        log.debug("Performing yahooresp")
        processOpenIDResponse("yahoo", params, request, response)
    }

    def flickrreq = {
        log.debug("Performing flickrreq")
        performOpenIDRequest("flickr", params, request, response)
    }

    def flickrresp = {
        log.debug("Performing flickrresp")
        processOpenIDResponse("flickr", params, request, response)
    }

    def googlereq = {
        log.info("Performing googlereq")
        performOpenIDRequest("google", params, request, response)
    }

    def googleresp = {
        log.debug("Performing googleresp")
        processOpenIDResponse("google", params, request, response)
    }

    def bloggerreq = {
        log.debug("Performing bloggerreq")
        performOpenIDRequest("blogger", params, request, response)
    }

    def bloggerresp = {
        log.debug("Performing bloggerresp")
        processOpenIDResponse("blogger", params, request, response)
    }

    def wordpressreq = {
        log.debug("Performing wordpressreq")
        performOpenIDRequest("wordpress", params, request, response)
    }

    def wordpressresp = {
        log.debug("Performing wordpressresp")
        processOpenIDResponse("wordpress", params, request, response)
    }

    def technoratireq = {
        if (params.technoratiusername) {
            log.debug("Performing technoratireq for $params.technoratiusername")
            params.put('openiduri', 'http://technorati.com/people/technorati/' + params.technoratiusername)
            performOpenIDRequest("technorati", params, request, response)
        }
        else {
            log.debug("Erronous technoratireq no username")
            flash.type = 'error'
            flash.message = message(code: "nimble.login.openid.invalid.identifier")
            redirect(action: 'login', params: [active: 'technorati'])
        }
    }

    def technoratiresp = {
        log.debug("Performing technoratiresp")
        processOpenIDResponse("technorati", params, request, response)
    }


  private performOpenIDRequest = {service, params, request, response ->


        log.warn("Attempting to authenticate $service openID user")

        def serviceIdentifier
        def discovered
        def authRequest

        StringBuffer responseUrl = new StringBuffer(createLink(controller:"auth", action:"${service}resp", absolute: true))
        if (params.openiduri != null) {
            serviceIdentifier = params.openiduri
            (discovered, authRequest) = openIDService.establishRequest(serviceIdentifier, responseUrl.toString())
        }
        else {
            serviceIdentifier = service
            (discovered, authRequest) = openIDService.establishDiscoveryRequest(serviceIdentifier, responseUrl.toString())
        }

        if (discovered && authRequest) {

            log.warn("Successfully discovered details for openid service $serviceIdentifier redirecting client")

            session.setAttribute("discovered", discovered)
            if (!discovered.isVersion2()) {
                response.sendRedirect(authRequest.getDestinationUrl(true))
                return
            }

            render(view: "openidreq", model: [openidreqparams: authRequest.getParameterMap(), destination: authRequest.getDestinationUrl(false)])
        }
        else {
            log.warn("Unable to discover details for openid service $serviceIdentifier redirecting client")

            flash.type = 'error'
            flash.message = message(code: "nimble.login.openid.${service}.internal.error.req")
            redirect(action: 'login', params: [active: service])
        }
    }

    private processOpenIDResponse = {service, params, request, response ->

        def discovered = session.getAttribute("discovered")
        ParameterList openIDResponse = new ParameterList(request.getParameterMap());

        StringBuffer receivingUrl = new StringBuffer(createLink(action: "${service}resp", absolute: true))
        String queryString = request.getQueryString();
        if (queryString != null && queryString.length() > 0)
        receivingUrl.append("?").append(request.getQueryString());

        def authToken = openIDService.processResponse(discovered, openIDResponse, receivingUrl.toString())

        if (authToken) {
            try {
                SecurityUtils.subject.login(authToken)
                this.userService.createLoginRecord(request)

                def targetUri = session.getAttribute(AuthController.TARGET) ?: "/"
                session.removeAttribute(AuthController.TARGET)

                log.info("Authenticated openID user $authToken.userID. Directing to content $targetUri")
                redirect(uri: targetUri)
            }
            catch (AuthenticationException ex) {
                log.warn "OpenID authentication failure for $authToken.userID - ${ex.getLocalizedMessage()}"
                log.debug ex.printStackTrace()
                flash.type = 'error'
                flash.message = message(code: "nimble.login.openid.${service}.failed")
                redirect(action: 'login', params: [active: service])
            }
        }
        else {
            log.debug ("OpenID authentication failure")

            flash.type = 'error'
            flash.message = message(code: "nimble.login.openid.${service}.internal.error.res")
            redirect(action: 'login', params: [active: service])
        }
    }
}
