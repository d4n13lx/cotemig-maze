package Fabric.Objects;

import Fabric.Types.UI;
import Fabric.World.AvailablePath;
import Fabric.World.Block;

import java.util.Stack;

public class Rat extends GameObject {

    private Stack<AvailablePath> history;
    private Block[][] blocks;

    public Rat(Block[][] blocks) {
        super();
        this.blocks = blocks;
        spawn();
    }

    private void spawn() {
        this.blocks[1][1].add(this);
        history = new Stack<>();
    }

    public void scan() {

    }

    public void move() {

    }

    public void backtrack() {

    }
}

