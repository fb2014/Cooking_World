package cooking_world

import org.springframework.web.multipart.MultipartFile
import org.springframework.mock.web.MockMultipartFile

import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */

class UploadImageServiceIntegrationSpec extends Specification {


    UploadImageService uploadImageService=new UploadImageService()

    void "test uploadImage "(){
        given :" une recette et une image"
        Recette recette = Recette.findByTitre("Macarons au chocolat")
        String name = "heart.png"
        String originalFileName = "heart.png"
        String contentType = "image/png"

        MultipartFile mockMultipartFile = new MockMultipartFile(name,originalFileName, contentType, '123' as byte[])

        when:"quand on enregistre la recette"

        uploadImageService?.uploadImage("Jean", mockMultipartFile ,recette)

        then:" l'image est associée à la recette"
        recette.filename=="heart.png"
    }

    void "test uploadImage par defaut "(){
        given :" une recette sans image"
        Recette recette = Recette.findByTitre("Macarons au chocolat")
        String name = "heart.png"
        String originalFileName = "heart.png"
        String contentType = "image/png"
        MultipartFile mockMultipartFile = new MockMultipartFile(name,originalFileName, contentType, '' as byte[])
        when:"quand on enregistre la recette"

        uploadImageService?.uploadImage("Jean", mockMultipartFile ,recette)

        then:" l'image par defaut est associée à la recette"
        recette.filename=="default.jpg"
    }

    void "test update image"(){
        given:"une recette avec image"
        Recette recette = Recette.findByTitre("Macarons au chocolat")
        recette.filename="Ancienne photo"
        def ancien=recette.filename
        String name = "default.jpg"
        String originalFileName = "default.jpg"
        String contentType = "image/jpeg"

        MultipartFile mockMultipartFile = new MockMultipartFile(name,originalFileName, contentType, '123' as byte[])

        when:"on change la photo associée à la recette"

        uploadImageService?.updateImage("Jean", mockMultipartFile ,recette)

        then:"la photo est mise à jour"
        !(recette.filename.equals(ancien))

    }

    void "test update image par defaut"(){
        given:"une recette avec image par defaut"
        Recette recette = Recette.findByTitre("Macarons au chocolat")
        recette.filename="default.jpg"
        def ancien=recette.filename
        String name = "heart.png"
        String originalFileName = "heart.png"
        String contentType = "image/png"
        MultipartFile mockMultipartFile = new MockMultipartFile(name,originalFileName, contentType, '' as byte[])

        when:"si il y avait une photo associée à la recette avant la maj alors la récupérer et la garder"

        uploadImageService?.updateImage("Jean", mockMultipartFile ,recette)

        then:"la photo est mise à jour"
        recette.filename.equals(ancien)

    }

    void "test update sans image"(){
        given:"une recette avec image par defaut"
        Recette recette = Recette.findByTitre("Macarons au chocolat")
        recette.filename="imagetest.jpg"
        def ancien=recette.filename
        String name = "heart.png"
        String originalFileName = "heart.png"
        String contentType = "image/png"
        MultipartFile mockMultipartFile = new MockMultipartFile(name,originalFileName, contentType, '' as byte[])

        when:"quand on enregistre la recette sans photo"

        uploadImageService?.updateImage("Jean", mockMultipartFile ,recette)

        then:" l'image par defaut est associée à la recette"
        recette.filename.equals(ancien)

    }

}
