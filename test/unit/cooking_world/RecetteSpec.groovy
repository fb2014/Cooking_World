package cooking_world

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Recette)
class RecetteSpec extends Specification {

    Recette recette

    def setup() {
        recette = new Recette()
    }

    def cleanup() {
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
        "Titre" | ""       | "ingredient, ingredient" | "description" | 10         | 15           | new Date('10/10/2010') | true
        "Titre" | "photo"  | ""                       | "description" | 10         | 15           | new Date('10/10/2010') | false
        "Titre" | "photo"  | "ingredient, ingredient" | ""            | 10         | 15           | new Date('10/10/2010') | false
        "Titre" | "photo"  | "ingredient, ingredient" | "description" | 0          | 15           | new Date('10/10/2010') | false
        "Titre" | "photo"  | "ingredient, ingredient" | "description" | 10         | -1           | new Date('10/10/2010') | false
        "Titre" | "photo"  | "ingredient, ingredient" | "description" | 10         | 15           | new Date('10/10/2010') | true

    }


    @Unroll
    void "test d'une recette avec toutes les informations necessaires"() {
        given: "une recette complète"
        recette.titre = unTitre
        recette.filename = unePhoto
        recette.ingredients = desIngredients
        recette.description = uneDesc
        recette.tempsPreparation = unTpsPrepa
        recette.tempsCuisson = unTpsCuisson
        recette.dateCreation = uneDateCreation

        when: "la recette est entièrement validée"

        def valid = ((recette.titre) && (recette.ingredients) && (recette.description) && (recette.tempsPreparation > 0)
                && (recette.tempsCuisson > 0) && (recette.dateCreation))


        then: "les vérifications de contraintes sont appliquées correctement"
        valid == etatAttendu

        where:
        unTitre | unePhoto | desIngredients           | uneDesc       | unTpsPrepa | unTpsCuisson | uneDateCreation        | etatAttendu
        ""      | "photo"  | "ingredient, ingredient" | "description" | 10         | 15           | new Date('10/10/2010') | false
        "Titre" | ""       | "ingredient, ingredient" | "description" | 10         | 15           | new Date('10/10/2010') | true
        "Titre" | "photo"  | ""                       | "description" | 10         | 15           | new Date('10/10/2010') | false
        "Titre" | "photo"  | "ingredient, ingredient" | ""            | 10         | 15           | new Date('10/10/2010') | false
        "Titre" | "photo"  | "ingredient, ingredient" | "description" | 0          | 15           | new Date('10/10/2010') | false
        "Titre" | "photo"  | "ingredient, ingredient" | "description" | 10         | -1           | new Date('10/10/2010') | false
        "Titre" | "photo"  | "ingredient, ingredient" | "description" | 10         | 15           | new Date('10/10/2010') | true
        "Titre" | "photo"  | "ingredient, ingredient" | "description" | 10         | 15           | new Date('10/10/2010') | true

    }
}