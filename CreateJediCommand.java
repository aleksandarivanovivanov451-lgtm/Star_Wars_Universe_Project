public class CreateJediCommand implements Command {
    @Override
    public void execute(String[] tokens, Universe universe) throws Exception {
        if (tokens.length < 7) throw new Exception("Usage: create_jedi <planet> <name> <age> <color> <strength> <rank>");
        Planet p  = universe.findPlanet(tokens[1]);
        if (p == null) throw new Exception("Planet not found: " + tokens[1]);
        if (!universe.isJediNameUnique(tokens[2])) throw new Exception("Jedi name must be unique!");
        Rank r = Rank.valueOf(tokens[6].toUpperCase());
        Jedi newJedi = new Jedi(tokens[2], Integer.parseInt(tokens[3]), tokens[4], Double.parseDouble(tokens[5]), r);
        p.addJedi(newJedi);
        System.out.println("Jedi " + tokens[2] + "added to " + tokens[1]);
    }
}
