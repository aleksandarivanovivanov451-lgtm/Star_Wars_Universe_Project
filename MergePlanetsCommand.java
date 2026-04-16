import java.util.List;

public class MergePlanetsCommand implements Command {
    @Override
    public void execute(String[] tokens, Universe universe) throws Exception {
        if (tokens.length<2) throw new Exception("Usage: merge_planets <p1> <p2>");
        List<Jedi> merge = universe.mergePlanet(tokens[1] , tokens[2]);
        System.out.println("--- Merged Jedi List (Sorted) ---");
        for (Jedi j : merge){
            System.out.println(j);
        }
    }
}
