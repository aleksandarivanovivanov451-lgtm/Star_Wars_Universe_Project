public class AddPlanetCommand implements Command {
    @Override
    public String execute(String[] tokens, Universe universe) throws Exception {
        if (tokens.length<2)throw new Exception("Usage: add_planet <name>");
        universe.addPlanet(tokens[1]);
        return "Planet " + tokens[1] + " added successfully.";
    }
}
