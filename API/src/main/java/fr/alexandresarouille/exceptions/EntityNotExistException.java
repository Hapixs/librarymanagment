package fr.alexandresarouille.exceptions;

import javax.persistence.Entity;

public class EntityNotExistException extends Exception {

    public EntityNotExistException(Class<Entity> clazz, int id) {
        this.clazz = clazz;
        this.id = id;
    }

    private Class<Entity> clazz;
    private int id;

    public void printError() {
        System.out.println(getError() + "id: "+id);
    }

    public String getError() {
        return "Une erreur c'est produit il semblerais que le "+clazz.getSimpleName()+" exist déjà.";
    }
}
