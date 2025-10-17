package Main;

import Fabric.Objects.Rat.Rat;
import Fabric.Objects.Winner;
import Fabric.UI.Console;
import Fabric.World.Block;
import Fabric.World.MazeGenerator;

public class Program {
    public static void main(String[] args) {
        Block[][] blocks = new Block[11][11];

        Console ui = new Console(blocks);

        MazeGenerator.buildMaze(blocks);

        long waitTime = 750;

        Winner winner = new Winner();

        Rat rato = new Rat(blocks, winner, waitTime, ui);

        rato.solve();
    }
}
