public class SaveAsCommand implements Command {
    @Override
    public String execute(String[] tokens, Universe universe) throws Exception {
        if (tokens.length < 2) throw new Exception("Usage: saveas <file_path>");

        universe.saveData(tokens[1]);
        universe.setFileOpened(true, tokens[1]);
        return "Successfully saved as " + tokens[1];
    }
}