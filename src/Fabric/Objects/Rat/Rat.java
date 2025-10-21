package Fabric.Objects.Rat;

import Fabric.Objects.*;
import Fabric.Types.UI;
import Fabric.World.Block;

import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Rat extends GameObject implements Runnable {

    private Block[][] blocks;
    private Winner winner;
    private long waitTime;
    private final UI ui;
    private Stack<History> history = new Stack<>();
    
    private int startX;
    private int startY;
    
    public Rat(Block[][] blocks, Winner winner, long waitTime, UI ui, int startX, int startY) {
        super();
        this.blocks = blocks;
        this.winner = winner;
        this.waitTime = waitTime;
        this.ui = ui;
        this.startX = startX;
        this.startY = startY;
        
        synchronized(blocks) {
        	if (this.blocks[startX][startY] == null) {
        		this.blocks[startX][startY] = new Block(new Floor());
        	}
        	this.blocks[startX][startY].getObjects().add(this);
        }
    }
    
    @Override
    public void run() {
    	this.solve();
    }
    
    public void solve() {
        if (winner.getWinner() != null) return;
        
        if (backtrack(this.startX, this.startY)) {
        	triggerEndGame();
        }
    }

    private boolean backtrack(final int x, final int y) {
    	if (winner.getWinner() != null) return false;

        for (Direction direction : randomizeMoves()) {
        	if (winner.getWinner() != null) return false;
        	
            int stepX = x + direction.x();
            int stepY = y + direction.y();
            
            GameObject nextStepObject;
            
            synchronized(blocks) {
            	nextStepObject = getLastObject(stepX, stepY);
            }
            
            if (nextStepObject instanceof Target) {
            	stepUpRat(stepX, stepY, direction);
            	render();
            	return true;
            }
            
            if (isSafe(stepX, stepY) && !isExplored(stepX, stepY)) {
                render();
                stepUpRat(stepX, stepY, direction);

                if (backtrack(stepX, stepY)) return true;
                
                if (winner.getWinner() != null) return false;

                render();
                stepBackRat(x, y);
            }
        }
        return false;
    }

    private boolean isSafe(final int x, final int y) {
        boolean isOnBounds = x >= 1 && x < getRowLength() - 1 && y >= 1 && y < getCollumLength() - 1;
        if (!isOnBounds) return false;
        
        boolean isBlocked;
        synchronized(blocks) {
        	GameObject topObject = getLastObject(x, y);
        	
        	isBlocked = (topObject instanceof Wall) || (topObject instanceof Rat) ||
        			(topObject instanceof Path && ((Path)topObject).getRat() != this);
        }

        return !isBlocked;
    }

    private boolean isExplored(final int x, final int y) {
        List<GameObject> objectsCopy;
        
        synchronized(blocks) {
        	Block block = blocks[x][y];
        	
        	if (block == null) return false;
        	
        	objectsCopy = new ArrayList<>(block.getObjects());
        }
        
        for (GameObject object : objectsCopy) {
        	if (object instanceof Path) {
        		if (((Path) object).getRat() == this) return true;
        	}
        }
        return false;
    }
    
    private GameObject getLastObject(final int x, final int y) {
    	Block block = blocks[x][y];
    	
    	if (block == null || block.getObjects().isEmpty()) {
    		return null;
    	}
    	
    	return block.getObjects().getLast();
    }

    
    private GameObject getTopObject(final int x, final int y) {
    	if (blocks[x][y] == null) {
    		return null;
    	}
    	
    	if (blocks[x][y].getObjects().isEmpty()) {
    		return null;
    	}
    	
        return blocks[x][y].getObjects().getFirst();
    }

    private GameObject getBottomObject(final int x, final int y) {
        if (blocks[x][y] == null) {
        	return null;
        }
        
        if (blocks[x][y].getObjects().isEmpty()) {
        	return null;
        }
        
    	return blocks[x][y].getObjects().getLast();
    }

    private void triggerEndGame() {
        synchronized (winner) {
        	winner.setWinner(this);
        }
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
            // e.printStackTrace();
        	Thread.currentThread().interrupt();
        }
    }

    private void stepUpRat(final int x, final int y, Direction direction) {
        int stepX = x - direction.x();
        int stepY = y - direction.y();

        Path path = new Path(this);
        history.push(new History(x, y, path));

        blocks[stepX][stepY].getObjects().remove(this);
        blocks[stepX][stepY].getObjects().add(path);
        
        if (blocks[x][y] == null) {
        	blocks[x][y] = new Block(new Floor());
        }
        
        blocks[x][y].getObjects().add(this);
    }

    private void stepBackRat(final int oldX, final int oldY) {
        History back = history.pop();
        
        blocks[back.x()][back.y()].getObjects().remove(this);
        blocks[oldX][oldY].getObjects().remove(back.path());
        blocks[oldX][oldY].getObjects().add(this);
    }

    private int getRowLength() {
        return blocks.length;
    }

    private int getCollumLength() {
        return blocks[0].length;
    }

    private void render() {
    	synchronized(blocks) {
    		ui.clear();
            ui.draw();
    	}
        
        freeze();
    }

    public int getStepsTaken() {
        return this.history.size();
    }

}

