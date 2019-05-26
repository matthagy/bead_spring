package bead_spring.simulation.state;

public class SpringType {

    private final double equilibriumLength;
    private final double springFactor;

    public SpringType(double equilibriumLength, double springFactor) {
        this.equilibriumLength = equilibriumLength;
        this.springFactor = springFactor;
    }

    public double getEquilibriumLength() {
        return equilibriumLength;
    }

    public double getSpringFactor() {
        return springFactor;
    }
}
