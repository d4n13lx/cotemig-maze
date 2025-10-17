package Fabric.Objects.Rat;

public enum Direction {
    UP(-1, 0) {
        public Direction opposite() {
            return DOWN;
        }
    },

    DOWN(1, 0) {
        public Direction opposite() {
            return UP;
        }
    },

    LEFT(0, 1) {
        public Direction opposite() {
            return RIGHT;
        }
    },
    RIGHT
            (0, -1) {
        public Direction opposite() {
            return LEFT;
        }
    };

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
}

