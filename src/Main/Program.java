package Main;

import Fabric.Objects.Rat.Rat;
import Fabric.Objects.Winner;
import Fabric.UI.Console;
import Fabric.World.Block;
import Fabric.World.MazeGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Program {
    public static void main(String[] args) {
    	final int MAZE_HEIGHT = 11; // Números ímpares funciona melhor para o DFS
    	final int MAZE_WIDTH = 25;
    	
    	final int N_RATS = 3;
    	final long WAIT_TIME = 250;
    	
        Block[][] blocks = new Block[MAZE_HEIGHT][MAZE_WIDTH];
        Console ui = new Console(blocks);
        Winner winner = new Winner();
        Random rand = new Random();
        
        MazeGenerator.buildMaze(blocks);

        List<int[]> floorTiles = new ArrayList<>();
        for (int i = 1; i < MAZE_HEIGHT - 1; i++) {
        	for (int j = 1; j < MAZE_WIDTH - 1; j++) {
        		if (blocks[i][j].isPath()) {
        			floorTiles.add(new int[] {i, j});
        		}
        	}
        }
        
        List<Thread> ratThreads = new ArrayList<>();
        
        System.out.println("Número de ratos: " + N_RATS);
        
        for (int i = 0; i < N_RATS; i++) {
        	int[] startPos = floorTiles.remove(rand.nextInt(floorTiles.size()));
        	
        	if (blocks[startPos[0]][startPos[1]].getObjects().getFirst() instanceof Fabric.Objects.Target) {
        		i--;
        		continue;
        	}
        	
        	Rat rato = new Rat(blocks, winner, WAIT_TIME, ui, startPos[0], startPos[1]);
        	
        	Thread ratThread = new Thread(rato);
        	ratThreads.add(ratThread);
        	ratThread.start();
        }
        
        for (Thread t : ratThreads) {
        	try {
        		t.join();
        	} catch (InterruptedException e) {
        		e.printStackTrace();
        	}
        }
        
        System.out.println("A simulação terminou");
        
        if (winner.getWinner() != null) {
        	System.out.println("O vencedor foi: " + winner.getWinner().getUUID());
        } else {
        	System.out.println("Nenhum rato encontrou o queijo");
        }
    }
}
