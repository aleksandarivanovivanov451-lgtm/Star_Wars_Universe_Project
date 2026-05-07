public class SaveCommand implements Command {
    @Override
    public String execute(String[] tokens, Universe universe) throws Exception {
        if (!universe.isFileOpened()) throw new Exception("No file is currently open.");
        universe.saveData(universe.getCurrentFilePath());
        return "Successfully saved to " + universe.getCurrentFilePath();
    }
}