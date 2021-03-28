package fr.alexandresarouille.exceptions;

/**
 * Exception called if an entity already exist in the data base
 */
public class EntityExistException extends Exception {

    public EntityExistException(Class clazz, int id) {
        this.clazz = clazz;
        this.id = id;
    }

    private Class clazz;
    private int id;

    public void printError() {
        System.out.println(getError() + "id: "+id);
    }

    public String getError() {
        return "Une erreur c'est produit il semblerais que le "+clazz.getSimpleName()+" exist déjà.";
    }
}
