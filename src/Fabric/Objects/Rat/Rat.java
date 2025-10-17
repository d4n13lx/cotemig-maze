package Fabric.Objects.Rat;

import Fabric.Objects.*;
import Fabric.Types.UI;
import Fabric.World.Block;

import javax.crypto.spec.PSource;
import javax.swing.JOptionPane;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Rat extends GameObject {

    private Block[][] blocks;
    private Winner winner;
    private long waitTime;
    private final UI ui;

    private Stack<History> history = new Stack<>();

    public Rat(Block[][] blocks, Winner winner, long waitTime, UI ui) {
        super();
        this.blocks = blocks;
        this.winner = winner;
        this.waitTime = waitTime;
        this.ui = ui;
        this.blocks[1][1] = new Block(this);
    }

    public void solve() {
        if (backtrack(1, 1)) {
            triggerEndGame();
            JOptionPane.showMessageDialog(null, "Queijo Achado!");
        } else {
            JOptionPane.showMessageDialog(null, "Labirinto Sem Solução");
        }
    }

    private boolean backtrack(final int x, final int y) {
        if (getTopObject(x, y) instanceof Target) return true;

        for (Direction direction : randomizeMoves()) {
            int stepX = x + direction.x();
            int stepY = y + direction.y();

            if (isSafe(stepX, stepY) && !isExplored(stepX, stepY)) {
                render();
                stepUpRat(stepX, stepY, direction);

                if (backtrack(stepX, stepY)) return true;

                render();
                stepBackRat(x, y);
            }
        }
        return false;
    }

    private boolean isSafe(final int x, final int y) {
        boolean isOnBounds = x >= 1 && x < getRowLength() - 1 && y >= 1 && y < getCollumLength() - 1;

        boolean isWall = getTopObject(x, y) instanceof Wall;

        return isOnBounds && !isWall;
    }

    private boolean isExplored(final int x, final int y) {
        for (GameObject object : getAllObjects(x, y)) {
            if (object instanceof Path) {
                if (((Path) object).getRat() == this) return true;
            }
        }
        return false;
    }

    private List<GameObject> getAllObjects(final int x, final int y) {
        return blocks[x][y].getObjects();
    }

    private GameObject getTopObject(final int x, final int y) {
        return blocks[x][y].getObjects().getFirst();
    }

    private GameObject getBottomObject(final int x, final int y) {
        return blocks[x][y].getObjects().getLast();
    }

    private void triggerEndGame() {
        this.winner.setWinner(this);
    }

    private Direction[] randomizeMoves() {
        List<Direction> directions = Arrays.asList(Direction.values());
        Direction[] availableDirections = new Direction[directions.size()];
        Collections.shuffle(directions);

        for (int i = 0; i < directions.size(); i++) {
            availableDirections[i] = directions.get(i);
        }

        return availableDirections;
    }

    private void freeze() {
        if (waitTime <= 0) return;

        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void stepUpRat(final int x, final int y, Direction direction) {
        int stepX = x - direction.x();
        int stepY = y - direction.y();

        Path path = new Path(this);
        history.push(new History(x, y, path));

        blocks[stepX][stepY].getObjects().remove(this);
        blocks[stepX][stepY].getObjects().add(path);
        blocks[x][y].getObjects().add(this);
    }

    private void stepBackRat(final int oldX, final int oldY) {
        History back = history.pop();
        blocks[oldX][oldY].getObjects().remove(history.peek().path());
        blocks[oldX][oldY].getObjects().add(this);

        blocks[back.x()][back.y()].getObjects().remove(this);
        blocks[back.x()][back.y()].getObjects().remove(back.path());
    }

    private int getRowLength() {
        return blocks.length;
    }

    private int getCollumLength() {
        return blocks[0].length;
    }

    private void render() {
        ui.clear();
        ui.draw();
        freeze();
    }

}

