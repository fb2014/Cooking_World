package cooking_world

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Commentaire)
@Mock([Utilisateur, Recette])
class CommentaireSpec extends Specification {

    Commentaire commentaire

    def setup() {
        commentaire = new Commentaire()
    }


    @Unroll
    void "test sur les contraintes d'un commentaire valide"() {
        given: "un commentaire"
        commentaire.message = unMessage
        commentaire.recette = uneRecette
        commentaire.utilisateur = unUtilisateur

        when: "le commentaire est valide"
        def valid = commentaire.validate()

        then: "les verifications des contraintes sont appliquées correctement"
        valid == etatAttendu

        where:
        unMessage | uneRecette    | unUtilisateur     | etatAttendu
        "Hello."  | Mock(Recette) | Mock(Utilisateur) | true
        "Hello."  | null          | Mock(Utilisateur) | false
        "Hello."  | Mock(Recette) | null              | false
        null      | Mock(Recette) | Mock(Utilisateur) | false
        ""        | Mock(Recette) | Mock(Utilisateur) | false


    }
}
