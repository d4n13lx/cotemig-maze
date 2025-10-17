package Fabric.World;

import Fabric.Objects.Floor;
import Fabric.Objects.Target;
import Fabric.Objects.Wall;

public class MazeGenerator {

    public static void buildTestMaze(Block[][] blocks) {
        System.out.println("Building test maze...");
        System.out.println("Maze size: " +  blocks.length + "x" + blocks[0].length);
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                if (i == 0 || j == 0 || i == blocks.length - 1 || j == blocks[0].length - 1) {
                    blocks[i][j] = new Block(new Wall());
                } else {
                    blocks[i][j] = new Block(new Floor());
                }
            }
        }

        // Depois que terminar a geração do labirinto


        // Definição da posição do alvo
        blocks[blocks.length - 2][blocks[0].length - 2] = new Block(new Target());
    }

    public static void buildMaze(Block[][] blocks) {
        /* TODO: implementar algoritmo de geração do labirinto
         * Precisa spawnar o caçador no canto esquerdo superior do labirinto
         * Precisa spawnar o alvo no canto direito inferior do labirinto
         */
    }

}
