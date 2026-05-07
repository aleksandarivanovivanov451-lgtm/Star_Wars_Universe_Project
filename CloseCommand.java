public class CloseCommand implements Command {
    @Override
    public String execute(String[] tokens, Universe universe) throws Exception {
        universe.close();
       return "File is closed. Memory cleared" ;
    }
}
