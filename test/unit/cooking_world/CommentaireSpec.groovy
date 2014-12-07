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
        commentaire.dateCom = uneDate

        when: "le commentaire est valide"
        def valid = commentaire.validate()

        then: "les verifications des contraintes sont appliquées correctement"
        valid == etatAttendu

        where:
        unMessage | uneRecette    | unUtilisateur     | uneDate                | etatAttendu
        "Hello."  | Mock(Recette) | Mock(Utilisateur) | new Date("10/10/2014") | true
        "Hello."  | Mock(Recette) | Mock(Utilisateur) | new Date()             | true
        "Hello."  | null          | Mock(Utilisateur) | new Date("10/10/2014") | false
        "Hello."  | Mock(Recette) | null              | new Date("10/10/2014") | false
        null      | Mock(Recette) | Mock(Utilisateur) | new Date("10/10/2014") | false
        ""        | Mock(Recette) | Mock(Utilisateur) | new Date("10/10/2014") | false
        "Hello."  | Mock(Recette) | Mock(Utilisateur) | null                   | false


    }

    void "test toString() "() {
        given: "un commentaire"
        commentaire.message = "unCommentaire"

        when: "on appel la fonction toString()"
        def monCommentaire = commentaire.toString()

        then: "on a bien le message du commentaire"
        monCommentaire == commentaire.message
    }
}
