public class PrintJediCommand  implements Command {
    @Override
    public void execute(String[] tokens, Universe universe) throws Exception {
        if (tokens.length<2)throw new Exception("Usage: print_jedi <name>");
        System.out.println(universe.printJedi(tokens[1]));
    }
}
