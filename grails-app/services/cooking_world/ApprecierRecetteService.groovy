package cooking_world

//import grails.transaction.Transactional

//@Transactional
class ApprecierRecetteService {

    def noterRecette(Recette recetteInstance,Utilisateur user,int ngout,int nclarte,int nsimplicite) {
        if (recetteInstance?.validate() && user?.validate()) {
            def newNote=new Notes(clarte:nclarte,simplicite: nsimplicite,gout: ngout)
            recetteInstance.addToNotes(newNote)
            user.addToNotes(newNote)
            if (newNote.validate())
                newNote.save(flush: true, failOnError: true)
        }
    }
    def donnerCoupdecoeur(Recette recetteInstance,Utilisateur user){
        if (recetteInstance?.validate() && user?.validate()) {
            def cdc = new CoupDeCoeur(dateCoupDeCoeur: new Date())
            recetteInstance.addToCoupDeCoeur(cdc)
            user.addToCoupDeCoeur(cdc)
            if (cdc.validate())
                cdc.save(flush: true, failOnError: true)
        }
    }

    /*def noterRecette(Recette recetteInstance,Utilisateur user,int ngout,int nclarte,int nsimplicite) {
        if (recetteInstance?.validate() && user?.validate()) {
            if (nclarte >=0 && nclarte <=5 && ngout >=0 && ngout <=5 && nsimplicite >=0 && nsimplicite <=5) {
                def newNote = new Notes(clarte: nclarte, simplicite: nsimplicite, gout: ngout)
                recetteInstance.addToNotes(newNote)
                user.addToNotes(newNote)
                if (newNote.validate())
                    newNote.save(flush: true, failOnError: true)
            }
        }
    }

    def donnerCoupdecoeur(Recette recetteInstance,Utilisateur user){
        if (recetteInstance?.validate() && user?.validate()) {
            def cdc = new CoupDeCoeur(dateCoupDeCoeur: new Date())
            user.addToCoupDeCoeur(cdc)
            recetteInstance.addToCoupDeCoeur(cdc)

            if (cdc.validate())
                cdc.save(flush: true, failOnError: true)
        }
    }*/

    /*def commenterRecette(Recette recetteInstance, Utilisateur user, String commentaire){
        def newCommentaire = new Commentaire(message: commentaire)
        recetteInstance.addToCommentaire(newCommentaire)
        user.addToCommentaire(newCommentaire)
        newCommentaire.save(flush: true, failOnError: true)
    }*/

}
