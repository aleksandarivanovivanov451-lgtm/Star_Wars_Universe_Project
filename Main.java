import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Universe universe = new Universe();
        Map<String, Command> commands = new HashMap<>();

        // РЕГИСТРАЦИЯ НА ВСИЧКИ КОМАНДИ
        commands.put("open", new OpenCommand());
        commands.put("close", new CloseCommand());
        commands.put("save", new SaveCommand());
        commands.put("saveas", new SaveAsCommand());
        commands.put("help", new HelpCommand());

        commands.put("add_planet", new AddPlanetCommand());
        commands.put("create_jedi", new CreateJediCommand());
        commands.put("remove_jedi", new RemoveJediCommand());

        commands.put("promote", new PromoteCommand());
        commands.put("demote", new DemoteCommand());

        commands.put("print", new PrintCommand());
        commands.put("print_jedi", new PrintJediCommand());
        commands.put("merge_planets", new MergePlanetsCommand());

        commands.put("get_strongest_jedi", new StrongestJediCommand());
        commands.put("get_youngest_jedi", new YoungestJediCommand());
        commands.put("get_most_used_saber_color", new SaberColorCommand());
        commands.put("get_most_used_saber_color_grandmaster", new GMSaberColorCommand());

        System.out.println("--- Star Wars Universe System Ready ---");
        System.out.println("Type 'help' to see all commands or 'exit' to quit.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            // Твоята проверка за EXIT
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting... May the Force be with you!");
                break;
            }

            // Нарязваме командата на части (tokens)
            String[] tokens = input.split("\\s+");
            String commandName = tokens[0].toLowerCase();

            // Търсим командата в нашия списък (Map)
            Command cmd = commands.get(commandName);

            if (cmd != null) {
                try {
                    // Проверка за отворен файл (освен за 'open' и 'help')
                    if (!universe.isFileOpened() && !commandName.equals("open") && !commandName.equals("help")) {
                        System.out.println("Error: Please open a file first using the 'open' command.");
                        continue;
                    }

                    // ТУК Е ВАЖНОТО: Викаме execute и ПРИНТИРАМЕ String резултата
                    String result = cmd.execute(tokens, universe);

                    if (result != null) {
                        System.out.println(result);
                    }

                } catch (Exception e) {
                    // Тук се принтират съобщенията от JediException, PlanetException и т.н.
                    System.out.println("Error: " + e.getMessage());
                }
            } else {
                System.out.println("Unknown command: '" + commandName + "'. Type 'help' for a list of commands.");
            }
        }
        scanner.close();
    }
}