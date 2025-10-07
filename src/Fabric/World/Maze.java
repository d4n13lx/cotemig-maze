package Fabric.World;

import Fabric.Objects.GameObject;
import Fabric.Objects.Path;
import Fabric.Objects.Target;
import Fabric.Objects.Wall;
import Fabric.Types.UI;

import java.util.ArrayList;
import java.util.List;

public class Maze {

    private Block[][] blocks;

    public Maze(Block[][] blocks) {
        this.blocks = blocks;
    }

    public void buildTestMaze() {
        /* TODO: implementar algoritmo de geração do labirinto
         * Precisa spawnar o caçador no canto esquerdo superior do labirinto
         * Precisa spawnar o alvo no canto direito inferior do labirinto
         */

        // Geração de labirinto de teste
        for (int i = 0; i < blocks[0].length; i++) {
            for (int j = 0; j < blocks[1].length; j++) {
                if (i == 0 || j == 0 || i == blocks[0].length - 1 || j == blocks[1].length - 1) {
                    blocks[i][j] = new Block(new Path());
                } else {
                    blocks[i][j] = new Block(new Wall());
                }
            }
        }

        // Depois que terminar a geração do labirinto


        // Definição da posição do alvo
        blocks[blocks[0].length - 2][blocks[1].length - 2] = new Block(new Target());
    }

    public void buildMaze() {
        /* TODO: implementar algoritmo de geração do labirinto
         * Precisa spawnar o caçador no canto esquerdo superior do labirinto
         * Precisa spawnar o alvo no canto direito inferior do labirinto
         */
    }

}
