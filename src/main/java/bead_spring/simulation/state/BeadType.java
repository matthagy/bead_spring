package bead_spring.simulation.state;

import com.google.common.base.Preconditions;

import java.util.Objects;

public class BeadType {

    private final String name;
    private final double radius;
    private final String color;

    public BeadType(String name, double radius, String color) {
        this.name = Preconditions.checkNotNull(name);
        Preconditions.checkArgument(radius > 0);
        this.radius = radius;
        this.color = Preconditions.checkNotNull(color);
    }

    public String getName() {
        return name;
    }

    public double getRadius() {
        return radius;
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeadType beadType = (BeadType) o;
        return Double.compare(beadType.radius, radius) == 0 &&
                name.equals(beadType.name) &&
                color.equals(beadType.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, radius, color);
    }
}
