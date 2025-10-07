package Fabric.World;

import Fabric.Objects.Path;
import Fabric.Objects.Target;
import Fabric.Objects.Wall;
import Fabric.Types.UI;

public class Maze {

    public Cell[][] cells;
    private final UI ui;

    public Maze(Cell[][] cells, UI ui){
        this.cells = cells;
        this.ui = ui;
    }

    public Cell getCell(int i, int j) {
        return cells[i][j];
    }

    public int getWidth(){
        return cells[0].length;
    }

    public int getHeight(){
        return cells[1].length;
    }

    public void generateMaze() {
        // Limpar a tela caso tenha alguma coisa
        ui.clear();


        /* TODO: implementar algoritmo de geração do labirinto
         * Precisa spawnar o caçador no canto esquerdo superior do labirinto
         * Precisa spawnar o alvo no canto direito inferior do labirinto
         */

        // Geração de labirinto de teste
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                if (i == 0 || j == 0 || i == getWidth() - 1 || j == getHeight() - 1) {
                    cells[i][j] = new Cell(new Path(ui, this));
                } else {
                    cells[i][j] = new Cell(new Wall(ui, this));
                }
            }
        }

        // Depois que terminar a geração do labirinto


        // Definição da posição do alvo
        cells[getWidth() - 2][getHeight() - 2] = new Cell(new Target(ui, this));

        // Desenhar a tela quando tudo acabar
        ui.draw();
    }

}
