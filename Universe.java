import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Universe {
    private List<Planet> planets;
    private String currentFilePath;
    private boolean isFileOpened;

    public Universe() {
        this.planets = new ArrayList<>();
        this.currentFilePath = null;
        this.isFileOpened = false;
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    // Метод за командата CLOSE
    public void close() {
        planets.clear();
        currentFilePath = null;
        isFileOpened = false;
    }

    public boolean isFileOpened() {
        return isFileOpened;
    }

    public void setFileOpened(boolean opened, String path) {
        this.isFileOpened = opened;
        this.currentFilePath = path;
    }

    public String getCurrentFilePath() {
        return currentFilePath;
    }

    //  Помощен метод за намиране на обект Planet по име
    public Planet findPlanet(String name) {
        for (Planet p : planets) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    //  Метод за добавяне на планета
    public boolean addPlanet(String name) {
        if (findPlanet(name) != null) {

            throw new PlanetException("Планетата вече съществува: " + name);
        }
        planets.add(new Planet(name));
        return true;
    }

    //  Проверка дали името на джедай е уникално в цялата вселена
    public boolean isJediNameUnique(String name) {
        for (Planet p : planets) {
            for (Jedi j : p.getJediList()) {
                if (j.getName().equalsIgnoreCase(name)) {
                    return false;
                }
            }
        }
        return true;
    }

    //  Глобално принтиране на джедай
    public String printJedi(String name) {
        for (Planet p : planets) {
            for (Jedi j : p.getJediList()) {
                if (j.getName().equalsIgnoreCase(name)) {
                    return j.toString() + " | Обитава планета: " + p.getName();

                }
            }
        }

        throw new JediException("Джедай с име " + name + " не е намерен.");
    }

    //  Обединяване на планети
    public List<Jedi> mergePlanet(String name1, String name2) throws JediException {
        Planet p1 = findPlanet(name1);
        Planet p2 = findPlanet(name2);


        if (p1 == null) throw new JediException("Планетата " + name1 + " не съществува!");
        if (p2 == null) throw new JediException("Планетата " + name2 + " не съществува!");

        // Създаваме нов временен списък с всички джедаи
        List<Jedi> combinedList = new ArrayList<>();
        combinedList.addAll(p1.getJediList());
        combinedList.addAll(p2.getJediList());


        combinedList.sort(Comparator.comparing(Jedi::getName));


        return combinedList;
    }

    public void saveData(String path) throws IOException {
        FileHandler.save(path, this.planets);
    }

    public void loadData(String path) throws IOException {
        this.planets.clear();
        FileHandler.load(path, this);
    }
}


