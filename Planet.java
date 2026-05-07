import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Planet {
    private String name;
    private List<Jedi> jediList;

    public Planet(){
        this.jediList = new ArrayList<>();
    }
    public Planet(String name) {
        this.name = name;
        this.jediList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Jedi> getJediList() {
        return jediList;
    }

    // метод за добавяне на джедай
    public boolean addJedi(Jedi jedi){
        jediList.add(jedi);
        return true;
    }


        //ФУнкция за премахване на Джедай по име
        public boolean removeJedi(String jediName) {
            boolean removed = jediList.removeIf(j -> j.getName().equalsIgnoreCase(jediName));
            if (!removed) {
                throw new JediName("Не е намерен джедай с име: " + jediName);
            }
            return true;
        }
//функция за намиране на най силния джедай на конкретна планета
    public Jedi getStrongestJedi(){
        // 1. Проверка дали списъкът е празен (за да не гръмне програмата)
        if (jediList.isEmpty()){
            return null;
        }
        // 2. Променлива от тип Jedi, която пази най-добрия до момента
        Jedi strongest = jediList.get(0);
// 3. Цикъл за сравнение
        for (Jedi current : jediList){
            if (current.getStrength() > strongest.getStrength()){
                strongest=current;
            }
            // Ако силите са еднакви, гледаме имената
            else if (current.getStrength() == strongest.getStrength()) {
                if (current.getName().compareToIgnoreCase(strongest.getName())<0){
                    strongest=current;
                }
            }
        }
        return strongest;

    }
//функция за намиоране на най младия джедай от даден ранк
    public Jedi getYoungestJedi(Rank targetRank){
        Jedi youngest = null;
        for (Jedi j : jediList){
            // 1. Проверяваме дали джедаят е от търсения ранг
            if (j.getRank() == targetRank){
                // 2. Ако това е първият джедай от този ранг, който срещаме
                if (youngest == null){
                    youngest = j;
                }
                // 3. Ако вече сме намерили един, сравняваме годините
                else if(j.getAge() < youngest.getAge()){
                    youngest = j;
                }
                // 4. Ако са на еднаква възраст - гледаме имената (лексикографски)
                else if (j.getAge()== youngest.getAge()){
                    if (j.getName().compareToIgnoreCase(youngest.getName())<0){
                        youngest= j;
                    }
                }
            }
        }
        return youngest;

    }
    // Просто пренасочваме към по-общата функция с нужния ранг(GRAND_MASTER))
    public String getMostUsedSaberColorForGrandMaster() {

        return getMostUsedSaberColor(Rank.GRAND_MASTER);
    }
    //най-честия цвят от даден ранг
    public String getMostUsedSaberColor(Rank targetRank) {

        List<String> colorsAtRank = new ArrayList<>();
        for (Jedi j : jediList) {
            if (j.getRank() == targetRank) {
                colorsAtRank.add(j.getSaberColor());
            }
        }


        if (colorsAtRank.isEmpty()) {
            throw new PlanetException("На планетата " + name + " няма джедаи с ранг " + targetRank);
        }


        String mostUsed = "";
        int maxCount = 0;

        for (String currentColor : colorsAtRank) {
            int currentCount = 0;
            for (String c : colorsAtRank) {
                if (c.equalsIgnoreCase(currentColor)) {
                    currentCount++;
                }
            }


            if (currentCount > maxCount) {
                maxCount = currentCount;
                mostUsed = currentColor;
            } else if (currentCount == maxCount) {
                if (currentColor.compareToIgnoreCase(mostUsed) < 0) {
                    mostUsed = currentColor;
                }
            }
        }

        return mostUsed;
    }

//взимаме информацията за въведената планета планета
    public String getPlanetInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Planet: ").append(this.name).append("\n");

        // Сортираме за изгледа
        List<Jedi> sortedJedi = new ArrayList<>(jediList);
        sortedJedi.sort(Comparator.comparing(Jedi::getRank).thenComparing(Jedi::getName));

        if (sortedJedi.isEmpty()) {
            sb.append("Няма джедаи на тази планета.");
        } else {
            for (Jedi j : sortedJedi) {
                sb.append(j.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Planet: " + name + " (Jedi count: " + jediList.size() + ")";
    }
}
