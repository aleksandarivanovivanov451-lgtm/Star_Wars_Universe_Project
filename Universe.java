import java.util.ArrayList;
import java.util.List;

public class Universe {
    private List<Planet> planets;

    public Universe() {
        this.planets = new ArrayList<>();
    }

    public Planet findPlanet(String name){
        for (Planet p: planets){
            if (p.getName().equalsIgnoreCase(name)){
                return p;
            }
        }
        return null;
    }
    public void  addPlanet(String name){
        for (Planet p: planets){
            if (findPlanet(name) != null){
                System.out.println("Планетата вече съществува:" + name);
                return;
            }
            planets.add(new Planet(name));
                System.out.println("Успешно добавена планета:" + name);

        }
    }


}
