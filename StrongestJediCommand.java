public class StrongestJediCommand implements Command {
    @Override
    public void execute(String[] tokens, Universe universe) throws Exception {
        if (tokens.length<2)throw new Exception("Usage: get_strongest_jedi <planet>");
        Planet p = universe.findPlanet(tokens[1]);
        if (p == null)throw new Exception("Planet not found.");
        System.out.println("Strongest Jedi: "  + p.getStrongestJedi());
    }
}
