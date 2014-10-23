package cooking_world



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RecetteController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Recette.list(params), model:[recetteInstanceCount: Recette.count()]
    }

    def show(Recette recetteInstance) {
        respond recetteInstance
    }

    def create() {
        respond new Recette(params)
    }

    @Transactional
    def save(Recette recetteInstance) {
        if (recetteInstance == null) {
            notFound()
            return
        }

        if (recetteInstance.hasErrors()) {
            respond recetteInstance.errors, view:'create'
            return
        }

        recetteInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'recette.label', default: 'Recette'), recetteInstance.id])
                redirect recetteInstance
            }
            '*' { respond recetteInstance, [status: CREATED] }
        }
    }

    def edit(Recette recetteInstance) {
        respond recetteInstance
    }

    @Transactional
    def update(Recette recetteInstance) {
        if (recetteInstance == null) {
            notFound()
            return
        }

        if (recetteInstance.hasErrors()) {
            respond recetteInstance.errors, view:'edit'
            return
        }

        recetteInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Recette.label', default: 'Recette'), recetteInstance.id])
                redirect recetteInstance
            }
            '*'{ respond recetteInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Recette recetteInstance) {

        if (recetteInstance == null) {
            notFound()
            return
        }

        recetteInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Recette.label', default: 'Recette'), recetteInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'recette.label', default: 'Recette'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
