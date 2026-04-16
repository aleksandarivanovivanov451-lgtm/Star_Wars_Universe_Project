public class SaveCommand implements Command {
    @Override
    public void execute(String[] tokens, Universe universe) throws Exception {
        universe.saveData(universe.getCurrentFilePath());
        System.out.println("Successfully saved to " + universe.getCurrentFilePath());
    }
}
