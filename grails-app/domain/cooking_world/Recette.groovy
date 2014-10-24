package cooking_world

class Recette {
    String titre
    Byte[] photo
    String ingredients
    String description
    Integer tempsPreparation
    Integer tempsCuisson
    Date dateCreation
    String filename
    static belongsTo = [Utilisateur]
    static hasMany = [notes : Notes, coupDeCoeur : CoupDeCoeur]

    static constraints = {
        titre (nullable : false, blank : false)
        photo (nullable : true,maxSize: 16384 /* 16K */)
        tempsPreparation(min : 0)
        tempsCuisson (min : 0)
        ingredients (widget:'textarea',nullable : false, blank : false)
        description (widget:'textarea', nullable:false, blank:false)
        dateCreation()
        filename(blank:true, nullable:true,display:false)
    }

    @Override
    public String toString() {
        titre + "\n Temps de préparation : "+tempsPreparation+ "\n Temps de cuisson : "+tempsCuisson + "\n Ingrédients :" + ingredients + "\n"+description
    }
}
