package fr.alexandresarouille.exceptions;

import javax.persistence.Entity;

public class EntityNotExistException extends Exception {
    public String printStackTrace(Class<Entity> clazz, int id, boolean printInConsole) {
        String s = printStackTrace(clazz);
        if(printInConsole) System.out.println(s  + "id: "+id);
        return s;
    }

    public String printStackTrace(Class<Entity> clazz) {
        return "Une erreur c'est produit il semblerais que le "+clazz.getSimpleName()+" n'existe pas.";
    }
}
