package Fabric.Objects;

import Fabric.Objects.Rat.Rat;

public class Path extends GameObject {
    private Rat rat;

    public Path(Rat rat) { super(); this.rat = rat; }

    public Rat getRat() {
        return rat;
    }

    public void setRat(Rat rat) {
        this.rat = rat;
    }
}
