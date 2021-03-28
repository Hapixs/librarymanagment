package fr.alexandresarouille.exceptions;

import javax.persistence.Entity;

/**
 * Exception called if an entity already exist in the data base
 */
public class EntityExistException extends Exception {

    public String printStackTrace(Class<Entity> clazz, int id, boolean printInConsole) {
        String s = printStackTrace(clazz);
        if(printInConsole) System.out.println(s  + "id: "+id);
        return s;
    }

    public String printStackTrace(Class<Entity> clazz) {
        return "Une erreur c'est produit il semblerais que le "+clazz.getSimpleName()+" exist déjà.";
    }
}
