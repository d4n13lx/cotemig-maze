package Fabric.UI;

import Fabric.Objects.*;
import Fabric.Objects.Rat.Rat;
import Fabric.Types.UI;
import Fabric.World.Block;

public class Console implements UI {

    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_CHEESE = "\u001B[33m";
    private final String ANSI_WALL = "\u001B[34m";
    private final String ANSI_RAT = "\u001B[37m";
    private Block[][] blocks;

    public Console(Block[][] blocks) {
        this.blocks = blocks;
    }

    @Override
    public void draw() {
        StringBuilder canvas = new StringBuilder();
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
            	
            	if (blocks[i][j] == null) {
            		canvas.append("█");
            		continue;
            	}
                
            	if (blocks[i][j].getObjects().isEmpty()) {
            		canvas.append("  ");
            		continue;
            	}
            	
                GameObject object = blocks[i][j].getObjects().getLast();
                
                if (object instanceof Wall) {
                	canvas.append(ANSI_WALL + "█" + ANSI_RESET);
                } else if (object instanceof Floor) {
                	canvas.append(" ");
                } else if (object instanceof Rat) {
                    canvas.append(ANSI_RAT + "█" + ANSI_RESET);
                } else if (object instanceof Target) {
                    canvas.append(ANSI_CHEESE + "█" + ANSI_RESET);
                } else if (object instanceof Path) {
                	canvas.append("-");
                } else {
                	canvas.append("█");
                }
            }
            canvas.append("\n");
        }
        System.out.println(canvas);
    }

    @Override
    public void clear() {
        StringBuilder canvas = new StringBuilder();
    	for (int i = 0; i < 50; i++) {
            canvas.append("\n");
    	}
        System.out.println(canvas);
    }
}
