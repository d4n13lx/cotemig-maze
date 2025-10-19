package Fabric.Objects;

import Fabric.Types.UI;

import java.util.UUID;

public abstract class GameObject {

    protected UUID id;

    public GameObject() {
        this.id = UUID.randomUUID();
    }

    public UUID getUUID() {
        return id;
    }
}


