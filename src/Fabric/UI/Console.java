package Fabric.UI;

import Fabric.Types.Element;
import Fabric.Types.UI;
import Fabric.World.Maze;

public class Console implements UI {
    Maze maze;

    public Console(Maze maze) {
        this.maze = maze;
    }

    @Override
    public void draw() {
        clear();
        String canvas = "";
        for (int i = 0; i < maze.getWidth(); i++) {
            for (int j = 0; j < maze.getHeight(); j++) {
                switch (maze.getCell(i, j)) {
                    default -> {}
                    case Element.WALL -> canvas += "⬛";
                    case Element.PATH -> canvas += "▪️";
                    case Element.TARGET -> canvas += "🧀";
                    case Element.HUNTER -> canvas += "🐀";
                }
            }
            System.out.println(canvas);
            canvas = "";
        }
    }

    @Override
    public void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
