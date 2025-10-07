package Fabric.Objects;

import Fabric.Types.UI;

import java.util.UUID;

public abstract class GameObject {


    protected UUID id;
    protected UI ui;
    protected int x;
    protected int y;

    public GameObject() {
        this.id = UUID.randomUUID();
    }

    public GameObject(int x, int y) {
        this.id = UUID.randomUUID();
        this.x = x;
        this.y = y;
    }

    public UUID getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}


