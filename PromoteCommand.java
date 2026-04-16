public class PromoteCommand implements Command{
    @Override
    public void execute(String[] tokens, Universe universe) throws Exception {
        if (tokens.length<3)throw new Exception("Usage: promote <name> <multiplier>");
        boolean found = false;
        for (Planet p : universe.getPlanets()){
            for (Jedi j : p.getJediList()){
                if (j.getName().equalsIgnoreCase(tokens[1])){
                    j.promote(Double.parseDouble(tokens[2]));
                    System.out.println(j.getName() + " promoted to " + j.getRank());
                    found = true;
                    break;
                }
            }
            if (found) break;
        }
        if (!found){
            System.out.println("Jedi not found");
        }
    }
}
