package cooking_world

import spock.lang.Specification

class UtilisateurIntegrationSpec extends Specification {

    Recette maRecette
    Utilisateur utilisateur
    Commentaire commentaire
    Notes notes
    CoupDeCoeur monCoupDeCoeur

    void "test suppression d'un utilisateur"() {
        given: "un utilisateur,une recette, un coup de coeur, des notes et un message"
        utilisateur = new Utilisateur(pseudo: "TestPseudo2",motDePasse: "TestMdp").save(failOnError: true)
        maRecette = new Recette(titre:"Cookies", ingredients: "chocolat, farine, sucre", description:"Mélanger les ingrédients", tempsCuisson: 40, tempsPreparation: 45, dateCreation: new Date("10/10/2014"), filename : "cookie.jpg")
        utilisateur.addToRecette(maRecette).save(failOnError: true)
        commentaire = new Commentaire(dateCom: new Date(), message: "Je n'ai rien compris à la recette :(")
        utilisateur.addToCommentaire(commentaire)
        maRecette.addToCommentaire(commentaire)
        utilisateur.save(failOnError: true)
        notes = new Notes(clarte: 3, simplicite: 3, gout: 2)
        utilisateur.addToNotes(notes)
        maRecette.addToNotes(notes)
        monCoupDeCoeur = new CoupDeCoeur(dateCoupDeCoeur: new Date())
        utilisateur.addToCoupDeCoeur(monCoupDeCoeur)
        maRecette.addToCoupDeCoeur(monCoupDeCoeur).save(failOnError: true)


        when: "on supprime l'utilisateur"
        utilisateur.delete()
        then: "Tout est supprimé"
        Recette.get(maRecette.id) == null
        Commentaire.get(commentaire.id) == null
        Utilisateur.get(utilisateur.id) == null
        Notes.get(notes.id) == null
        CoupDeCoeur.get(monCoupDeCoeur.id) == null
    }
}
