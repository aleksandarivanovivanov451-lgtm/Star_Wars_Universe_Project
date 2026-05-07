public class GMSaberColorCommand implements Command {
    @Override
    public String execute(String[] tokens, Universe universe) throws Exception {
        if (tokens.length < 2) throw new Exception("Usage: get_most_used_saber_color_grandmaster <planet_name>");

        Planet p = universe.findPlanet(tokens[1]);
        if (p == null) throw new Exception("Planet not found.");

        String color = p.getMostUsedSaberColorForGrandMaster();
        return "The Grand Masters on " + tokens[1] + " mostly use " + color + " sabers.";
    }
}