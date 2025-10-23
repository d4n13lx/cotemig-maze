package Fabric.Types;

public enum RatDirection {
    UP(-1, 0) {
        public RatDirection opposite() {
            return DOWN;
        }
    },

    DOWN(1, 0) {
        public RatDirection opposite() {
            return UP;
        }
    },

    LEFT(0, 1) {
        public RatDirection opposite() {
            return RIGHT;
        }
    },
    RIGHT
            (0, -1) {
        public RatDirection opposite() {
            return LEFT;
        }
    };

    private final int x;
    private final int y;

    RatDirection(int x, int y) {
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

