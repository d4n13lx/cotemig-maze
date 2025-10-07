package Fabric.World;

import Fabric.Objects.GameObject;
import Fabric.Objects.Wall;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    // Nesta lógica, um Cell é capaz de armazenar vários objetos do tipo GameObject

    // Uma matriz de Cell (ex. `Cell[][]`) já é o labirinto

    List<GameObject> objects = new ArrayList<>();

    public Cell(GameObject gameObject) {
        add(gameObject);
    }

    public Cell(GameObject[] gameObject) {
        for (GameObject g : gameObject) {
            add(g);
        }
    }

    public void add(GameObject gameObject) {
        objects.add(gameObject);
    }

    public void remove(GameObject gameObject) {
        objects.remove(gameObject);
    }

    public boolean isPath() {
        return !isWall();
    }

    public boolean isWall() {
        for (GameObject g : objects) {
            if (g instanceof Wall) {
                return true;
            }
        }

        return false;
    }
}
