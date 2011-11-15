package uk.co.devooght.stock

class SkuController {

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  def index = {
    redirect(action: "list", params: params)
  }

  def list = {
    params.max = Math.min(params.max ? params.int('max') : 10, 100)
    [skuInstanceList: Sku.list(params), skuInstanceTotal: Sku.count()]
  }

  def create = {
    def skuInstance = new Sku()
    skuInstance.properties = params
    return [skuInstance: skuInstance]
  }

  def save = {
    def skuInstance = new Sku(params)
    if (skuInstance.save(flush: true)) {
      flash.message = "${message(code: 'default.created.message', args: [message(code: 'sku.label', default: 'Sku'), skuInstance.id])}"
      redirect(action: "show", id: skuInstance.id)
    }
    else {
      render(view: "create", model: [skuInstance: skuInstance])
    }
  }

  def show = {
    def skuInstance = Sku.get(params.id)
    if (!skuInstance) {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sku.label', default: 'Sku'), params.id])}"
      redirect(action: "list")
    }
    else {
      [skuInstance: skuInstance]
    }
  }

  def edit = {
    def skuInstance = Sku.get(params.id)
    if (!skuInstance) {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sku.label', default: 'Sku'), params.id])}"
      redirect(action: "list")
    }
    else {
      return [skuInstance: skuInstance]
    }
  }

  def update = {
    def skuInstance = Sku.get(params.id)
    if (skuInstance) {
      if (params.version) {
        def version = params.version.toLong()
        if (skuInstance.version > version) {

          skuInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'sku.label', default: 'Sku')] as Object[], "Another user has updated this Sku while you were editing")
          render(view: "edit", model: [skuInstance: skuInstance])
          return
        }
      }
      skuInstance.properties = params
      if (!skuInstance.hasErrors() && skuInstance.save(flush: true)) {
        flash.message = "${message(code: 'default.updated.message', args: [message(code: 'sku.label', default: 'Sku'), skuInstance.id])}"
        redirect(action: "show", id: skuInstance.id)
      }
      else {
        render(view: "edit", model: [skuInstance: skuInstance])
      }
    }
    else {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sku.label', default: 'Sku'), params.id])}"
      redirect(action: "list")
    }
  }

  def delete = {
    def skuInstance = Sku.get(params.id)
    if (skuInstance) {
      try {
        skuInstance.delete(flush: true)
        flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'sku.label', default: 'Sku'), params.id])}"
        redirect(action: "list")
      }
      catch (org.springframework.dao.DataIntegrityViolationException e) {
        flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'sku.label', default: 'Sku'), params.id])}"
        redirect(action: "show", id: params.id)
      }
    }
    else {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sku.label', default: 'Sku'), params.id])}"
      redirect(action: "list")
    }
  }
}
