package devooght

import uk.co.devooght.front.Basket
import org.springframework.web.context.request.RequestContextHolder as RCH
import javax.servlet.http.Cookie

class ShopFilters {

  def filters = {
    loadBasket(controller: '*', action: '*') {
      before = {
        request.basket = getBasket()
      }
    }
  }

  def getBasket() {
    def cookies = RCH.requestAttributes.getCurrentRequest().getCookies()
    Cookie foundCookie = cookies.find { it.getName().equals(Basket.BASKET_COOKIE_NAME) }

    def basket = null
    if (foundCookie) {
      basket = Basket.findById(foundCookie.value)
    }
    if (!basket) {
      basket = new Basket().save(flush:true)
    }
    return basket
  }
}
