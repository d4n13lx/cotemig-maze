package Fabric.UI;

import Fabric.Objects.*;
import Fabric.Objects.Rat.Rat;
import Fabric.Types.UI;
import Fabric.World.Block;

public class Console implements UI {
    private Block[][] blocks;

    public Console(Block[][] blocks) {
        this.blocks = blocks;
    }

    @Override
    public void draw() {
        clear();
        String canvas = "";
        for (int i = 0; i < blocks[0].length; i++) {
            for (int j = 0; j < blocks[1].length; j++) {
                GameObject object = blocks[i][j].getObjects().getLast();
                if (object instanceof Wall) {
                    canvas += "⬛";
                } else if (object instanceof Floor) {
                    canvas += "▪️";
                } else if (object instanceof Rat) {
                    canvas += "🐀";
                } else if (object instanceof Target) {
                    canvas += "🧀";
                }
            }
            canvas += "\n";
        }
        System.out.println(canvas);
    }

    @Override
    public void clear() {
        System.out.print("\033[H\033[2J");
    }
}
