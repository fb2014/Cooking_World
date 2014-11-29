package cooking_world

import spock.lang.Specification

/**
 * Created by Boulanger on 14/11/2014.
 */
class ApprecierRecetteServiceIntegrationSpec extends Specification {

    ApprecierRecetteService apprecierRecetteService

    def setup() {
        apprecierRecetteService = new ApprecierRecetteService()
    }

    void "test notation d'une recette"() {
        given: "une recette, un utilisateur et des notes"
        Utilisateur utilisateur = Utilisateur.findByPseudo(unPseudo)
        Recette recette = Recette.findByTitre(unTitre)
        Integer nbNotesAvantAjout = Notes.findByRecette(recette)?.count()
        when: "l'utilisateur note la recette"
        apprecierRecetteService?.noterRecette(recette, utilisateur, uneNoteGout, uneNoteClarte, uneNoteSimplicite)
        then: "la note a été prise en compte et ajoutée à la base de données"
        System.out.println(unTitre + unPseudo + caDoitMarcher + " : nb cdc avant = " + nbNotesAvantAjout + " - nb cdc après = " + Notes.findByRecette(recette)?.count())
        ((nbNotesAvantAjout) != Notes.findByRecette(recette)?.count()) == caDoitMarcher
        /*if (caDoitMarcher)
            (nbNotesAvantAjout + 1) == Notes.findByRecette(recette).count()
        else // La note n'a pas été enregistrée
            nbNotesAvantAjout == Notes.findByRecette(recette)?.count()*/
        where:
        unTitre                  | unPseudo             | uneNoteClarte | uneNoteSimplicite | uneNoteGout | caDoitMarcher
        "Macarons à la pistache" | "Alice"              | 0             | 3                 | 5           | true
        "Macarons à la pistache" | "Alice"              | 5             | 0                 | 2           | true
        "Macarons à la pistache" | "Alice"              | 4             | 5                 | 0           | true
        "Macarons à la pistache" | "Alice"              | -1            | 2                 | 1           | false
        "Macarons à la pistache" | "Alice"              | 1             | -1                | 1           | false
        "Macarons à la pistache" | "Alice"              | 1             | 2                 | -1          | false
        "Macarons à la pistache" | "UtilisateurInconnu" | 1             | 2                 | 1           | false
        "RecetteEtrange"         | "Alice"              | 3             | 3                 | 3           | false
        "Macarons à la pistache" | null                 | 0             | 3                 | 5           | false
        null                     | "Alice"              | 0             | 3                 | 5           | false
    }

    void "test ajout d'un coup de coeur sur une recette"() {
        given: "une recette et un utilisateur"
        Utilisateur utilisateur = Utilisateur.findByPseudo(unPseudo)
        Recette recette = Recette.findByTitre(unTitre)
        Integer nbCoupsDeCoeurAvantAjout = CoupDeCoeur.findByRecette(recette)?.count()
        when: "l'utilisateur donne un coup de coeur la recette "
        apprecierRecetteService?.donnerCoupdecoeur(recette, utilisateur)
        then: "le coup de coeur a été pris en compte et ajouté à la base de données"
        System.out.println(unTitre + unPseudo + caDoitMarcher + " : nb cdc avant = " + nbCoupsDeCoeurAvantAjout + " - nb cdc après = " + CoupDeCoeur.findByRecette(recette)?.count())
        ((nbCoupsDeCoeurAvantAjout) != CoupDeCoeur.findByRecette(recette)?.count()) == caDoitMarcher
        /*if (caDoitMarcher)
            (nbCoupsDeCoeurAvantAjout + 1) == CoupDeCoeur.findByRecette(recette).count()
        else // La note n'a pas été enregistrée
            nbCoupsDeCoeurAvantAjout == Notes.findByRecette(recette)?.count()*/
        where:
        unTitre                  | unPseudo             | caDoitMarcher
        "Macarons à la pistache" | "Alice"              | true
        null                     | "Alice"              | false
        "Macarons à la pistache" | null                 | false
        "Macarons à la pistache" | "UtilisateurInconnu" | false
        "RecetteEtrange"         | "Alice"              | false
    }

    void "test ajout d'un commentaire sur une recette"() {
        given: "une recette, un utilisateur et un message"
        Utilisateur utilisateur = Utilisateur.findByPseudo(unPseudo)
        Recette recette = Recette.findByTitre(unTitre)
        Integer nbCommentairesAvantAjout = Commentaire.findByRecette(recette)?.count()
        when: "l'utilisateur commente la recette"
        apprecierRecetteService?.commenterRecette(recette, utilisateur, unMessage)
        then: "le commentaire a été pris en compte et ajouté à la base de données"
        System.out.println(unMessage + unPseudo + caDoitMarcher + " : nb coms avant = " + nbCommentairesAvantAjout + " - nb coms après = " + Commentaire.findByRecette(recette)?.count())
        ((nbCommentairesAvantAjout) != Commentaire.findByRecette(recette)?.count()) == caDoitMarcher
        /*if (caDoitMarcher)
            (nbNotesAvantAjout + 1) == Notes.findByRecette(recette).count()
        else // La note n'a pas été enregistrée
            nbNotesAvantAjout == Notes.findByRecette(recette)?.count()*/
        where:
        unTitre                  | unPseudo             | unMessage                            | caDoitMarcher
        "Macarons à la pistache" | "Alice"              | "Excellente recette. Merci beaucoup" | true
        "Macarons à la pistache" | "Alice"              | "C'est nul !!!"                      | true
        "Macarons à la pistache" | "Alice"              | ""                                   | false
        "Macarons à la pistache" | "UtilisateurInconnu" | "paf"                                | false
        "RecetteEtrange"         | "Alice"              | "pif"                                | false
        "Macarons à la pistache" | null                 | "plop"                               | false
        null                     | "Alice"              | "pouet"                              | false
        "Macarons à la pistache" | "Alice"              | null                                 | false

    }
}
