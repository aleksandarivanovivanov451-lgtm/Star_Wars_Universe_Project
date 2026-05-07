import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileHandler {
    //функция за записване на информацията  в отворения  файл
    public static void save(String path, List<Planet> planets) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (Planet p : planets) {
            sb.append("PLANET:").append(p.getName()).append("\n");
            for (Jedi j : p.getJediList()) {
                sb.append("JEDI:")
                        .append(j.getName()).append(",")
                        .append(j.getAge()).append(",")
                        .append(j.getSaberColor()).append(",")
                        .append(j.getStrength()).append(",")
                        .append(j.getRank()).append("\n");
            }
        }
        Files.writeString(Paths.get(path), sb.toString());
    }
    //функция за четене на информацията  от  отворения  файл
    public static void load(String path, Universe universe) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        Planet lastPlanet = null;
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            if (line.startsWith("PLANET:")) {
                String pName = line.substring(7);
                lastPlanet = new Planet(pName);
                universe.getPlanets().add(lastPlanet);
            } else if (line.startsWith("JEDI:") && lastPlanet != null) {
                String[] parts = line.substring(5).split(",");
                Jedi j = new Jedi(parts[0], Integer.parseInt(parts[1]), parts[2],
                        Double.parseDouble(parts[3]), Rank.valueOf(parts[4]));
                lastPlanet.addJedi(j);
            }
        }
    }
}
