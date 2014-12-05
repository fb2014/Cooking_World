package cooking_world

import grails.transaction.Transactional
@Transactional
class InitialisationService {

    def initialiserDonnees() {

        def utilisateurJean = new Utilisateur(pseudo: "Jean",motDePasse: "azerty").save(failOnError : true)
        def utilisateurMarc = new Utilisateur(pseudo: "Marc",motDePasse: "motdepasse").save(failOnError : true)
        def utilisateurPaul = new Utilisateur(pseudo: "Paul",motDePasse: "jesuispaul").save(failOnError : true)
        def utilisateurAlice = new Utilisateur(pseudo: "Alice",motDePasse: "secret").save(failOnError : true)
        def utilisateurMarie = new Utilisateur(pseudo: "Marie",motDePasse: "tarteauxpommes").save(failOnError : true)

        def recetteCookies = new Recette(titre:"Cookies", ingredients: "chocolat, farine, sucre", description:"Mélanger les ingrédients", tempsCuisson: 10, tempsPreparation: 45, dateCreation: new Date("10/10/2014"), filename : "cookie.jpg")
        def lien01 =getClass().getResource("/").toString()+"web-app/images/cookie.jpg"
        def lien1=lien01.replaceFirst("/","")
        def lien11 =lien1.replace("/","\\")
        def storagePathDefault1=lien11.replace("file:","")
        File maphoto1 = new  File(storagePathDefault1)
        recetteCookies.photo = maphoto1.bytes
        utilisateurAlice.addToRecette(recetteCookies).save(failOnError: true)

        def recetteMacaronsPistache = new Recette(titre:"Macarons à la pistache", ingredients: "poudre d'amende, sucre glace, blanc d'oeuf, pâte à pistache, chocolat blanc", description:"Faire les coques, faire la ganache, assembler.", tempsCuisson: 12, tempsPreparation: 60, dateCreation: new Date(), filename : "pistache.jpg")
        def lien02 =getClass().getResource("/").toString()+"web-app/images/pistache.jpg"
        def lien2=lien02.replaceFirst("/","")
        def lien22 =lien2.replace("/","\\")
        def storagePathDefault2=lien22.replace("file:","")
        File maphoto2 = new  File(storagePathDefault2)
        recetteMacaronsPistache.photo = maphoto2.bytes
        utilisateurAlice.addToRecette(recetteMacaronsPistache).save(failOnError: true)

        def recetteMacaronsChocolat = new Recette(titre:"Macarons au chocolat", ingredients: "poudre d'amende, sucre glace, blanc d'oeuf, poudre cacao, chocolat noir", description:"Faire les coques, faire la ganache, assembler.", tempsCuisson: 12, tempsPreparation: 60, dateCreation: new Date(), filename : "choco.jpg")
        def lien03 =getClass().getResource("/").toString()+"web-app/images/choco.jpg"
        def lien3=lien03.replaceFirst("/","")
        def lien33 =lien3.replace("/","\\")
        def storagePathDefault3=lien33.replace("file:","")
        File maphoto3 = new  File(storagePathDefault3)
        recetteMacaronsChocolat.photo = maphoto3.bytes
        utilisateurJean.addToRecette(recetteMacaronsChocolat).save(failOnError: true)

        def recetteTartePommes = new Recette(titre:"Tarte aux pommes", ingredients: "beurre, sucre , pommes, pâte feuilletée", description:"Eplucher et couper les pommes; enfournez.", tempsCuisson: 30, tempsPreparation: 45, dateCreation: new Date(), filename : "tarte.jpg")
        def lien04 =getClass().getResource("/").toString()+"web-app/images/tarte.jpg"
        def lien4=lien04.replaceFirst("/","")
        def lien44 =lien4.replace("/","\\")
        def storagePathDefault4=lien44.replace("file:","")
        File maphoto4 = new  File(storagePathDefault4)
        recetteTartePommes.photo = maphoto4.bytes
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

        def notesMarcMacaronsChocolat = new Notes(clarte: 3, simplicite: 3, gout: 2)
        utilisateurMarc.addToNotes(notesMarcMacaronsChocolat)
        recetteMacaronsChocolat.addToNotes(notesMarcMacaronsChocolat).save(failOnError: true)

        def notesMarieCookies = new Notes(clarte: 1, simplicite: 4, gout: 4)
        utilisateurMarie.addToNotes(notesMarieCookies)
        recetteCookies.addToNotes(notesMarieCookies).save(failOnError: true)

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
