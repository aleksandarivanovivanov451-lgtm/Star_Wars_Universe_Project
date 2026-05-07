import java.util.List;

public class MergePlanetsCommand implements Command {
    @Override
    public String execute(String[] tokens, Universe universe) throws Exception {
        if (tokens.length < 3) throw new Exception("Usage: merge_planets <p1> <p2>");
        List<Jedi> merged = universe.mergePlanet(tokens[1], tokens[2]);
        StringBuilder sb = new StringBuilder("Merged Jedi:\n");
        for (Jedi j : merged) sb.append(j.toString()).append("\n");
        return sb.toString();
    }
}
