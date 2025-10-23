package Fabric.UI;

import Fabric.Objects.*;
import Fabric.Objects.Rat.Rat;
import Fabric.World.Block;
import Fabric.World.Maze;

public class Console implements UI {

    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_CHEESE = "\u001B[33m";
    private final String ANSI_WALL = "\u001B[34m";
    private final String ANSI_RAT = "\u001B[37m";
    private Maze maze;
    private final StringBuilder canvas = new StringBuilder();

    public Console(Maze maze) {
        this.maze = maze;
    }

    @Override
    public void draw() {
        for (int i = 0; i < maze.heigth(); i++) {
            for (int j = 0; j < maze.width(); j++) {
            	
            	if (maze.getBlock(i, j) == null) {
            		canvas.append("█");
            		continue;
            	}

            	if (maze.getBlock(i, j).isEmpty()) {
            		canvas.append("  ");
            		continue;
            	}
            	
                GameObject object = maze.getBlock(i, j).getObjects().getLast();
                
                if (object instanceof Wall) {
                	canvas.append(ANSI_WALL + "█" + ANSI_RESET);
                } else if (object instanceof Floor) {
                	canvas.append(" ");
                } else if (object instanceof Rat) {
                    canvas.append(ANSI_RAT + "█" + ANSI_RESET);
                } else if (object instanceof Target) {
                    canvas.append(ANSI_CHEESE + "█" + ANSI_RESET);
                } else if (object instanceof Path) {
                	canvas.append("·");
                }
            }
            canvas.append("\n");
        }
        System.out.println(canvas);
    }

    @Override
    public void clear() {
        canvas.setLength(0);
        canvas.append("\n".repeat(50));
    }
}
