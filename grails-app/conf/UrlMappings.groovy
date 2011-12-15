class UrlMappings {

	static mappings = {

    "/col/$id/$name"(controller:"home", action:"collection")
    "/p/$id/$name"(controller:"home", action:"product")
    "/basket"(controller:"home", action:"basket")
    "/basket/add"(controller:"basket", action:"add")
    "/basket/remove"(controller:"basket", action:"remove")
    "/basket/update"(controller:"basket", action:"update")
    "/basket/clear"(controller:"basket", action:"clear")
    "/stock"(controller:"stock", action:"index")
    "/stock/upload"(controller:"stock", action:"upload")

    "/auth/login"(controller:"auth", action:"login")
    "/auth/facebookSignin"(controller:"auth", action:"facebookSignin")

		"/"(controller:"home", action:"index")
		"500"(view:'/error')
	}
}
