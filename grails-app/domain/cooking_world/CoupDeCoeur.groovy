package cooking_world

class CoupDeCoeur {

    Date date

    static belongsTo = [utilisateur : Utilisateur, recette : Recette]
    static constraints = {
        utilisateur(nullable: false, blank:false)
        recette(nullable: false, blank: false)
        date(nullable: false, blank: false)
    }
}
