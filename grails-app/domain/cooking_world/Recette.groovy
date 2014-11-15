package cooking_world

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Recette {
    String titre
    byte[] photo
    String ingredients
    String description
    Integer tempsPreparation
    Integer tempsCuisson
    Date dateCreation
    String filename
    static belongsTo = [utilisateur : Utilisateur]
    static hasMany = [notes : Notes, coupDeCoeur : CoupDeCoeur,commentaire:Commentaire]

    static constraints = {
        titre (nullable : false, blank : false)
        photo (nullable : true,maxSize: 1073741824 /*1GB*/)
        tempsPreparation(min : 0)
        tempsCuisson (min : 0)
        ingredients (widget:'textarea',nullable : false, blank : false)
        description (widget:'textarea', nullable:false, blank:false)
        dateCreation()
        filename(blank:true, nullable:true,display:false)
        utilisateur(nullable : false, blank: false)
    }

    @Override
    public String toString() {
        titre + "\n Temps de préparation : "+tempsPreparation+ "\n Temps de cuisson : "+tempsCuisson + "\n Ingrédients :" + ingredients + "\n"+description
    }
}
