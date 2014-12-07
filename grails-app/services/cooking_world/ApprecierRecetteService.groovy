package cooking_world

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

    def commenterRecette(Recette recetteInstance, Utilisateur user, String commentaire){
        if (recetteInstance?.validate() && user?.validate()) {
            def newCommentaire = new Commentaire(message: commentaire, dateCom: new Date())
            recetteInstance.addToCommentaire(newCommentaire)
            user.addToCommentaire(newCommentaire)
            if (newCommentaire.validate())
                newCommentaire.save(flush: true, failOnError: true)
        }
    }

}
