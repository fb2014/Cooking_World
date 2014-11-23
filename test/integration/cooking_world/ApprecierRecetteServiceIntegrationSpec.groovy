package cooking_world

//import grails.test.mixin.TestFor
import junit.framework.TestCase
import spock.lang.Specification

/**
 * Created by Boulanger on 14/11/2014.
 */
//@TestFor(ApprecierRecetteService)
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
        if (caDoitMarcher)
            (nbNotesAvantAjout + 1) == Notes.findByRecette(recette).count()
        else // La note n'a pas été enregistrée
            nbNotesAvantAjout == Notes.findByRecette(recette)?.count()
        where:
        unTitre                  | unPseudo             | uneNoteClarte | uneNoteSimplicite | uneNoteGout | caDoitMarcher
        "Macarons à la pistache" | "Alice"              | 0             | 3                 | 5           | true
        "Macarons à la pistache" | "Alice"              | -1            | 2                 | 1           | false
        "Macarons à la pistache" | "UtilisateurInconnu" | 1             | 2                 | 1           | false
        "RecetteEtrange"         | "Alice"              | 3             | 3                 | 3           | false
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
        "Macarons à la pistache" | "Alice"              | true
        "Macarons à la pistache" | "UtilisateurInconnu" | false
        "RecetteEtrange"         | "Alice"              | false
    }

    /* ApprecierRecetteService apprecierRecetteService

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
         System.out.println(" : nb noes avant = " + nbNotesAvantAjout + " - nb notes après = " + Notes.findByRecette(recette)?.count())
         (nbNotesAvantAjout != Notes.findByRecette(recette)?.count()) == caDoitMarcher
         /*if (caDoitMarcher)
             (nbNotesAvantAjout + 1) == Notes.findByRecette(recette).count()
         else // La note n'a pas été enregistrée
             nbNotesAvantAjout == Notes.findByRecette(recette)?.count()

         where:
         unTitre                  | unPseudo             | uneNoteClarte | uneNoteSimplicite | uneNoteGout | caDoitMarcher
         "Macarons à la pistache" | "Alice"              | 0             | 3                 | 5           | true
         "Macarons à la pistache" | "Alice"              | -1            | 2                 | 1           | false
         "Macarons à la pistache" | "Alice"              | 2             | -1                | 1           | false
         "Macarons à la pistache" | "Alice"              | 2             | 2                 | -1          | false
         "Macarons à la pistache" | "UtilisateurInconnu" | 1             | 2                 | 1           | false
         "RecetteEtrange"         | "Alice"              | 3             | 3                 | 3           | false
     }

     void "test ajout d'un coup de coeur sur une recette"() {

         given: "une recette et un utilisateur"
         Utilisateur utilisateur = Utilisateur.findByPseudo(unPseudo)
         Recette recette = Recette.findByTitre(unTitre)
         Integer nbCoupsDeCoeurAvantAjout = CoupDeCoeur.findByRecette(recette)?.count()

         when: "l'utilisateur donne un coup de coeur la recette "

         apprecierRecetteService?.donnerCoupdecoeur(recette, utilisateur)

         then: "le coup de coeur valide a été pris en compte et ajouté à la base de données"
         System.out.println(unTitre + unPseudo + caDoitMarcher + " : nb cdc avant = " + nbCoupsDeCoeurAvantAjout + " - nb cdc après = " + CoupDeCoeur.findByRecette(recette)?.count())
         ((nbCoupsDeCoeurAvantAjout) != CoupDeCoeur.findByRecette(recette)?.count()) == caDoitMarcher
         /* if (caDoitMarcher)
              (nbCoupsDeCoeurAvantAjout + 1) == CoupDeCoeur.findByRecette(recette).count()
          else // La note n'a pas été enregistrée
              nbCoupsDeCoeurAvantAjout == CoupDeCoeur.findByRecette(recette)?.count()

         where:
         unTitre                  | unPseudo             | caDoitMarcher
         "Macarons à la pistache" | "Alice"              | true
         "Macarons à la pistache" | "UtilisateurInconnu" | false
         "RecetteEtrange"         | "Alice"              | false
     }*/
}
