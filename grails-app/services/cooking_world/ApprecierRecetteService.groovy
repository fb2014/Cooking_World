package cooking_world

import grails.transaction.Transactional

@Transactional
class ApprecierRecetteService {

    def noterRecette(Recette recetteInstance,Utilisateur user,int ngout,int nclarte,int nsimplicite) {
        def newNote=new Notes(clarte:nclarte,simplicite: nsimplicite,gout: ngout)
        recetteInstance.addToNotes(newNote)
        user.addToNotes(newNote)
        newNote.save(flush: true, failOnError: true)

    }

    def donnerCoupdecoeur(Recette recetteInstance,Utilisateur user){
        def cdc=new CoupDeCoeur(date:new Date())
        recetteInstance.addToCoupDeCoeur(cdc)
        user.addToCoupDeCoeur(cdc)
        cdc.save(flush: true, failOnError: true)

    }


}
