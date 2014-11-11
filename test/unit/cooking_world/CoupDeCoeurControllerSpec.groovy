package cooking_world

@SuppressWarnings('GrailsMassAssignment')
@SuppressWarnings('NoWildcardImports')
import grails.test.mixin.*
import spock.lang.*

@TestFor(CoupDeCoeurController)
@Mock(CoupDeCoeur)
class CoupDeCoeurControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        params["dateCoupDeCoeur"] = new Date()
        params["utilisateur.id"] = 1
        params["recette.id"] = 1
    }

    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.coupDeCoeurInstanceList
        model.coupDeCoeurInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.coupDeCoeurInstance != null
    }

    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        def coupDeCoeur = new CoupDeCoeur()
        coupDeCoeur.validate()
        controller.save(coupDeCoeur)

        then: "The create view is rendered again with the correct model"
        model.coupDeCoeurInstance != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        coupDeCoeur = new CoupDeCoeur(params)

        controller.save(coupDeCoeur)

        then: "A redirect is issued to the show action"
        response.redirectedUrl == '/coupDeCoeur/show/1'
        controller.flash.message != null
        CoupDeCoeur.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def coupDeCoeur = new CoupDeCoeur(params)
        controller.show(coupDeCoeur)

        then: "A model is populated containing the domain instance"
        model.coupDeCoeurInstance == coupDeCoeur
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def coupDeCoeur = new CoupDeCoeur(params)
        controller.edit(coupDeCoeur)

        then: "A model is populated containing the domain instance"
        model.coupDeCoeurInstance == coupDeCoeur
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/coupDeCoeur/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def coupDeCoeur = new CoupDeCoeur()
        coupDeCoeur.validate()
        controller.update(coupDeCoeur)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.coupDeCoeurInstance == coupDeCoeur

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        coupDeCoeur = new CoupDeCoeur(params).save(flush: true)
        controller.update(coupDeCoeur)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/coupDeCoeur/show/$coupDeCoeur.id"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/coupDeCoeur/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def coupDeCoeur = new CoupDeCoeur(params).save(flush: true)

        then: "It exists"
        CoupDeCoeur.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.delete(coupDeCoeur)

        then: "The instance is deleted"
        CoupDeCoeur.count() == 0
        response.redirectedUrl == '/coupDeCoeur/index'
        flash.message != null
    }
}
