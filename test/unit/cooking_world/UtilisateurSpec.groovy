package cooking_world

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Utilisateur)
@Mock([Recette,Notes,Commentaire])
class UtilisateurSpec extends Specification {

    Utilisateur utilisateur

    def setup() {
        utilisateur = new Utilisateur()
    }

    @Unroll
    void "test sur les contraintes d'un utilisateur valide"() {
        given: "un utilisateur"
        utilisateur.pseudo = unPseudo
        utilisateur.motDePasse = unMotDePasse

        when: "l'utilisateur est validée"
        def valid = utilisateur.validate()

        then: "les vérifications de contraintes sont appliquées correctement"
        valid == etatAttendu

        where:
        unPseudo  | unMotDePasse | etatAttendu
        ""        | "un mdp"     | false
        null      | "un mdp"     | false
        "pseudo1" | null         | false
        "pseudo2" | ""           | false
        "pseudo3" | "un mdp"     | true

    }
    void "test d'un utilisateur avec une recette"() {
        given: "une recette"
        def recette = Mock(Recette)
        when: "la recette est associée  l'utilisateur"
        utilisateur.addToRecette(recette)
        then: "l'utilisateur a publié une nouvelle recette"
        utilisateur.getRecette().size() == 1
    }

    void "test d'un utilisateur avec une note "() {
        given: "une note "
        def note = Mock(Notes)
        when: " l'utilisateur note une recette"
        utilisateur.addToNotes(note)
        then: "l'utilisateur a noté une nouvelle recette"
        utilisateur.getNotes().size() == 1
    }
    void "test ajouter un commentaire  "() {
        given: "un commentaire "
        def commentaire = Mock(Commentaire)
        when: " l'utilisateur soumet un commentaire"
        utilisateur.addToCommentaire(commentaire)
        then: "le commentaire est enregistrée"
        utilisateur.getCommentaire().size() == 1
    }

    void "test toString() "() {
        given: "un utilisateur"
        utilisateur.pseudo = "unPseudo"
        utilisateur.motDePasse = "unMotDePasse"

        when: "on appel la fonction toString()"
        def monUtilisateur = utilisateur.toString()

        then: "on a bien le pseudo de l'utilisateur"
        monUtilisateur == utilisateur.pseudo
    }
}
