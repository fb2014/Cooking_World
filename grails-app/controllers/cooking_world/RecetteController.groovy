package cooking_world



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
/* create et update en fonction du user connecté ok
    supprimer toutes les lignes avec marianetest le moment venu
 */
@Transactional(readOnly = true)
class RecetteController {

    static allowedMethods = [save: "POST", update: ["PUT","POST"], delete: "DELETE"]
    //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    UploadImageService uploadImageService

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

        //supprimer la photo du repertoire
        if(!recetteInstance.filename.equals("default.jpg")) {
            //recuperer le user connecté
            //def currentUser=Utilisateur.get(session.utilisateur.id)
            def webRootDir = servletContext.getRealPath("/")
            File maphoto = new File(webRootDir, "/images/UsersImages/marianetest/" + recetteInstance.filename)
            //File maphoto = new  File(webRootDir,"/images/UsersImages/"+currentUser.pseudo+"/"+recetteInstance.filename)
            maphoto.delete()
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

     def upload(Recette  recetteInstance ){

         //recuperer le user connecté
         //def currentUser=Utilisateur.get(session.utilisateur.id)
         // Get the avatar file
         def uploadedFile = request.getFile('photo')

         //uploadImageService.uploadImage(currentUser.pseudo,uploadedFile,recetteInstance)
         uploadImageService.uploadImage("marianetest",uploadedFile,recetteInstance)
         recetteInstance.save flush:true


         request.withFormat {
             form multipartForm {
                 flash.message = message(code: 'default.created.message', args: [message(code: 'recette.label', default: 'Recette'), recetteInstance.id])
                 redirect recetteInstance
             }
             '*' { respond recetteInstance, [status: CREATED] }
         }
     }

    def showPayload() {
        def recetteInstance = Recette.get(params.id)
        response.outputStream << recetteInstance.photo // write the image to the outputstream
        response.outputStream.flush()
    }

    def updateImg(Recette recetteInstance){
        def uploadedFile = request.getFile('photo')
        //recuperer le user connecté
        //def currentUser=Utilisateur.get(session.utilisateur.id)
        //uploadImageService.updateImage(currentUser.pseudo,uploadedFile,recetteInstance)
        uploadImageService.updateImage("marianetest",uploadedFile,recetteInstance)
        recetteInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Recette.label', default: 'Recette'), recetteInstance.id])
                redirect recetteInstance
            }
            '*'{ respond recetteInstance, [status: OK] }
        }


    }
}