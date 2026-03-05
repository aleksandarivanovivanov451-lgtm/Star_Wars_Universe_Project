import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Planet {
    private String name;
    private List<Jedi> jediList;

    public Planet(String name) {
        this.name = name;
        this.jediList = new ArrayList<>();
    }
    // Трябва ти метод за добавяне на джедай
    public void addJedi(Jedi jedi){
        jediList.add(jedi);
    }

    public void removeJedi(String jediName){
        //ФУнкция за премахване на Джедай по име
        for (Jedi j : jediList){
            if (j.getName().equalsIgnoreCase(jediName)){
               jediList.remove(j);
               System.out.println("Успешно премахна джедая:"+ jediName );
               return;
            }
        }

        throw new JediName("Не е намерен джедай с това име!");
    }

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
    public String getMostUsedSaberColorForGrandMaster(Rank targetRank) {
        //  1. Събираме цветовете САМО на Grand Master-ите
        List<String> gmColors = new ArrayList<>();
        for (Jedi j : jediList) {
            if (j.getRank() == Rank.GRAND_MASTER) {
                gmColors.add(j.getSaberColor());
            }
        }
//  3. Проверка дали изобщо сме намерили такива
        if (gmColors.isEmpty()) {
            return "На тази планета няма Grand Master-и (съответно няма и техни цветове).";
        }
        //  4. Намираме най-честия цвят
        String mostUsed = "";
        int maxCount = 0;
        for (String color : gmColors) {
            int currentCount = 0;
            for (String c : gmColors) {
                if (c.equalsIgnoreCase(color)) {
                    currentCount++;
                }
            }
            if (currentCount > maxCount) {
                maxCount = currentCount;
                mostUsed = color;
            }
        }

        return mostUsed;
    }
    public String getMostUsedSaberColor(Rank targetRank){
        // 1. Събираме цветовете САМО на джедаите от подадения ранг
        List<String> colorsAtRank = new ArrayList<>();
        for (Jedi j: jediList){
            if (j.getRank() == targetRank){
                colorsAtRank.add(j.getSaberColor());
            }
        }
// 2. Проверка: Ако няма джедаи от този ранг на планетата
        if (colorsAtRank.isEmpty()){
            return "На тази няма джедай с този ранг" + targetRank;
        }
// 3. Търсим най-често срещания цвят в този списък
        String mostUsed = "";
        int maxCount = 0;
        for (String color: colorsAtRank){
            int currentCount = 0;
            for (String c : colorsAtRank){
                if (c.equalsIgnoreCase(color)){
                    currentCount++;
                }
            }
            if (currentCount>maxCount){
                maxCount=currentCount;
                mostUsed=color;
            }
        }
        return mostUsed;
    }
    

    public void printPlanet(){
        // 1. Извеждаме името на планетата
        System.out.println("Planet" + this.name);
// 2. Сортираме джедаите: първо по ранг (нарастващ), после по име (азбучен ред)
        jediList.sort(Comparator.comparing(Jedi::getRank).thenComparing(Jedi::getName));
// 3. Проверка и принтиране
        if (jediList.isEmpty()){
            System.out.println("Няма джедай на таизи планета");
        }else {
            for (Jedi j: jediList){
                System.out.println(j);
            }
        }

    }


}
