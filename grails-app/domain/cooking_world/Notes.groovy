package cooking_world

class Notes {

    Integer gout
    Integer difficulte
    Integer clarte
    static belongsTo = [Recette,Utilisateur]


    static constraints = {
        clarte (min : 0, max : 5)
        gout (min : 0, max : 5)
        difficulte (min : 0, max : 5)
    }
}
