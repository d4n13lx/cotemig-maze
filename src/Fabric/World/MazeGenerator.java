package Fabric.World;

import Fabric.Objects.Floor;
import Fabric.Objects.Target;
import Fabric.Objects.Wall;

import java.util.Collections;
import java.util.Arrays;
import java.util.Random;

public class MazeGenerator {
	
	private enum MazeDirection {
		N(0, -2), S(0, 2), E(2, 0), W(-2, 0);
		
		final int x, y;
		final int pathX, pathY;
		
		MazeDirection(int x, int y) {
			this.x = x;
			this.y = y;
			this.pathX = x / 2;
			this.pathY = y / 2;
		}
	}
	
    public static void buildTestMaze(Block[][] blocks) {
        System.out.println("Building test maze...");
        System.out.println("Maze size: " +  blocks.length + "x" + blocks[0].length);
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                if (i == 0 || j == 0 || i == blocks.length - 1 || j == blocks[0].length - 1) {
                    blocks[i][j] = new Block(new Wall());
                } else {
                    blocks[i][j] = new Block(new Floor());
                }
            }
        }

        // Depois que terminar a geração do labirinto


        // Definição da posição do alvo
        blocks[blocks.length - 2][blocks[0].length - 2] = new Block(new Target());
    }

    public static void buildMaze(Block[][] blocks) {
        /* TODO: implementar algoritmo de geração do labirinto
         * Precisa spawnar o caçador no canto esquerdo superior do labirinto
         * Precisa spawnar o alvo no canto direito inferior do labirinto
         */
    	for (int i = 0; i < blocks.length; i++) {
    		for (int j = 0; j < blocks[0].length; j++) {
    			blocks[i][j] = new Block(new Wall());
    		}
    	}
    	
    	carvePassages(blocks, 1, 1, new Random());
    	
    	int targetX = blocks.length - 2;
    	int targetY = blocks[0].length - 2;
    	
    	if (targetX % 2 == 0) targetX--;
    	if (targetY % 2 == 0) targetY--;
    	
    	blocks[targetX][targetY] = new Block(new Target());
    }
    
    public static void carvePassages(Block[][] blocks, int x, int y, Random rand) {
    	blocks[x][y] = new Block(new Floor());
    	
    	MazeDirection[] directions = MazeDirection.values();
    	Collections.shuffle(Arrays.asList(directions), rand);
    	
    	for (MazeDirection dir : directions) {
    		int newX = x + dir.x;
    		int newY = y + dir.y;
    		
    		if (newX > 0 && newX < blocks.length - 1 && newY > 0 && newY < blocks[0].length - 1) {
    			
    			if (blocks[newX][newY].isWall()) {
    				
    				blocks[x + dir.pathX][y + dir.pathY] = new Block(new Floor());
    				
    				carvePassages(blocks, newX, newY, rand);
    			}
    		}
    	}
    }

}
