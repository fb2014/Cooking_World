package cooking_world

import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.springframework.web.multipart.commons.CommonsMultipartFile

@Transactional
class UploadImageService {
    def servletContext = ServletContextHolder.servletContext

    def uploadImage(String pseudoUser, CommonsMultipartFile uploadedFile,Recette recetteInstance) {
        // Save l'image dans le repertoire dédié à l'utilisateur courant

        def storagePath = servletContext.getRealPath("/images/UsersImages/"+pseudoUser)

        def userDir = new File(storagePath )
        userDir.mkdirs()
        if (!(uploadedFile.empty)) {
            recetteInstance.photo = uploadedFile.bytes //converting the file to bytes
            recetteInstance.filename = uploadedFile.originalFilename //getting the file name from the uploaded file
            uploadedFile.transferTo( new File( userDir, uploadedFile.originalFilename))

        }
        else{ //mettre une image par default
            def storagePathDefault = servletContext.getRealPath("/images/default.jpg")
            File defaultfile = new  File(storagePathDefault)
            recetteInstance.filename ="default.jpg"
            recetteInstance.photo = defaultfile.bytes
        }

    }

    def updateImage(String pseudoUser, CommonsMultipartFile uploadedFile,Recette recetteInstance) {

        if (!uploadedFile.empty){//update avec modification de la photo

            // Save l'image dans le repertoire dédié à l'utilisateur courant

            def storagePath = servletContext.getRealPath("/images/UsersImages/"+pseudoUser)

            def userDir = new File(storagePath )
            userDir.mkdirs()

            recetteInstance.photo = uploadedFile.bytes//converting the file to bytes
            recetteInstance.filename = uploadedFile.originalFilename //getting the file name from the uploaded file
            uploadedFile.transferTo(new File(userDir, uploadedFile.originalFilename))
        }

        else{

            /* s'l y avait deja une photo associée à la recette avt la maj
             alors la récupérer et la garder
             */
            if (!(recetteInstance.filename.equals("default.jpg"))){
                def storagePath2 = servletContext.getRealPath("/images/UsersImages/"+pseudoUser+"/"+recetteInstance.filename)
                File maphoto= new  File(storagePath2)
                recetteInstance.photo = maphoto.bytes
             }
            else{
                def storagePathDefault2 = servletContext.getRealPath("/images/default.jpg")
                File maphoto2 = new  File(storagePathDefault2)
                recetteInstance.photo = maphoto2.bytes

            }
        }
    }
}
