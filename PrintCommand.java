public class PrintCommand implements Command {
    @Override
    public String execute(String[] tokens, Universe universe) throws Exception {
        if (tokens.length < 2) throw new Exception("Usage: print <planet_name>");

        Planet p = universe.findPlanet(tokens[1]);
        if (p == null) throw new Exception("Planet not found.");

        return p.getPlanetInfo();
    }
}