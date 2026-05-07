public class HelpCommand implements Command {
    @Override
    public String execute(String[] tokens, Universe universe) {
        return "Commands: open, close, save, saveas, add_planet, " +
                "create_jedi, remove_jedi, promote, demote, print, " +
                "print_jedi, merge_planets, get_strongest_jedi, get_youngest_jedi, " +
                "get_most_used_saber_color, exit";
    }
}
