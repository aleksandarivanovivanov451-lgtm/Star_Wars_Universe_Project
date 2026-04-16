public class HelpCommand implements Command {
    @Override
    public void execute(String[] tokens, Universe universe) throws Exception {
        System.out.println("\n--- STAR WARS SYSTEM COMMANDS ---");
        System.out.println("open, close, save, saveas, exit");
        System.out.println("add_planet, create_jedi, remove_jedi");
        System.out.println("print, print_jedi, merge_planets");
        System.out.println("get_strongest_jedi, get_youngest_jedi");
        System.out.println("get_most_used_saber_color, promote, demote");
        System.out.println("\nMANAGEMENT COMMANDS:");
        System.out.println("  add_planet <name> - Adds a new planet");
        System.out.println("  create_jedi <planet> <name> <age> <color> <strength> <rank>");
        System.out.println("  remove_jedi <name> <planet>");
        System.out.println("\nQUERY COMMANDS:");
        System.out.println("  print <planet>    - Shows all Jedi on a planet");
        System.out.println("  print_jedi <name> - Shows Jedi details and location");
    }
}
