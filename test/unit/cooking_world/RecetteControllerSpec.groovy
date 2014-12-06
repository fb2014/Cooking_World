package cooking_world

@SuppressWarnings('GrailsMassAssignment')
@SuppressWarnings('NoWildcardImports')
import grails.test.mixin.*
import spock.lang.*
import org.codehaus.groovy.grails.plugins.testing.GrailsMockMultipartFile


@TestFor(RecetteController)
@Mock([Recette,Utilisateur,Notes,CoupDeCoeur])
class RecetteControllerSpec extends Specification {
    UploadImageService myServiceUploadImg=Mock(UploadImageService)
    ApprecierRecetteService apprecierRecetteService=Mock(ApprecierRecetteService)
    def populateValidParams(params) {
        assert params != null

        params["titre"] = 'untitre'
        params ["photo"] =[]
        params["ingredients"] = 'desingredients'
        params["description"] = 'unedescritpion'
        params["tempsPreparation"] = 2
        params["tempsCuisson"] = 2
        params["dateCreation"] = new Date('10/10/2010')
        params["fileName"] = 'fileName'
        params["utilisateur.id"] = 2



    }

    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.recetteInstanceList
        model.recetteInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.recetteInstance != null
    }

    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        def recette = new Recette()
        recette.validate()
        controller.save(recette)

        then: "The create view is rendered again with the correct model"
        model.recetteInstance != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        recette = new Recette(params)
        controller.save(recette)

        then: "A redirect is issued to the show action"
        response.redirectedUrl == '/recette/show/1'
        controller.flash.message != null
        Recette.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def recette = new Recette(params)
        controller.show(recette)

        then: "A model is populated containing the domain instance"
        model.recetteInstance == recette
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def recette = new Recette(params)
        controller.edit(recette)

        then: "A model is populated containing the domain instance"
        model.recetteInstance == recette
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/recette/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def recette = new Recette()
        recette.validate()
        controller.update(recette)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.recetteInstance == recette

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        recette = new Recette(params).save(flush: true)
        controller.update(recette)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/recette/show/$recette.id"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        controller.delete(null)

        then: "A 404 is returned"
        //response.redirectedUrl == '/recette/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def recette = new Recette(params).save(flush: true)

        then: "It exists"
        Recette.count() == 1

        when: "The domain instance is passed to the delete action"
        Utilisateur user = Mock(Utilisateur)
        user.pseudo = 'Jean'
        def session = request.getSession(true)
        session.setAttribute("utilisateur", user)

        controller.delete(recette)

        then: "The instance is deleted"
        Recette.count() == 0
        flash.message != null

    }

    def "test qu'on  a bien une image associée à la recette quand on fait appel à la fonction upload"(){

        given:"une recette"

        populateValidParams(params)
        def recette = new Recette(params)
        recette.utilisateur=new Utilisateur(pseudo: "Marc",motDePasse: "motdepasse")
        recette.utilisateur.id=2
        recette.save()

        def fileContentType =  "image/jpeg"
        def fileContentBytes = '123' as byte[]
        def file = new GrailsMockMultipartFile('photo', 'originalName.jpg', fileContentType, fileContentBytes)
        request.addFile file

        def session = request.getSession(true)
        session.setAttribute("utilisateur",recette.utilisateur)

        controller.uploadImageService=myServiceUploadImg

        when:"on enregistre la recette"
        controller.upload(recette)


        then:"on fait appel à la fonction uploadImage du service UploadImageService pour associer une image à la recette"
        response.redirectedUrl == "/recette/show/$recette.id"
        flash.message != null
        1 *myServiceUploadImg.uploadImage("Marc",file,recette)

    }
    def "test que l'image associée à la recette est mise à jour quand on fait appel à la fonction updateImg"(){

        given:"une recette"

        populateValidParams(params)
        def recette = new Recette(params)
        recette.utilisateur=new Utilisateur(pseudo: "Marc",motDePasse: "motdepasse")
        recette.utilisateur.id=2
        recette.save()

        def fileContentType =  "image/jpeg"
        def fileContentBytes = '123' as byte[]
        def file = new GrailsMockMultipartFile('photo', 'originalName.jpg', fileContentType, fileContentBytes)
        request.addFile file

        def session = request.getSession(true)
        session.setAttribute("utilisateur",recette.utilisateur)

        controller.uploadImageService=myServiceUploadImg

        when:"on effectue une mise à jour de la recette"
        controller.updateImg(recette)


        then:"on fait appel à la fonction updateImage du service UploadImageService pour associer mettre à jour l'image associée à la recette"
        response.redirectedUrl == "/recette/show/$recette.id"
        flash.message != null
        1 *myServiceUploadImg.updateImage("Marc",file,recette)

    }

    def "test qu'un utilisateur peut attribuer un coup de coeur à une recette quand on fait appel à la fonction addCoupDeCoeur"(){

        given:"une recette, un utilisateur et un coup de coeur"

        populateValidParams(params)
        def recette = new Recette(params)
        recette.utilisateur=new Utilisateur(pseudo: "Marc",motDePasse: "motdepasse")
        recette.utilisateur.id=2
        recette.save()


        Utilisateur mon_user = new Utilisateur()
        mon_user.pseudo = 'Jean'
        mon_user.id =1
        mon_user.motDePasse="mdp"
        mon_user.save()


        def session = request.getSession(true)
        session.setAttribute("utilisateur",mon_user)


        controller.request.addParameter("valImg","cdc")

        def mon_cdc = Mock(CoupDeCoeur)
        mon_cdc.dateCoupDeCoeur = new Date()
        mon_cdc.recette = recette
        mon_cdc.utilisateur = mon_user
        List<CoupDeCoeur> list = new ArrayList<CoupDeCoeur>();
        list.add(mon_cdc);

        mon_user.coupDeCoeur=new HashSet<CoupDeCoeur>(list);


        controller.apprecierRecetteService=apprecierRecetteService


        when:"on enregistre le coup de coeur"
        controller.addCoupDeCoeur(recette)


        then:"on fait appel à la fonction donnerCoupdecoeur du service ApprecierRecetteService pour associer un coup de coeur à la recette"

        1 *apprecierRecetteService.donnerCoupdecoeur(recette,mon_user)

    }

    def "test qu'un utilisateur peut attribuer une note à une recette quand on fait appel à la fonction addNote"(){

        given:"une recette, un utilisateur et des notes(clarté, gout, simplicité)"

        populateValidParams(params)
        def recette = new Recette(params)
        recette.utilisateur=new Utilisateur(pseudo: "Marc",motDePasse: "motdepasse")
        recette.utilisateur.id=2
        recette.save()


        Utilisateur mon_user = new Utilisateur()
        mon_user.pseudo = 'Jean'
        mon_user.id =1
        mon_user.motDePasse="mdp"
        mon_user.save()

        def mes_notes = Mock(Notes)
        mes_notes.clarte >> 2
        mes_notes.simplicite >> 4
        mes_notes.gout >> 0
        List<Notes> list = new ArrayList<Notes>();
        list.add(mes_notes);

        mon_user.notes=new HashSet<Notes>(list);

        def session = request.getSession(true)
        session.setAttribute("utilisateur",mon_user)



        def noteGout=1
        def noteClarte=2
        def noteSimplicite=3
        controller.request.addParameter("gout","1")
        controller.request.addParameter("clarte","2")
        controller.request.addParameter("simplicite","3")

        controller.apprecierRecetteService=apprecierRecetteService


        when:"on enregistre la note"
        controller.addNote(recette)


        then:"on fait appel à la fonction noterRecette du service ApprecierRecetteService pour associer une note à la recette"

        1 *apprecierRecetteService.noterRecette(recette,mon_user,noteGout,noteClarte,noteSimplicite)

    }
}
