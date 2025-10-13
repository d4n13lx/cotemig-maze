package Fabric.World;

import Fabric.Objects.GameObject;
import Fabric.Objects.Wall;

import java.util.ArrayList;
import java.util.List;

public class Block {
    // Nesta lógica, um Block é capaz de armazenar vários objetos do tipo GameObject

    // Uma matriz de Block (ex. `Block[][]`) já é o labirinto

    private List<GameObject> objects = new ArrayList<>();

    public Block(GameObject gameObject) {
        add(gameObject);
    }

    public Block(GameObject[] gameObject) {
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

    public List<GameObject> getObjects() {
        return objects;
    }

    public GameObject getFirstObject() {
        return objects.getFirst();
    }

    public void removeAll() {
        objects.clear();
    }
}
