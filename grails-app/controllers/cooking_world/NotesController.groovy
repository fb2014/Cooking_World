package cooking_world


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class NotesController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Notes.list(params), model: [notesInstanceCount: Notes.count()]
    }

    def show(Notes notesInstance) {
        respond notesInstance
    }

    def create() {
        respond new Notes(params)
    }

    @Transactional
    def save(Notes notesInstance) {
        if (notesInstance == null) {
            notFound()
            return
        }

        if (notesInstance.hasErrors()) {
            respond notesInstance.errors, view: 'create'
            return
        }

        notesInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'notes.label', default: 'Notes'), notesInstance.id])
                redirect notesInstance
            }
            '*' { respond notesInstance, [status: CREATED] }
        }
    }

    def edit(Notes notesInstance) {
        respond notesInstance
    }

    @Transactional
    def update(Notes notesInstance) {
        if (notesInstance == null) {
            notFound()
            return
        }

        if (notesInstance.hasErrors()) {
            respond notesInstance.errors, view: 'edit'
            return
        }

        notesInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Notes.label', default: 'Notes'), notesInstance.id])
                redirect notesInstance
            }
            '*' { respond notesInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Notes notesInstance) {

        if (notesInstance == null) {
            notFound()
            return
        }

        notesInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Notes.label', default: 'Notes'), notesInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'notes.label', default: 'Notes'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
