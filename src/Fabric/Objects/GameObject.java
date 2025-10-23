package Fabric.Objects;

public abstract class GameObject {

    protected int id;
    private static int increment = 1;

    public GameObject() {
        this.id = increment++;
    }

    public int getID() {
        return id;
    }
}


