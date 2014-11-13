package cooking_world


@SuppressWarnings('NoWildcardImports')
@SuppressWarnings('GrailsMassAssignment')
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
/* create et update en fonction du user connecté ok
    supprimer toutes les lignes avec marianetest le moment venu
 */
@Transactional(readOnly = true)
class RecetteController {

    static allowedMethods = [save: "POST", update: ["PUT","POST"], delete: "DELETE"]

    UploadImageService uploadImageService
    ApprecierRecetteService apprecierRecetteService

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
                //redirect action:"index", method:"GET"
                redirect(controller : "Utilisateur", action: "show", id: session.utilisateur.id)
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
         def currentUser=Utilisateur.get(session.utilisateur.id)

         // Get the avatar file
         def uploadedFile = request.getFile('photo')

         uploadImageService.uploadImage(currentUser.pseudo,uploadedFile,recetteInstance)
         //uploadImageService.uploadImage("marianetest",uploadedFile,recetteInstance)
         //recuperer la date courante et l'utilsateur courant
         def currentDate=new Date()
         recetteInstance.dateCreation=currentDate
         recetteInstance.utilisateur=currentUser

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
    //ajouter la note à la recette
    def addNote(Recette  recetteInstance ){
        def notegout=request.getParameter("gout").toInteger()
        def noteclarte=request.getParameter("clarte").toInteger()
        def notesimplicite=request.getParameter("simplicite").toInteger()

        def currentUser
        def userExist=session['utilisateur']
        if (userExist==null){
            currentUser=Utilisateur.findByPseudo('Anonyme')
        }
        else{
            //recuperer le user connecté
            currentUser=Utilisateur.get(session.utilisateur.id)

        }
        apprecierRecetteService.noterRecette(recetteInstance,currentUser,notegout,noteclarte,notesimplicite)

        request.withFormat {
            form multipartForm {
                redirect recetteInstance
            }
            '*' { respond recetteInstance, [status: OK] }
        }


    }

    //ajouter un coup de coeur à une recette
    def addCoupDeCoeur(Recette recetteInstance){
        if (!(request.getParameter("valImg")).equals("")) {//le user a eu un coup de coeur pour la recette

            def currentUser
            def userExist = session['utilisateur']
            if (userExist == null) {
                currentUser = Utilisateur.findByPseudo('Anonyme')
            }
            else {
                //recuperer le user connecté
                currentUser = Utilisateur.get(session.utilisateur.id)

            }
            apprecierRecetteService.donnerCoupdecoeur(recetteInstance, currentUser)
        }
        request.withFormat {
            form multipartForm {
                redirect recetteInstance
            }
            '*' { respond recetteInstance, [status: OK] }
        }
    }

}