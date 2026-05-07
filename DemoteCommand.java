public class DemoteCommand implements Command {
    @Override
    public String execute(String[] tokens, Universe universe) throws Exception {
        if (tokens.length < 3) throw new Exception("Usage: demote <jedi_name> <multiplier>");

        String name = tokens[1];
        double multiplier = Double.parseDouble(tokens[2]);

        for (Planet p : universe.getPlanets()) {
            for (Jedi j : p.getJediList()) {
                if (j.getName().equalsIgnoreCase(name)) {
                    j.demote(multiplier);
                    return "Jedi " + name + " demoted to " + j.getRank() + ". New strength: " + j.getStrength();
                }
            }
        }
        throw new Exception("Jedi with name " + name + " not found.");
    }
}