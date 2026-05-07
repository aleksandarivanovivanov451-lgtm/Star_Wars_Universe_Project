public class OpenCommand implements Command {
    @Override
    public String execute(String[] tokens, Universe universe) throws Exception {
        if (tokens.length < 2) throw new Exception("Usage: open <file_path>");
        universe.loadData(tokens[1]);
        universe.setFileOpened(true, tokens[1]);
        return "Successfully opened " + tokens[1];
    }
}
