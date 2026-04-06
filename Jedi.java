public class Jedi {
    private  String name;
    private int age;
    private String saberColor;
    private double strength;
    private Rank rank;

    public Jedi(String name, int age, String saberColor, double strength, Rank rank) {
        this.name = name;
        this.age = age;
        this.saberColor = saberColor;
        this.strength = strength;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSaberColor() {
        return saberColor;
    }

    public double getStrength() {
        return strength;
    }

    public Rank getRank() {
        return rank;
    }
// фУНКЦИЯ за вдигане на ранг от потребителя
    public void promote(double multiplier){
        //проверка за multiplier
        if (multiplier <= 0 ){
            throw new JediException("Мultiplier трябва да е положително число");
        }
        //проверка дали е положително число
        if (multiplier > 0) {
            //По формулата за увеличаване на силата
            this.strength += (multiplier * this.strength);
        }
        // Повишаване на ранга с едно ниво нагоре
        Rank[] ranks = Rank.values();
        if (this.rank.ordinal() < ranks.length - 1){
            this.rank = ranks[this.rank.ordinal()+1];
        }else {
            throw new JediException(name + " вече е на максимален ранг (GRAND_MASTER)!");
        }

    }
    public void demote(double multiplier){
        //проверка за multiplier
        if (multiplier <= 0 ){
            throw new JediException("Мultiplier трябва да е положително число");
        }
        //проверка дали е положително число
        if (multiplier > 0) {
            //По формулата за увеличаване на силата
            this.strength -= (multiplier * this.strength);
            if (this.strength<0){
                this.strength=0;
            }
        }
        // Намаляваме ранга с едно ниво надолу, но не по-ниско от YOUNGLING
        if (this.rank.ordinal() > 0) {
            Rank[] ranks = Rank.values();
            this.rank = ranks[this.rank.ordinal() - 1];
        }else {
            throw new JediException("Джедая: " + name + " вече е най ниския  ранг ");
        }

    }

    @Override
    public String toString() {
        return "Jedi{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", saberColor='" + rank + '\'' +
                ", strength=" + strength +
                ", rank=" + saberColor +
                '}';
    }
}
