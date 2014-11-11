package cooking_world

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class CoupDeCoeur {

    Date dateCoupDeCoeur

    static belongsTo = [utilisateur : Utilisateur, recette : Recette]
    static constraints = {
        utilisateur(nullable: false, blank:false)
        recette(nullable: false, blank: false)
        dateCoupDeCoeur(nullable: false, blank: false)
    }
}
