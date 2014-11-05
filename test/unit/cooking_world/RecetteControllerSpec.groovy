package cooking_world


import grails.test.mixin.*
import spock.lang.*

@TestFor(RecetteController)
@Mock(Recette)
class RecetteControllerSpec extends Specification {

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
        response.redirectedUrl == '/recette/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def recette = new Recette(params).save(flush: true)

        then: "It exists"
        Recette.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.delete(recette)

        then: "The instance is deleted"
        Recette.count() == 0
        response.redirectedUrl == '/recette/index'
        flash.message != null
    }
}
