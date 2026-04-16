public class DemoteCommand implements Command {
    @Override
    public void execute(String[] tokens, Universe universe) throws Exception {
        if (tokens.length<3)throw new Exception("Usage: demote <name> <multiplier>");
        boolean found = false;
        for (Planet p : universe.getPlanets()){
            for (Jedi j : p.getJediList()){
                if (j.getName().equalsIgnoreCase(tokens[1])){
                    j.demote(Double.parseDouble(tokens[2]));
                    System.out.println(j.getName() + "demote to " + j.getRank());
                    found = true;
                    break;
                }
            }
        }
        if (!found)throw new Exception("Jedi not found");
    }
}
