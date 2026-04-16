public class YoungestJediCommand implements Command {
    @Override
    public void execute(String[] tokens, Universe universe) throws Exception {
        if (tokens.length<3)throw new Exception("Usage: get_youngest_jedi <planet_name> <rank>");
        Planet p = universe.findPlanet(tokens[1]);
        if (p == null)throw new Exception("Planet not found");
        try {
            Rank rank = Rank.valueOf(tokens[2].toUpperCase());
            Jedi youngest = p.getYoungestJedi(rank);
            if (youngest!=null)System.out.println("The youngest " + rank + " on " + tokens[1] + " is: " + youngest);
            else System.out.println("No Jedi with rank " + rank + " found on this planet.");
        }catch (IllegalArgumentException e){
            throw new Exception("Invalid rank! Available ranks: YOUNG_LING, PADAWAN, KNIGHT, MASTER, GRAND_MASTER");
        }
    }
}
