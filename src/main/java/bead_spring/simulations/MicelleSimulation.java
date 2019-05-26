package bead_spring.simulations;

import bead_spring.simulation.state.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MicelleSimulation {

    private static final int COPOLYMER_HYDROPHILIC_BEADS = 10;
    private static final int COPOLYMER_HYDROPHOBIC_BEADS = 5;

    private static final BeadType HYDROPHILIC_BEAD_TYPE = new BeadType("hydrophilic", 0.5);
    private static final BeadType HYDROPHOBIC_BEAD_TYPE = new BeadType("hydrophobic", 0.5);
    private static final BeadType SOLVENT_BEAD_TYPE = new BeadType("solvent", 0.5);

    private static final SpringType SPRING_TYPE = new SpringType(1.2, 0.1);

    private static final int LATERAL_BOX_SIZE = 20;

    public static void main(String[] args) throws Exception {
        final List<LinearPolymer> polymers = new ArrayList<>();
        final List<Bead> solvents = new ArrayList<>();
        createBeads(polymers, solvents);

        final List<Bead> allBeads = new ArrayList<>();
        allBeads.addAll(solvents);
        polymers.stream().flatMap(p -> p.getBeads().stream()).forEach(allBeads::add);

        Configuration configuration = new Configuration(allBeads);

        dumpBeads(0, polymers, solvents);
    }

    private static void createBeads(List<LinearPolymer> polymers, List<Bead> solvents) {
        for (int x = 0; x < LATERAL_BOX_SIZE; x += 2) {
            for (int y = 0; y < LATERAL_BOX_SIZE; y += 2) {
                polymers.add(createBlockCopolymer(x, y));
                for (int z = COPOLYMER_HYDROPHILIC_BEADS + COPOLYMER_HYDROPHOBIC_BEADS;
                     z < LATERAL_BOX_SIZE; z += 1) {
                    solvents.add(new Bead(SOLVENT_BEAD_TYPE, new Vector(x, y, z)));
                }
            }
        }
        for (int x = 0; x < 20; x += 1) {
            for (int y = 0; y < 20; y += 1) {
                if (x % 2 == 0 && y % 2 == 0) continue;
                for (int z = 0; z < LATERAL_BOX_SIZE; z += 1) {
                    solvents.add(new Bead(SOLVENT_BEAD_TYPE, new Vector(x, y, z)));
                }
            }
        }
    }

    private static LinearPolymer createBlockCopolymer(double x, double y) {
        final List<Bead> beads = new ArrayList<>(COPOLYMER_HYDROPHILIC_BEADS + COPOLYMER_HYDROPHOBIC_BEADS);
        IntStream.range(0, COPOLYMER_HYDROPHILIC_BEADS).forEach(i -> {
            beads.add(new Bead(HYDROPHILIC_BEAD_TYPE, new Vector(x, y, i)));
        });
        IntStream.range(0, COPOLYMER_HYDROPHOBIC_BEADS).forEach(i -> {
            beads.add(new Bead(HYDROPHOBIC_BEAD_TYPE, new Vector(x, y, i + COPOLYMER_HYDROPHILIC_BEADS)));
        });
        return LinearPolymer.link(beads, SPRING_TYPE);
    }

    private static void dumpBeads(int steps, List<LinearPolymer> polymers, List<Bead> solvents) {
        JSONObject jsonConfiguration = new JSONObject();
        jsonConfiguration.put("steps", steps);
        jsonConfiguration.put("polymers", createArray(
                polymers.stream().map(p -> createArray(p.getBeads().stream()
                        .map(MicelleSimulation::outputBead)))));
        jsonConfiguration.put("solvents", createArray(solvents.stream()
                .map(MicelleSimulation::outputBead)));
        System.out.println(jsonConfiguration.toJSONString());
    }

    private static JSONArray createArray(Stream stream) {
        final JSONArray arr = new JSONArray();
        stream.forEach(arr::add);
        return arr;
    }

    private static JSONObject outputBead(Bead bead) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("x", bead.getCoordinates().getX());
        jsonObject.put("y", bead.getCoordinates().getY());
        jsonObject.put("z", bead.getCoordinates().getZ());
        jsonObject.put("type", bead.getType().getName());
        return jsonObject;
    }
}
