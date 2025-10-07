package Fabric;

import Fabric.Objects.Rat;
import Fabric.Objects.Target;
import Fabric.Types.UI;
import Fabric.World.Maze;

public class Game {
    UI ui;
    Maze maze;
    Rat[] rat;
    Target target;

    private int width;
    private int height;

    public void init() {

    }

    public void start() {

    }

    public void setUi(UI ui) {
        this.ui = ui;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public void setRat(Rat[] rat) {
        this.rat = rat;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
