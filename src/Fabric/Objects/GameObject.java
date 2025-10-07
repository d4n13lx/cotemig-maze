package Fabric.Objects;

import Fabric.Types.UI;
import Fabric.World.Maze;

import java.util.UUID;

public abstract class GameObject {

    protected UUID id;

    protected UI ui;
    protected Maze maze;

    public GameObject(UI ui, Maze maze) {
        this.ui = ui;
        this.maze = maze;
        this.id = UUID.randomUUID();
    }

    public GameObject() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }
}


