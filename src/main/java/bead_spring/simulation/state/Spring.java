package bead_spring.simulation.state;

import com.google.common.base.Preconditions;

public class Spring {
    private final Bead a;
    private final Bead b;
    private final SpringType springType;

    public Spring(Bead a, Bead b, SpringType springType) {
        Preconditions.checkNotNull(a);
        Preconditions.checkNotNull(b);
        Preconditions.checkNotNull(springType);
        Preconditions.checkArgument(a != b);
        this.a = a;
        this.b = b;
        this.springType = springType;
    }

    public Bead getA() {
        return a;
    }

    public Bead getB() {
        return b;
    }

    public Bead other(final Bead bead) {
        if (bead == a) {
            return b;
        } else if (bead==b) {
            return a;
        } else {
            throw new IllegalArgumentException("Bead not part of spring");
        }
    }
}
