package Fabric.Objects;

import Fabric.Types.Element;
import Fabric.Types.UI;
import Fabric.World.AvailablePath;
import Fabric.World.Maze;

import java.util.Stack;

public class Rat extends GameObject {

    private Stack<AvailablePath> history;

    public Rat(UI ui, Maze maze) {
        super(ui, maze);
        maze.cells[1][1].add(this);

        history = new Stack<>();
    }

    public void scan() {

    }

    public void move() {

    }

    public void backtrack() {

    }
}

