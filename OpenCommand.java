public class OpenCommand implements Command {
    @Override
    public void execute(String[] tokens, Universe universe) throws Exception {
        if (tokens.length<2)throw new Exception("Usage: open <file_name>");
        String fileName = tokens[1];
        universe.loadData(fileName);
        universe.setFileOpened(true, fileName);
        System.out.println("Successfully opened " + fileName);
    }
}
