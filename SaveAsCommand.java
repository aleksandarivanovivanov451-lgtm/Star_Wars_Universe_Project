public class SaveAsCommand implements Command {
    @Override
    public void execute(String[] tokens, Universe universe) throws Exception {
        if (tokens.length<2)throw new Exception("Usage: saveas <file_path>");
        String newPath = tokens[1];
        universe.saveData(newPath);
        universe.setFileOpened(true, newPath);
        System.out.println("Data successfully saved as " + newPath);
    }
}
