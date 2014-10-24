package cooking_world

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Notes)

class NotesSpec extends Specification {

    Notes notes

    def setup() {
        notes = new Notes()
    }

    def cleanup() {
    }

    @Unroll
    void "test sur les contraintes d'une note valide"() {
        given: "une note"
        notes.clarte = uneNoteClarte
        notes.gout = uneNoteGout
        notes.difficulte = uneNoteDifficulte

        when: "la note est validée"
        def valid = (((notes.clarte>=0 && (notes.clarte<=5)) && ((notes.gout>=0) &&(notes.gout<=5)) && ((notes.difficulte>=0)&& (notes.difficulte<=5))))

        then: "les vérifications de contraintes sont appliquées correctement"
        valid == etatAttendu

        where:
        uneNoteClarte | uneNoteGout | uneNoteDifficulte | etatAttendu
        -1            | 3           | 3                 | false
        6             | 3           | 3                 | false
        3             | -1          | 3                 | false
        3             | 6           | 3                 | false
        3             | 3           | -1                | false
        3             | 3           | 6                 | false
        3             | 3           | 3                 | true

    }


}
