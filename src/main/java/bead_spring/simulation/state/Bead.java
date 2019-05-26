package bead_spring.simulation.state;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Bead {

    private final BeadType type;
    private final Vector coordinates;
    private final List<Spring> springs = new ArrayList<>();

    private Vector force = Vector.ZERO;

    public Bead(BeadType type, Vector coordinates) {
        this.type = Preconditions.checkNotNull(type);
        this.coordinates = Preconditions.checkNotNull(coordinates);
    }

    public BeadType getType() {
        return type;
    }

    public Vector getCoordinates() {
        return coordinates;
    }

    public List<Spring> getSprings() {
        return Collections.unmodifiableList(springs);
    }

    public Set<Bead> bondedNeighbors() {
        return springs.stream().map(s -> s.other(this)).collect(Collectors.toSet());
    }

    public void addSpring(final Bead other, final SpringType springType) {
        Preconditions.checkNotNull(other);
        Preconditions.checkNotNull(springType);
        if (bondedNeighbors().contains(other)) {
            throw new IllegalArgumentException("already bonded to other bead");
        }
        Spring spring = new Spring(this, other, springType);
        springs.add(spring);
        other.springs.add(spring);
    }

    public void resetForce() {
        force = Vector.ZERO;
    }

    public void addForce(Vector incremental) {
        force = force.add(incremental);
    }

    public Vector getForce() {
        return force;
    }
}
