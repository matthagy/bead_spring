package bead_spring.simulation.state;

import com.google.common.base.Preconditions;

import java.util.Objects;

public class BeadType {

    private final String name;
    private final double radius;

    public BeadType(String name, double radius) {
        this.name = Preconditions.checkNotNull(name);
        Preconditions.checkArgument(radius > 0);
        this.radius = radius;
    }

    public String getName() {
        return name;
    }

    public double getRadius() {
        return radius;
    }
}
