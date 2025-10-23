package Fabric.Objects;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class GameObject {

    protected int id;
    private static final AtomicInteger increment = new AtomicInteger(1);

    public GameObject() {
        this.id = increment.getAndIncrement();
    }

    public int getID() {
        return id;
    }
}


