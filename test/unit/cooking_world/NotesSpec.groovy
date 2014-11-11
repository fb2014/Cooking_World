package cooking_world

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Notes)
@Mock([Utilisateur, Recette])

class NotesSpec extends Specification {

    Notes notes

    def setup() {
        notes = new Notes()
    }

    @Unroll
    void "test sur les contraintes d'une note valide"() {
        given: "une note"
        notes.clarte = uneNoteClarte
        notes.gout = uneNoteGout
        notes.simplicite = uneNoteSimplicite
        notes.utilisateur = unUtilisateur
        notes.recette = uneRecette

        when: "la note est validée"
        def valid = notes.validate()//(((notes.clarte>=0 && (notes.clarte<=5)) && ((notes.gout>=0) &&(notes.gout<=5)) && ((notes.simplicite>=0)&& (notes.simplicite<=5))))

        then: "les vérifications de contraintes sont appliquées correctement"
        valid == etatAttendu

        where:
        uneNoteClarte | uneNoteGout | uneNoteSimplicite | unUtilisateur     | uneRecette    | etatAttendu
        -1            | 3           | 3                 | Mock(Utilisateur) | Mock(Recette) | false
        6             | 3           | 3                 | Mock(Utilisateur) | Mock(Recette) | false
        3             | -1          | 3                 | Mock(Utilisateur) | Mock(Recette) | false
        3             | 6           | 3                 | Mock(Utilisateur) | Mock(Recette) | false
        3             | 3           | -1                | Mock(Utilisateur) | Mock(Recette) | false
        3             | 3           | 6                 | Mock(Utilisateur) | Mock(Recette) | false
        3             | 3           | 3                 | Mock(Utilisateur) | Mock(Recette) | true
        0             | 5           | 3                 | Mock(Utilisateur) | Mock(Recette) | true
        3             | 3           | 3                 | null              | Mock(Recette) | false
        3             | 3           | 3                 | Mock(Utilisateur) | null          | false
        3             | 3           | 3                 | null              | null          | false
        3             | 3           | null              | Mock(Utilisateur) | Mock(Recette) | false
        3             | null        | 3                 | Mock(Utilisateur) | Mock(Recette) | false
        null          | 3           | 3                 | Mock(Utilisateur) | Mock(Recette) | false


    }


}
