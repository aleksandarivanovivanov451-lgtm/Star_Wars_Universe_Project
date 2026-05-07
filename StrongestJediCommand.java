public class StrongestJediCommand implements Command {
    @Override
    public String execute(String[] tokens, Universe universe) throws Exception {
        if (tokens.length < 2) throw new Exception("Usage: get_strongest_jedi <planet>");
        Planet p = universe.findPlanet(tokens[1]);
        if (p == null) throw new Exception("Planet not found.");
        Jedi s = p.getStrongestJedi();
        return s == null ? "No Jedi." : "Strongest: " + s.toString();
    }
}
