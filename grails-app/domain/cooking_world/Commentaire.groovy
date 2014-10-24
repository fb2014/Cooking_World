package cooking_world

class Commentaire {

    String message
    static belongsTo = [Recette, Utilisateur]

    static constraints = {
        message (nullable : false, blank : false)
    }

    @Override
    public String toString() {
        message
    }
}
