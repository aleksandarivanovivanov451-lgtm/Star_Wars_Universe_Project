public class CloseCommand implements Command {
    @Override
    public void execute(String[] tokens, Universe universe) throws Exception {
        universe.close();
        System.out.println("File is closed. Memory cleared");
    }
}
