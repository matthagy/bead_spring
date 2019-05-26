package bead_spring.simulation.state;

import java.util.Collections;
import java.util.List;

public class LinearPolymer {

    private final List<Bead> beads;

    public LinearPolymer(List<Bead> beads) {
        this.beads = beads;
    }

    public List<Bead> getBeads() {
        return Collections.unmodifiableList(beads);
    }

    public static LinearPolymer link(List<Bead> beads, SpringType springType) {
        for (int i = 0; i < beads.size() - 1; i++) {
            beads.get(i).addSpring(beads.get(i + 1), springType);
        }
        return new LinearPolymer(beads);
    }
}
