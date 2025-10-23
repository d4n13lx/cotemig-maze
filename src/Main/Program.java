package Main;

import Fabric.Objects.Rat.Rat;
import Fabric.Objects.Target;
import Fabric.Objects.Winner;
import Fabric.UI.Console;
import Fabric.World.Block;
import Fabric.World.Maze;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a altura do labirinto (blocos).\n> ");

    	final int MAZE_HEIGHT = scanner.nextInt();

        System.out.print("Digite a largura do labirinto (blocos).\n> ");

    	final int MAZE_WIDTH = scanner.nextInt();

        System.out.println("Quantos ratos irão para o gulag?");

    	final int N_RATS = scanner.nextInt();

    	final long WAIT_TIME = 300;
    	
        Maze maze = new Maze(MAZE_HEIGHT, MAZE_WIDTH);
        Console ui = new Console(maze);
        Winner winner = new Winner();
        Random rand = new Random();

        List<int[]> availableFloorTiles = new ArrayList<>();
        maze.buildMaze(availableFloorTiles);
        
        List<Thread> ratThreads = new ArrayList<>();
        
        System.out.println("Número de ratos: " + N_RATS);
        
        for (int i = 0; i < N_RATS; i++) {
        	int[] startPos = availableFloorTiles.remove(rand.nextInt(availableFloorTiles.size()));
        	
        	if (maze.getTopObject(startPos[0], startPos[1]) instanceof Target) {
        		i--;
        		continue;
        	}
        	
        	Rat rato = new Rat(maze, winner, WAIT_TIME, ui, startPos[0], startPos[1]);
        	
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
