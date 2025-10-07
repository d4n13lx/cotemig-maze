package Fabric;

import Fabric.Enums.Element;
import Fabric.Objects.Path;
import Fabric.Objects.Rat;
import Fabric.Objects.Target;
import Fabric.Objects.Wall;
import Fabric.UI.UI;

public class Maze {

    Cell[][] cells;
    UI ui;

    public Maze(Cell[][] cells, UI ui){
        this.cells = cells;
        this.ui = ui;
    }

    public Cell getCell(int i, int j) {
        return cells[i][j];
    }

    public int getWidth(){
        return cells.length;
    }

    public int getHeight(){
        return cells[0].length;
    }

    public void generateMaze() {
        // Limpar a tela caso tenha alguma coisa
        ui.clear();


        /* TODO: implementar algoritmo de geração do labirinto
         * Precisa spawnar o caçador no canto esquerdo superior do labirinto
         * Precisa spawnar o alvo no canto direito inferior do labirinto
         */

        // lógica de geração de labirinto
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                if (j >= 1 && j <= getHeight() - 2 & i >= 1 && i <= getWidth() - 2) {
                    cells[i][j] = new Cell(new Path());
                } else {
                    cells[i][j] = new Cell(new Wall());
                }
            }
        }

        // Depois que terminar a geração do labirinto

        // Definição da posição do alvo
        cells[getWidth() - 2][getHeight() - 2] = new Cell(new Target());

        // Desenhar a tela quando tudo acabar

        ui.draw();
    }

}
