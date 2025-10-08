package Fabric.World;

public enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, 1),
    RIGHT(0, -1);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    public int xOpposite() {
        return this.x * -1;
    }
    public int yOpposite() {
        return this.y * -1;
    }
}

