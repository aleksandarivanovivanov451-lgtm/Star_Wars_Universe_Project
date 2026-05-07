public class PromoteCommand implements Command{
    @Override
    public String execute(String[] tokens, Universe universe) throws Exception {
        if (tokens.length < 3) throw new Exception("Usage: promote <jedi_name> <multiplier>");

        String name = tokens[1];
        double multiplier = Double.parseDouble(tokens[2]);

        for (Planet p : universe.getPlanets()) {
            for (Jedi j : p.getJediList()) {
                if (j.getName().equalsIgnoreCase(name)) {
                    j.promote(multiplier);
                    return "Jedi " + name + " promoted to " + j.getRank() + ". New strength: " + j.getStrength();
                }
            }
        }
        throw new Exception("Jedi with name " + name + " not found in the universe.");
    }
}
