package cooking_world

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Commentaire)
class CommentaireSpec extends Specification {
    Commentaire commentaire
    def setup() {
        commentaire=new Commentaire()
    }

    def cleanup() {
    }

    void "test commentaire valide"() {
        given:"un commentaire"
        commentaire.message="Hello"
        when:"le commentaire est valide"
        def valid= commentaire.validate()
        then:"les contraintes sont respectées"
        valid==true

    }
}
