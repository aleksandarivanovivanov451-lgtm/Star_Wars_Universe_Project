public class RemoveJediCommand implements Command {
    @Override
    public void execute(String[] tokens, Universe universe) throws Exception {
        if (tokens.length<3) throw new Exception("Usage: remove_jedi <name> <planet>");
        Planet p = universe.findPlanet(tokens[2]);
        if (p==null ) throw new Exception("Planet not found");
        p.removeJedi(tokens[1]);
        System.out.println("Jedi " + tokens[1] + "remove from " + tokens[2]);
    }
}
