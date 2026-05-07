public class CreateJediCommand implements Command {
    @Override
    public String execute(String[] tokens, Universe universe) throws Exception {
        if (tokens.length < 7) throw new Exception("Usage: create_jedi <planet> <name> <age> <saber_color> <power> <rank>");

        if (!universe.isJediNameUnique(tokens[2])) {
            throw new Exception("Jedi with name " + tokens[2] + " already exists!");
        }

        Planet p = universe.findPlanet(tokens[1]);
        if (p == null) throw new Exception("Planet " + tokens[1] + " not found.");

        Jedi jedi = new Jedi(
                tokens[2],
                Integer.parseInt(tokens[3]),
                tokens[4],
                Double.parseDouble(tokens[5]),
                Rank.valueOf(tokens[6].toUpperCase())
        );
        p.addJedi(jedi);

        return "Jedi " + tokens[2] + " was created on planet " + tokens[1];
    }
}