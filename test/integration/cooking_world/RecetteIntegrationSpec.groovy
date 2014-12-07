package cooking_world

import spock.lang.Specification

class RecetteIntegrationSpec extends Specification {

    Recette maRecette
    Utilisateur utilisateur
    Commentaire commentaire
    Notes notes
    CoupDeCoeur monCoupDeCoeur

    void "test suppression d'une recette"() {
        given: "une recette, un utilisateur, un coup de coeur, des notes et un message"
        utilisateur = new Utilisateur(pseudo: "TestPseudo",motDePasse: "TestMdp").save(failOnError: true)
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


        when: "on supprime la recette"
        maRecette.delete()
        then: "La recette, les notes et le commentaire sont supprimés mais pas l'utilisateur"
        Recette.get(maRecette.id) == null
        Commentaire.get(commentaire.id) == null
        Utilisateur.get(utilisateur.id) == utilisateur
        Notes.get(notes.id) == null
        CoupDeCoeur.get(monCoupDeCoeur.id) == null
    }
}
