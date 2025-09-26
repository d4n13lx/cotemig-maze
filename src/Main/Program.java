package Main;

import Fabric.Maze;
import Fabric.Vector2D;

public class Program {
    public static void main(String[] args) {
        int largura = 40;
        Vector2D inicio = new Vector2D();
        Vector2D alvo = new Vector2D(largura / 2,largura / 2);

        Maze maze = new Maze(largura, inicio, alvo);

        maze.draw();
    }
}
