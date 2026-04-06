import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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

                    case "save":
                        handleSave(universe, universe.getCurrentFilePath());
                        break;

                    case "saveas":
                        if (tokens.length < 2) throw new Exception("Usage: saveas <file_path>");
                        handleSave(universe, tokens[1]);
                        break;

                    case "add_planet":
                        if (tokens.length < 2) throw new Exception("Usage: add_planet <name>");
                        universe.addPlanet(tokens[1]);
                        System.out.println("Planet " + tokens[1] + " added.");
                        break;

                    case "create_jedi":
                        if (tokens.length < 7) throw new Exception("Usage: create_jedi <planet> <name> <age> <color> <strength> <rank>");
                        Planet p = universe.findPlanet(tokens[1]);
                        if (p == null) throw new Exception("Planet not found.");
                        if (!universe.isJediNameUnique(tokens[2])) throw new Exception("Jedi name must be unique!");

                        Rank r = Rank.valueOf(tokens[6].toUpperCase());
                        Jedi newJedi = new Jedi(tokens[2], Integer.parseInt(tokens[3]), tokens[4], Double.parseDouble(tokens[5]), r);
                        p.addJedi(newJedi);
                        System.out.println("Jedi " + tokens[2] + " added to " + tokens[1]);
                        break;

                    case "remove_jedi":
                        if (tokens.length < 3) throw new Exception("Usage: remove_jedi <name> <planet>");
                        Planet rp = universe.findPlanet(tokens[2]);
                        if (rp == null) throw new Exception("Planet not found.");
                        try {
                            rp.removeJedi(tokens[1]);
                            System.out.println("Jedi " + tokens[1] + " has been removed from " + tokens[2]);
                        } catch (JediName e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case "promote":
                        if (tokens.length < 3) throw new Exception("Usage: promote <name> <multiplier>");
                        boolean promFound = false;
                        for (Planet pl : universe.getPlanets()) {
                            for (Jedi j : pl.getJediList()) {
                                if (j.getName().equalsIgnoreCase(tokens[1])) {
                                    j.promote(Double.parseDouble(tokens[2]));
                                    System.out.println(j.getName() + " promoted to " + j.getRank());
                                    promFound = true; break;
                                }
                            }
                        }
                        if (!promFound) System.out.println("Jedi not found.");
                        break;

                    case "demote":
                        if (tokens.length < 3) throw new Exception("Usage: demote <name> <multiplier>");
                        boolean demFound = false;
                        for (Planet pl : universe.getPlanets()) {
                            for (Jedi j : pl.getJediList()) {
                                if (j.getName().equalsIgnoreCase(tokens[1])) {
                                    j.demote(Double.parseDouble(tokens[2]));
                                    System.out.println(j.getName() + " demoted to " + j.getRank());
                                    demFound = true; break;
                                }
                            }
                        }
                        if (!demFound) System.out.println("Jedi not found.");
                        break;

                    case "get_strongest_jedi":
                        if (tokens.length < 2) throw new Exception("Usage: get_strongest_jedi <planet>");
                        Planet strP = universe.findPlanet(tokens[1]);
                        if (strP != null) {
                            System.out.println("Strongest: " + strP.getStrongestJedi());
                        }
                        break;

                    case "get_youngest_jedi": // ТВОЯТ НОВ МЕТОД
                        if (tokens.length < 3) throw new Exception("Usage: get_youngest_jedi <planet> <rank>");
                        Planet yoP = universe.findPlanet(tokens[1]);
                        if (yoP != null) {
                            Rank yr = Rank.valueOf(tokens[2].toUpperCase());
                            Jedi yj = yoP.getYoungestJedi(yr);
                            System.out.println("Youngest " + yr + " is: " + (yj != null ? yj : "None found"));
                        }
                        break;

                    case "get_most_used_saber_color": // ТВОЯТ НОВ МЕТОД
                        if (tokens.length < 3) throw new Exception("Usage: get_most_used_saber_color <planet> <rank>");
                        Planet colP = universe.findPlanet(tokens[1]);
                        if (colP != null) {
                            Rank cr = Rank.valueOf(tokens[2].toUpperCase());
                            System.out.println("Most used color for " + cr + ": " + colP.getMostUsedSaberColor(cr));
                        }
                        break;

                    case "get_most_used_saber_color_grandmaster": // СПЕЦИФИЧНАТА КОМАНДА
                        if (tokens.length < 2) throw new Exception("Usage: get_most_used_saber_color_grandmaster <planet>");
                        Planet gmP = universe.findPlanet(tokens[1]);
                        if (gmP != null) {
                            System.out.println("Most used color among Grand Masters: " + gmP.getMostUsedSaberColorForGrandMaster());
                        }
                        break;

                    case "print": // Използва твоя getPlanetInfo()
                        if (tokens.length < 2) throw new Exception("Usage: print <planet>");
                        Planet prP = universe.findPlanet(tokens[1]);
                        if (prP != null) System.out.println(prP.getPlanetInfo());
                        break;

                    case "print_jedi": // Използва метода ти от Universe
                        if (tokens.length < 2) throw new Exception("Usage: print_jedi <name>");
                        System.out.println(universe.printJedi(tokens[1]));
                        break;

                    case "merge_planets":
                        if (tokens.length < 3) throw new Exception("Usage: merge_planets <p1> <p2>");
                        List<Jedi> merged = universe.mergePlanet(tokens[1], tokens[2]);
                        System.out.println("--- Merged Jedi List ---");
                        for (Jedi mj : merged) System.out.println(mj);
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
        System.out.println("\n--- STAR WARS UNIVERSE COMMANDS ---");
        System.out.println("SYSTEM COMMANDS:");
        System.out.println("  open <file>       - Opens or creates a file");
        System.out.println("  close             - Closes the current file");
        System.out.println("  save              - Saves to current file");
        System.out.println("  saveas <file>     - Saves to specific file");
        System.out.println("  exit              - Exits the program");
        System.out.println("\nMANAGEMENT COMMANDS:");
        System.out.println("  add_planet <name> - Adds a new planet");
        System.out.println("  create_jedi <planet> <name> <age> <color> <strength> <rank>");
        System.out.println("  remove_jedi <name> <planet>");
        System.out.println("\nQUERY COMMANDS:");
        System.out.println("  print <planet>    - Shows all Jedi on a planet");
        System.out.println("  print_jedi <name> - Shows Jedi details and location");
        System.out.println("------------------------------------\n");
    }

    private static void handleOpen(String[] tokens, Universe u) {
        if (tokens.length < 2) {
            System.out.println("Error: Usage: open <file_name.txt>");
            return;
        }

        String fileName = tokens[1];
        Path path = Paths.get(fileName);

        try {
            if (Files.notExists(path)) {
                Files.writeString(path, "");
                System.out.println("Successfully created new file: " + fileName);
            } else {
                System.out.println("Successfully opened " + fileName);
                // ВАЖНО: Викаме loadData тук, за да прочетем съществуващия файл!
                u.loadData(fileName);
                System.out.println("Data loaded into memory.");
            }
            u.setFileOpened(true, fileName);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Data Error: " + e.getMessage());
        }
    }

    private static void handleSave(Universe u, String path) {
        try {
            u.saveData(path);
            System.out.println("Successfully saved to " + new File(path).getName());
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}