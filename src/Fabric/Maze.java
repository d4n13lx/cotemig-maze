package Fabric;

import java.util.Random;

public class Maze {

    int[][] maze;
    private int height;
    private int width;
    private Vector2D startPos;
    private Vector2D targerPos;
    private Random rand = new Random();

    private final int PATH = 0;
    private final int WALL = 1;
    private final int TARGET = 2;
    private final int CHOOSEN = 3;
    private final int TAKEN = 4;
    private final int INVALID = 5;
    private final int HORSE = 6;

    // main program usage
    public Maze(int w, int h, Vector2D s, Vector2D p){
        width = w;
        height = h;
        targerPos = p;
        startPos = s;
        generateMaze();
    }

    public Maze(int square, Vector2D s, Vector2D p){
        width = height = square;
        targerPos = p;
        startPos = s;
        generateMaze();
    }

    public void generateMaze() {
        maze = new int[height][width];
        // outter walls
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (j >= 1 && j <= height - 2 & i >= 1 && i <= width - 2) {
                    maze[i][j] = PATH;
                } else {
                    maze[i][j] = WALL;
                }
            }
        }

        // testing
        maze[20][20] = HORSE;
        maze[20][22] = TARGET;
        maze[20][24] = INVALID;
    }

    public void draw() {
        String canvas = "";
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                switch (maze[i][j]) {
                    case WALL -> canvas += "⬛";
                    case PATH -> canvas += "▪️";
                    case TARGET -> canvas += "🍎";
                    case INVALID -> canvas += "🔻";
                    case HORSE -> canvas += "🐴";
                    case CHOOSEN -> canvas += "🔹";
                    case TAKEN -> canvas += "🔸";
                }
            }
            System.out.println(canvas);
            canvas = "";
        }
    }


}
