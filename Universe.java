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
    public void close(){
        planets.clear();
        currentFilePath = null;
        isFileOpened = false;
        System.out.println("Успешно затворен файл.");
    }
    public boolean isFileOpened(){
        return isFileOpened;
    }

    public void setFileOpened(boolean opened, String path){
        this.isFileOpened = opened;
        this.currentFilePath = path;
    }

    public String getCurrentFilePath(){
        return currentFilePath;
    }

    // 1. Помощен метод за намиране на обект Planet по име
    public Planet findPlanet(String name){
        for (Planet p: planets){
            if (p.getName().equalsIgnoreCase(name)){
                return p;
            }
        }
        return null;
    }
    // 2. Метод за добавяне на планета (Команда: add_planet)
    public void addPlanet(String name) {
        if (findPlanet(name) != null) {
            System.out.println("Планетата вече съществува: " + name);
            return;
        }
        planets.add(new Planet(name));
        System.out.println("Успешно добавена планета: " + name);
    }
    // 3. Проверка дали името на джедай е уникално в цялата вселена
    public boolean isJediNameUnique(String name){
        for (Planet p: planets){
            for (Jedi j: p.getJediList()){
                if (j.getName().equalsIgnoreCase(name)){
                    return false;
                }
            }
        }
        return true;
    }
    // 4. Глобално принтиране на джедай
    public void printJedi(String name){
       boolean found = false;
       for (Planet p: planets){
           for (Jedi j : p.getJediList()){
               if (j.getName().equalsIgnoreCase(name)){
                   System.out.println(j+" Обитава планета " + p.getName());
                   found = true;
               }
           }
       }
       if (!found){
           System.out.println("Джедай с име " + name + " не е намерен.");
       }
    }
    // 5. Обединяване на планети
    public void mergePlanet(String name1, String name2){
        Planet p1 = findPlanet(name1);
        Planet p2 = findPlanet(name2);

        if (p1 == null || p2 == null){
            System.out.println("Грешка: Една или две от планетите не са намерени.");
            return;
        }

        List<Jedi> combine = new ArrayList<>();
        combine.addAll(p1.getJediList());
        combine.addAll(p2.getJediList());

        combine.sort(Comparator.comparing(Jedi::getName));
        System.out.println("Резултат от " + name1 + " + " + name2 + ":");
        for (Jedi j : combine){
            System.out.println(j);
        }
    }


}
