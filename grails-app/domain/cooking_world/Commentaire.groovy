package cooking_world

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Commentaire {

    String message
    Date dateCom
    static belongsTo = [recette : Recette, utilisateur : Utilisateur]

    static constraints = {
        message (nullable : false, blank : false)
        dateCom(nullable: false, blank: false)
        recette(nullable: false, blank: false)
        utilisateur(nullable: false, blank: false)
    }

    @Override
    public String toString() {
        message
    }
}
