package cooking_world

import grails.transaction.Transactional
import org.springframework.web.multipart.MultipartFile

@Transactional
class UploadImageService {

    def uploadImage(String pseudoUser, MultipartFile uploadedFile,Recette recetteInstance) {
        // Save l'image dans le repertoire dédié à l'utilisateur courant

        def val =getClass().getResource("/").toString()+"web-app/images/UsersImages/"+pseudoUser
        def val1=val.replaceFirst("/","")
        def val2 =val1.replace("/","\\")
        def storagePath=val2.replace("file:","")

        def userDir = new File(storagePath )
        userDir.mkdirs()
        if (!(uploadedFile.empty)) {
            recetteInstance.photo = uploadedFile.bytes //converting the file to bytes
            recetteInstance.filename = uploadedFile.originalFilename //getting the file name from the uploaded file
            uploadedFile.transferTo( new File( userDir, uploadedFile.originalFilename))

        }
        else{ //mettre une image par default

            def autre =getClass().getResource("/").toString()+"web-app/images/default.jpg"
            def autre1=autre.replaceFirst("/","")
            def autre2 =autre1.replace("/","\\")
            def storagePathDefault=autre2.replace("file:","")

            File defaultfile = new  File(storagePathDefault)
            recetteInstance.filename ="default.jpg"
            recetteInstance.photo = defaultfile.bytes
        }

    }

    def updateImage(String pseudoUser, MultipartFile uploadedFile,Recette recetteInstance) {

        if (!uploadedFile.empty){//update avec modification de la photo

            // Save l'image dans le repertoire dédié à l'utilisateur courant

            def val =getClass().getResource("/").toString()+"web-app/images/UsersImages/"+pseudoUser
            def val1=val.replaceFirst("/","")
            def val2 =val1.replace("/","\\")
            def storagePath=val2.replace("file:","")


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

                def val =getClass().getResource("/").toString()+"web-app/images/UsersImages/"+pseudoUser+"/"+recetteInstance.filename
                def val1=val.replaceFirst("/","")
                def val2 =val1.replace("/","\\")
                def storagePath2=val2.replace("file:","")

                File maphoto= new  File(storagePath2)
                recetteInstance.photo = maphoto.bytes
             }
            else{

                def autre =getClass().getResource("/").toString()+"web-app/images/default.jpg"
                def autre1=autre.replaceFirst("/","")
                def autre2 =autre1.replace("/","\\")
                def storagePathDefault2=autre2.replace("file:","")

                File maphoto2 = new  File(storagePathDefault2)
                recetteInstance.photo = maphoto2.bytes

            }
        }
    }
}
