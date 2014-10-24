package cooking_world

class CoupDeCoeur {

    Date date

    static belongsTo = [Utilisateur,Recette]
    static constraints = {
    }
}
