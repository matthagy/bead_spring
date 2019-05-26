package bead_spring.simulation.state;

import com.google.common.base.Preconditions;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Configuration {

    private final List<Bead> beads;

    public Configuration(List<Bead> beads) {
        this.beads = Preconditions.checkNotNull(beads);
    }

    public List<Bead> getBeads() {
        return Collections.unmodifiableList(beads);
    }

    public Set<Spring> getSprings() {
        return beads.stream().flatMap(b -> b.getSprings().stream()).collect(Collectors.toSet());
    }
}
