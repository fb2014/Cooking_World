package cooking_world

class Utilisateur {
    String pseudo
    String motDePasse
    static hasMany = [recette : Recette,notes:Notes]

    static constraints = {
        pseudo (nullable : false, blank : false, unique: true)
        motDePasse (nullable : false, blank : false)
    }

    @Override
    public String toString() {
        pseudo
    }
}
