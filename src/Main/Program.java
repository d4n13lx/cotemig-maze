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

        MazeGenerator.buildTestMaze(blocks);

        long waitTime = 200;
        Winner winner = new Winner();

        Rat rato = new Rat(blocks, winner, waitTime, ui);

        rato.backtrack();

        System.out.println("ACHOU");
        winner.toString();


    }
}
