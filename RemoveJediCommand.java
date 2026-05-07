public class RemoveJediCommand implements Command {
    @Override
    public String execute(String[] tokens, Universe universe) throws Exception {
        if (tokens.length < 3) throw new Exception("Usage: remove_jedi <jedi_name> <planet_name>");
        Planet p = universe.findPlanet(tokens[2]);
        if (p == null) throw new Exception("Planet not found.");
        p.removeJedi(tokens[1]);
        return "Jedi " + tokens[1] + " removed.";
    }
}
