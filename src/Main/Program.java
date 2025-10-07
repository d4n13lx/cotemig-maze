package Main;

import Fabric.Objects.Rat;
import Fabric.UI.Console;
import Fabric.World.Block;
import Fabric.World.Maze;

public class Program {
    public static void main(String[] args) {
        Block[][] blocks = new Block[41][41];
        Console console = new Console(blocks);

        Maze maze = new Maze(blocks);
        maze.buildTestMaze();

        Rat rato = new Rat(blocks);

        rato.scan();
    }
}
