package Main;

import Fabric.Objects.Rat.Rat;
import Fabric.Objects.Winner;
import Fabric.UI.Console;
import Fabric.World.Block;
import Fabric.World.MazeGenerator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Program {
    public static void main(String[] args) {
    	final int MAZE_HEIGHT = Integer.parseInt(JOptionPane.showInputDialog(
				"Digite a altura do labirinto (blocos).\n\n" +
				"OBS.: O número precisa ser ímpar, se a entrada for par,\nserá incrementada +1 no entrada"));

    	final int MAZE_WIDTH = Integer.parseInt(JOptionPane.showInputDialog(
				"Digite a largura do labirinto (blocos).\n\n" +
				"OBS.: O número precisa ser ímpar, se a entrada for par,\nserá incrementada +1 no entrada"));

    	final int N_RATS = Integer.parseInt(JOptionPane.showInputDialog("Quantos ratos irão para o gulag?"));

    	final long WAIT_TIME = 1000;
    	
        Block[][] blocks = new Block[MAZE_HEIGHT][MAZE_WIDTH];
        Console ui = new Console(blocks);
        Winner winner = new Winner();
        Random rand = new Random();

        List<int[]> availableFloorTiles = new ArrayList<>();
        MazeGenerator.buildMaze(blocks, availableFloorTiles);
        
        List<Thread> ratThreads = new ArrayList<>();
        
        System.out.println("Número de ratos: " + N_RATS);
        
        for (int i = 0; i < N_RATS; i++) {
        	int[] startPos = availableFloorTiles.remove(rand.nextInt(availableFloorTiles.size()));
        	
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
        
        if (winner.getWinner() != null) {
			JOptionPane.showMessageDialog(null, "	BATTLE ROYALE #1\n\n" +
					"O rato " + winner.getWinner().getID() + " achou o queijo!\n" +
					"Ele deu " + winner.getWinner().getStepsTaken() + " passo(s) para a vitória.");
        } else {
        	System.out.println("Nenhum rato encontrou o queijo");
        }
    }
}
