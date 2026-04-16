public class GMSaberColorCommand implements Command {
    @Override
    public void execute(String[] tokens, Universe universe) throws Exception {
        if (tokens.length<2)throw new Exception("Usage: get_most_used_saber_color_grandmaster <planet_name>");
        Planet p = universe.findPlanet(tokens[1]);
        if (p == null)throw new Exception("Planet not found");
        String color = p.getMostUsedSaberColorForGrandMaster();
        if (color!=null&&!color.equals("None")){
            System.out.println("Most used saber color among Grand Masters on"  + tokens[1] +  "is: "  + color);
        }else {
            System.out.println("No Grand Masters found on " + tokens[1]);
        }
    }
}
