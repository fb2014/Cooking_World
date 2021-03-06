package cooking_world

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Recette)
@Mock([Utilisateur,Notes,Commentaire])
class RecetteSpec extends Specification {

    Recette recette
    Utilisateur mon_user
    Notes mes_notes

    def setup() {
        recette = new Recette()
        mon_user=Mock(Utilisateur)
        mes_notes=Mock(Notes)
    }

    @Unroll
    void "test sur les contraintes d'une recette valide"() {
        given: "une recette"
        recette.titre = unTitre
        recette.filename = unePhoto
        recette.ingredients = desIngredients
        recette.description = uneDesc
        recette.tempsPreparation = unTpsPrepa
        recette.tempsCuisson = unTpsCuisson
        recette.dateCreation = uneDateCreation

        when: "la recette est validée"
        def valid = ((recette.titre) && (recette.ingredients) && (recette.description) && (recette.tempsPreparation > 0)
                && (recette.tempsCuisson > 0) && (recette.dateCreation))

        then: "les vérifications de contraintes sont appliquées correctement"
        valid == etatAttendu

        where:
        unTitre | unePhoto | desIngredients           | uneDesc       | unTpsPrepa | unTpsCuisson | uneDateCreation        | etatAttendu
        ""      | "photo"  | "ingredient, ingredient" | "description" | 10         | 15           | new Date('10/10/2010') | false
        null    | "photo"  | "ingredient, ingredient" | "description" | 10         | 15           | new Date('10/10/2010') | false
        "Titre" | ""       | "ingredient, ingredient" | "description" | 10         | 15           | new Date('10/10/2010') | true
        "Titre" | "photo"  | ""                       | "description" | 10         | 15           | new Date('10/10/2010') | false
        "Titre" | "photo"  | "ingredient, ingredient" | ""            | 10         | 15           | new Date('10/10/2010') | false
        "Titre" | "photo"  | "ingredient, ingredient" | "description" | 0          | 15           | new Date('10/10/2010') | false
        "Titre" | "photo"  | "ingredient, ingredient" | "description" | 10         | -1           | new Date('10/10/2010') | false
        "Titre" | "photo"  | "ingredient, ingredient" | "description" | 10         | 15           | new Date('10/10/2010') | true

    }


    void "test une recette avec une note"(){
        given:"une recette à noter"
        recette.titre = "ma recette"
        recette.filename = "ma photo"
        recette.ingredients = "mes ingredients"
        recette.description = " description"
        recette.tempsPreparation = 10
        recette.tempsCuisson = 3
        recette.dateCreation = new Date('10/10/2010')

        when:"on associe une note à la recette"
        mes_notes.clarte >> 2
        mes_notes.simplicite >> 4
        mes_notes.gout >> 0
        recette.addToNotes(mes_notes)

        then: "on a une note de plus pour la recette"
        recette.getNotes().size()==1
        recette.getNotes()[0].getClarte()==2

    }
    void "test ajouter un commentaire à une recette  "() {
        given: "un commentaire "
        def commentaire = Mock(Commentaire)
        when: " la recette est commentée"
        recette.addToCommentaire(commentaire)
        then: "le commentaire est enregistrée"
       recette.getCommentaire().size() == 1
    }

    void "test toString() "() {
        given: "une recette"
        recette.titre = "ma recette"
        recette.filename = "ma photo"
        recette.ingredients = "mes ingredients"
        recette.description = " description"
        recette.tempsPreparation = 10
        recette.tempsCuisson = 3
        recette.dateCreation = new Date('10/10/2010')

        when: "on appel la fonction toString()"
        def maRecette = recette.toString()

        then: "on a bien le toString attendu de la recette"
        maRecette == recette.titre + "\n Temps de préparation : "+recette.tempsPreparation+ "\n Temps de cuisson : "+recette.tempsCuisson + "\n Ingrédients :" + recette.ingredients + "\n"+recette.description

    }
}