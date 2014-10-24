package cooking_world


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CoupDeCoeurController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond CoupDeCoeur.list(params), model: [coupDeCoeurInstanceCount: CoupDeCoeur.count()]
    }

    def show(CoupDeCoeur coupDeCoeurInstance) {
        respond coupDeCoeurInstance
    }

    def create() {
        respond new CoupDeCoeur(params)
    }

    @Transactional
    def save(CoupDeCoeur coupDeCoeurInstance) {
        if (coupDeCoeurInstance == null) {
            notFound()
            return
        }

        if (coupDeCoeurInstance.hasErrors()) {
            respond coupDeCoeurInstance.errors, view: 'create'
            return
        }

        coupDeCoeurInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'coupDeCoeur.label', default: 'CoupDeCoeur'), coupDeCoeurInstance.id])
                redirect coupDeCoeurInstance
            }
            '*' { respond coupDeCoeurInstance, [status: CREATED] }
        }
    }

    def edit(CoupDeCoeur coupDeCoeurInstance) {
        respond coupDeCoeurInstance
    }

    @Transactional
    def update(CoupDeCoeur coupDeCoeurInstance) {
        if (coupDeCoeurInstance == null) {
            notFound()
            return
        }

        if (coupDeCoeurInstance.hasErrors()) {
            respond coupDeCoeurInstance.errors, view: 'edit'
            return
        }

        coupDeCoeurInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'CoupDeCoeur.label', default: 'CoupDeCoeur'), coupDeCoeurInstance.id])
                redirect coupDeCoeurInstance
            }
            '*' { respond coupDeCoeurInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(CoupDeCoeur coupDeCoeurInstance) {

        if (coupDeCoeurInstance == null) {
            notFound()
            return
        }

        coupDeCoeurInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'CoupDeCoeur.label', default: 'CoupDeCoeur'), coupDeCoeurInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'coupDeCoeur.label', default: 'CoupDeCoeur'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
