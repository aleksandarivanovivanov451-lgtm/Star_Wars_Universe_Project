public class SaberColorCommand implements Command {
    @Override
    public void execute(String[] tokens, Universe universe) throws Exception {
        if (tokens.length<3)throw new Exception("Usage: get_most_used_saber_color <planet> <rank>");
        Planet p = universe.findPlanet(tokens[1]);
        if (p == null) throw new Exception("Planet not found.");
        Rank r = Rank.valueOf(tokens[2].toUpperCase());
        System.out.println("Most used color: " + p.getMostUsedSaberColor(r));
    }
}
