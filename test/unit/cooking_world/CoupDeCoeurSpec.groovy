package cooking_world

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(CoupDeCoeur)
@Mock([Utilisateur, Recette])
class CoupDeCoeurSpec extends Specification {

    CoupDeCoeur coupDeCoeur

    def setup() {
        coupDeCoeur = new CoupDeCoeur()
    }

    def cleanup() {
    }

    @Unroll
    void "test sur les contraintes d'un coup de coeur valide"() {
        given: "un coup de coeur"
        coupDeCoeur.date = uneDate
        coupDeCoeur.recette = uneRecette
        coupDeCoeur.utilisateur = unUtilisateur

        when: "le coup de coeur est validé"
        def valid = coupDeCoeur.validate()

        then: "les verifications des contraintes sont appliquées correctement"
        valid == etatAttendu

        where:
        uneDate                | unUtilisateur     | uneRecette    | etatAttendu
        new Date("10/10/2014") | Mock(Utilisateur) | Mock(Recette) | true
        new Date("10/10/2014") | null              | null          | false
        new Date("10/10/2014") | null              | Mock(Recette) | false
        new Date("10/10/2014") | Mock(Utilisateur) | null          | false
        new Date()             | Mock(Utilisateur) | Mock(Recette) | true
        new Date(2014, 10, 10) | Mock(Utilisateur) | Mock(Recette) | true
        null                   | Mock(Utilisateur) | Mock(Recette) | false

    }
}
