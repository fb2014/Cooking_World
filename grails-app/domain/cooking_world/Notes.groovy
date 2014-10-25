package cooking_world

class Notes {

    Integer clarte
    Integer gout
    Integer simplicite
    static belongsTo = [recette : Recette, utilisateur : Utilisateur]

    static constraints = {
        clarte (min : 0, max : 5)
        gout (min : 0, max : 5)
        simplicite (min : 0, max : 5)
        recette(nullable: false, blank: false)
        utilisateur(nullable: false, blank: false)
    }
}
