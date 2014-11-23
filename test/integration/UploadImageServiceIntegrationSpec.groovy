import cooking_world.Recette
import cooking_world.UploadImageService
import grails.test.mixin.TestFor
import spock.lang.Specification
import org.springframework.web.multipart.commons.CommonsMultipartFile

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(UploadImageService)
class UploadImageServiceIntegrationSpec extends Specification {

    void "test d'un upload  sans image"() {
        given: "une recette"
        def recette = Mock(Recette)
        recette.filename = ""
        def uploadImageService = Mock(UploadImageService)
        def commonsMultipartFile = Mock(CommonsMultipartFile)
        when: "on procède à l'upload sans image"
        uploadImageService.updateImage("user",commonsMultipartFile,recette)
        then: "l'image  est l'image par defaut"
        recette.filename == "default.jpg"
    }

    /*void "test d'un upload  sans image"() {
        given: "une recette"
        def recette = Mock(Recette)
        recette.filename = ""
        def uploadImageService = Mock(UploadImageService)
        def commonsMultipartFile = Mock(CommonsMultipartFile)
        when: "on procède à l'upload sans image"
        uploadImageService.updateImage("user",commonsMultipartFile,recette)
        then: "l'image  est l'image par defaut"
        recette.filename == "default.jpg"
    }*/
}
