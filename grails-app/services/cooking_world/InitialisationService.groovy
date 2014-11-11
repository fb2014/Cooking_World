package cooking_world

import grails.transaction.Transactional

@Transactional
class InitialisationService {

    boolean transactionnal = true

    def initialiserDonnees() {

        def utilisateurJean = new Utilisateur(pseudo: "Jean",motDePasse: "azerty").save(failOnError : true)
        def utilisateurMarc = new Utilisateur(pseudo: "Marc",motDePasse: "motdepasse").save(failOnError : true)
        def utilisateurPaul = new Utilisateur(pseudo: "Paul",motDePasse: "jesuispaul").save(failOnError : true)
        def utilisateurAlice = new Utilisateur(pseudo: "Alice",motDePasse: "secret").save(failOnError : true)
        def utilisateurMarie = new Utilisateur(pseudo: "Marie",motDePasse: "tarteauxpommes").save(failOnError : true)

        def recetteCookies = new Recette(titre:"Cookies", ingredients: "chocolat, farine, sucre", description:"Mélanger les ingrédients", tempsCuisson: 10, tempsPreparation: 45, dateCreation: new Date("10/10/2014"))
        utilisateurAlice.addToRecette(recetteCookies).save(failOnError: true)

        def recetteMacaronsPistache = new Recette(titre:"Macarons à la pistache", ingredients: "poudre d'amende, sucre glace, blanc d'oeuf, pâte à pistache, chocolat blanc", description:"Faire les coques, faire la ganache, assembler.", tempsCuisson: 12, tempsPreparation: 60, dateCreation: new Date())
        utilisateurAlice.addToRecette(recetteMacaronsPistache).save(failOnError: true)

        def recetteMacaronsChocolat = new Recette(titre:"Macarons au chocolat", ingredients: "poudre d'amende, sucre glace, blanc d'oeuf, poudre cacao, chocolat noir", description:"Faire les coques, faire la ganache, assembler.", tempsCuisson: 12, tempsPreparation: 60, dateCreation: new Date())
        utilisateurJean.addToRecette(recetteMacaronsChocolat).save(failOnError: true)

        def recetteTartePommes = new Recette(titre:"Tarte aux pommes", ingredients: "beurre, sucre , pommes, pâte feuilletée", description:"Eplucher et couper les pommes; enfournez.", tempsCuisson: 30, tempsPreparation: 45, dateCreation: new Date())
        utilisateurMarie.addToRecette(recetteTartePommes).save(failOnError: true)

        def coeurMarieMacaronPistache = new CoupDeCoeur(dateCoupDeCoeur: new Date())
        utilisateurMarie.addToCoupDeCoeur(coeurMarieMacaronPistache)
        recetteMacaronsPistache.addToCoupDeCoeur(coeurMarieMacaronPistache).save(failOnError: true)

        def notesMarieMacaron = new Notes(clarte: 5, simplicite: 4, gout: 5)
        utilisateurMarie.addToNotes(notesMarieMacaron)
        recetteMacaronsPistache.addToNotes(notesMarieMacaron).save(failOnError: true)

        def notesPaulMacaron = new Notes(clarte: 0, simplicite: 0, gout: 0)
        utilisateurPaul.addToNotes(notesPaulMacaron)
        recetteMacaronsPistache.addToNotes(notesPaulMacaron).save(failOnError: true)

        def notesMarcTartePommes = new Notes(clarte: 5, simplicite: 5, gout: 5)
        utilisateurMarc.addToNotes(notesMarcTartePommes)
        recetteTartePommes.addToNotes(notesMarcTartePommes).save(failOnError: true)

        def commentaireMarieMacaron = new Commentaire(message: "Super recette. Génial, merci Alice, j'arrive enfin à faire mes macarons !!! :)")
        utilisateurMarie.addToCommentaire(commentaireMarieMacaron)
        recetteMacaronsPistache.addToCommentaire(commentaireMarieMacaron).save(failOnError: true)

        def commentairePaulMacaron = new Commentaire(message: "Je n'ai rien compris à la recette :(")
        utilisateurPaul.addToCommentaire(commentairePaulMacaron)
        recetteMacaronsPistache.addToCommentaire(commentairePaulMacaron).save(failOnError: true)

        def commentaireMarcTartePommes = new Commentaire(message: "Recette très bien exliquée et très simple à réaliser. Auriez-vous la recette de la tarte tatin ?")
        utilisateurMarc.addToCommentaire(commentaireMarcTartePommes)
        recetteTartePommes.addToCommentaire(commentaireMarcTartePommes).save(failOnError: true)

    }
}
