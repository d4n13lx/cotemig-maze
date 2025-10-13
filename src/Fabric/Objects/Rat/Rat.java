package Fabric.Objects.Rat;

import Fabric.Objects.*;
import Fabric.Types.UI;
import Fabric.World.Block;

import java.awt.event.WindowStateListener;
import java.util.*;

public class Rat extends GameObject {

    private Block[][] blocks;
    private Winner winner;
    private long waitTime;
    private UI ui;

    private Stack<History> history;
    private int x, y;

    public Rat(Block[][] blocks, Winner winner, long waitTime, UI ui) {
        super();
        this.blocks = blocks;
        this.winner = winner;
        this.waitTime = waitTime;
        this.ui = ui;
        this.x = 1;
        this.y = 1;
        this.blocks[x][y].add(this);
        history = new Stack<>();

        this.init();
    }

    private void init() {
        history.add(new History(x, y));
    }

    public void backtrack() {
        if (winner.getWinner() != null) return;

        Direction[] moves = randomizeMoves();

        for (Direction direction : moves) {
            int directionsLeft = 4;
            int stepX = this.x + direction.x();
            int stepY = this.y + direction.y();
            if (getTopObject(stepX, stepY) instanceof Floor) {
                history.add(new History(stepX, stepY));

                blocks[x][y].remove(this);
                this.x = stepX;
                this.y = stepY;
                blocks[x][y].add(this);
                ui.clear();
                ui.draw();
                freeze();
                backtrack();
            }
            if (
                getTopObject(stepX, stepY) instanceof Wall
            ) {
                History backStep = history.pop();

                blocks[x][y].remove(this);

                this.x = backStep.x();
                this.y = backStep.y();

                blocks[x][y].add(this);

                ui.clear();
                ui.draw();
                freeze();
            } else {
                triggerEndGame();
                return;
            }
        }
    }

    private GameObject getTopObject(int x, int y) {
        return blocks[x][y].getFirstObject();
    }

    private GameObject getBottomObject(int x, int y) {
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
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

