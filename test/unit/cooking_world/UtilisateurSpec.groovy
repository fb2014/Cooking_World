package cooking_world

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Utilisateur)
class UtilisateurSpec extends Specification {

    Utilisateur utilisateur
    def setup() {
         utilisateur = new Utilisateur()
    }

    def cleanup() {
    }

    void "test sur les contraintes d'un utilisateur valide"() {
        given: "un utilisateur"
        utilisateur.pseudo = unPseudo
        utilisateur.motDePasse = unMotDePasse

        when: "l'utilisateur est validée"
        def valid = utilisateur.validate()

        then: "les vérifications de contraintes sont appliquées correctement"
        valid == etatAttendu

        where:
        unPseudo | unMotDePasse | etatAttendu
        ""  | "un mdp" | false
        null  | "un mdp" | false
        "pseudo1" | null | false
        "pseudo2" | "" | false
        "pseudo3" | "un mdp" | true

    }
}
