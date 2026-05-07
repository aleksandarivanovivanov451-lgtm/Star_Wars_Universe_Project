public class YoungestJediCommand implements Command {
    @Override
    public String execute(String[] tokens, Universe universe) throws Exception {
        if (tokens.length < 3) throw new Exception("Usage: get_youngest_jedi <planet_name> <rank>");

        Planet p = universe.findPlanet(tokens[1]);
        if (p == null) throw new Exception("Planet not found.");

        Rank targetRank = Rank.valueOf(tokens[2].toUpperCase());
        Jedi youngest = p.getYoungestJedi(targetRank);

        if (youngest == null) return "No Jedi with rank " + targetRank + " on planet " + tokens[1];
        return "Youngest " + targetRank + " on " + tokens[1] + ": " + youngest.getName() + " (Age: " + youngest.getAge() + ")";
    }
}
