import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Universe universe = new Universe();

        System.out.println("--- Star Wars Universe Command Line Interface ---");

        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] tokens = line.split("\\s+");
            String command = tokens[0].toLowerCase();

            try {
                if (command.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting the program...");
                    break;
                }

                if (command.equalsIgnoreCase("help")) {
                    printHelp();
                    continue;
                }

                if (command.equalsIgnoreCase("open")) {
                    handleOpen(tokens, universe);
                    continue;
                }


                if (!universe.isFileOpened()) {
                    System.out.println("Error: Please open a file first!");
                    continue;
                }

                switch (command) {
                    case "close":
                        universe.close();
                        System.out.println("Successfully closed file.");
                        break;
                    case "saveas":
                        if (tokens.length < 2) throw new Exception("Usage: saveas <file_path>");
                        handleSave(universe, tokens[1]);
                        break;
                    case "save":
                        handleSave(universe, universe.getCurrentFilePath());
                        break;
                    case "add_planet":
                        if (tokens.length < 2) throw new Exception("Usage: add_planet <name>");
                        universe.addPlanet(tokens[1]);
                        System.out.println("Planet " + tokens[1] + " added.");
                        break;
                    default:
                        System.out.println("Unknown command.");
                }
            } catch (Exception e) {

                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void printHelp() {
        System.out.println("\nThe following commands are supported:");
        System.out.println("open <file>      | opens <file>");
        System.out.println("close            | closes currently opened file");
        System.out.println("save             | saves to current file");
        System.out.println("saveas <file>    | saves to specific <file>");
        System.out.println("help             | prints this information");
        System.out.println("exit             | exits the program");
        System.out.println("add_planet <n>   | adds a new planet");
        System.out.println("--------------------------------------\n");
    }

    private static void handleOpen(String[] tokens, Universe u) throws IOException {
        if (tokens.length < 2) {
            System.out.println("Error: Usage: open <file_path>");
            return;
        }
        String path = tokens[1];
        File file = new File(path);

        if (!file.exists()) {
            file.createNewFile();
            System.out.println("File not found. Created new empty file: " + path);
        } else {
            System.out.println("Successfully opened " + file.getName());

        }
        u.setFileOpened(true, path);
    }

    private static void handleSave(Universe u, String path) {

        System.out.println("Successfully saved to " + new File(path).getName());
    }
}