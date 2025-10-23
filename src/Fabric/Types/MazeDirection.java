package Fabric.Types;

public enum MazeDirection {
    N(0, -2),
    S(0, 2),
    E(2, 0),
    W(-2, 0);

    private final int x;
    private final int y;

    MazeDirection(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public int pathX() {
        return x / 2;
    }
    public int pathY() {
        return y / 2;
    }


}
