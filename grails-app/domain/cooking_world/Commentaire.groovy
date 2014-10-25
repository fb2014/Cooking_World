package cooking_world

class Commentaire {

    String message
    static belongsTo = [recette : Recette, utilisateur : Utilisateur]

    static constraints = {
        message (nullable : false, blank : false)
        recette(nullable: false, blank: false)
        utilisateur(nullable: false, blank: false)
    }

    @Override
    public String toString() {
        message
    }
}
