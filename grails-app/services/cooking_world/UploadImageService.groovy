package cooking_world

import grails.transaction.Transactional
import org.fusesource.jansi.AnsiConsole
import org.springframework.web.multipart.MultipartFile

@Transactional
class UploadImageService {

    def uploadImage(String pseudoUser, MultipartFile uploadedFile,Recette recetteInstance) {
        // Save l'image dans le repertoire dédié à l'utilisateur courant


        def val =getClass().getResource("/").toString()+"web-app/images/UsersImages/"+pseudoUser
        def val1=val.replaceFirst("/","")
        def val2 =val1.replace("/","\\")
        def storagePath=val2.replace("file:","")

        /*def storagePath=""
        def chemin =getClass().getResource("/").toString()
        if(!(chemin.contains("\\"))) {
            def val = chemin + "web-app/images/UsersImages/" + pseudoUser
            def val1 = val.replaceFirst("/", "")
            def val2 = val1.replace("/", "\\")
            storagePath = val2.replace("file:", "")
        }
        else{
            def vall = chemin + "web-app"+"\\"+"images"+"\\"+"UsersImages"+"\\" + pseudoUser
            def vall1 = vall.replaceFirst("\\", "")
            storagePath = vall1.replace("file:", "")
        }*/



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

            /*def storagePathDefault=""
            if(!(chemin.contains("\\"))) {
                def val = chemin + +"web-app/images/default.jpg"
                def val1 = val.replaceFirst("/", "")
                def val2 = val1.replace("/", "\\")
                storagePathDefault = val2.replace("file:", "")
            }
            else{
                def va= chemin + "web-app"+"\\"+"images"+"\\"+"default.jpg"
                def va1 = va.replaceFirst("\\", "")
                storagePathDefault = va1.replace("file:", "")
            }*/

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

            /*def storagePath=""
            def chemin =getClass().getResource("/").toString()
            if(!(chemin.contains("\\"))) {
                def val = chemin + "web-app/images/UsersImages/" + pseudoUser
                def val1 = val.replaceFirst("/", "")
                def val2 = val1.replace("/", "\\")
                storagePath = val2.replace("file:", "")
            }
            else{
                def vall = chemin + "web-app"+"\\"+"images"+"\\"+"UsersImages"+"\\" + pseudoUser
                def vall1 = vall.replaceFirst("\\", "")
                storagePath = vall1.replace("file:", "")
            }*/


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

                /*def storagePath2=""
                def chemin1 =getClass().getResource("/").toString()
                if(!(chemin1.contains("\\"))) {
                    def valz = chemin1 +"web-app/images/UsersImages/"+pseudoUser+"/"+recetteInstance.filename
                    def valz1 = valz.replaceFirst("/", "")
                    def valz2 = valz1.replace("/", "\\")
                    storagePath2 = valz2.replace("file:", "")
                }
                else{
                    def valll = chemin1 + "web-app"+"\\"+"images"+"\\"+"UsersImages"+"\\"+pseudoUser+"\\"+recetteInstance.filename
                    def valll1 = valll.replaceFirst("\\", "")
                    storagePath2 = valll1.replace("file:", "")
                }*/

                File maphoto= new  File(storagePath2)
                recetteInstance.photo = maphoto.bytes
             }
            else{

                def autre =getClass().getResource("/").toString()+"web-app/images/default.jpg"
                def autre1=autre.replaceFirst("/","")
                def autre2 =autre1.replace("/","\\")
                def storagePathDefault2=autre2.replace("file:","")

               /* def storagePathDefault2=""
                def ch =getClass().getResource("/").toString()
                if(!(ch.contains("\\"))) {
                    def v = ch +"web-app/images/default.jpg"
                    def v1 = v.replaceFirst("/", "")
                    def v2 = v1.replace("/", "\\")
                    storagePathDefault2 = v2.replace("file:", "")
                }
                else{
                    def sa = ch +"web-app"+"\\"+"images"+"\\"+"default.jpg"
                    def sb = sa.replaceFirst("\\", "")
                    storagePathDefault2 = sb.replace("file:", "")
                }*/



                File maphoto2 = new  File(storagePathDefault2)
                recetteInstance.photo = maphoto2.bytes

            }
        }
    }
}
