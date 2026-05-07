public class SaberColorCommand implements Command {
    @Override
    public String execute(String[] tokens, Universe universe) throws Exception {
        if (tokens.length < 3) throw new Exception("Usage: get_most_used_saber_color <planet_name> <rank>");

        Planet p = universe.findPlanet(tokens[1]);
        if (p == null) throw new Exception("Planet not found.");

        Rank targetRank = Rank.valueOf(tokens[2].toUpperCase());
        String color = p.getMostUsedSaberColor(targetRank);

        return "Most used saber color for " + targetRank + " on " + tokens[1] + " is: " + color;
    }
}
