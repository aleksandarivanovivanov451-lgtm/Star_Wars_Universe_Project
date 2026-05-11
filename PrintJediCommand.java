public class PrintJediCommand  implements Command {
    //команда за принтиране на джедай
    @Override
    public String execute(String[] tokens, Universe universe) throws Exception {
        if (tokens.length<2)throw new Exception("Usage: print_jedi <name>");
       return universe.printJedi(tokens[1]);
    }
}
