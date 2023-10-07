import java.util.Comparator;

public abstract class Plant {
    public static final int INFINITE = Integer.MAX_VALUE;

    String name;
    int hp;
    int sun_cost;

    public Plant(String name, int sun_cost) {
        this.name = name;
        this.hp = 6;
        this.sun_cost = sun_cost;
    }

    public Plant(String name, int hp, int sun_cost) {
        this.name = name;
        this.hp = hp;
        this.sun_cost = sun_cost;
    }

    public boolean isAlive() {
        if (hp > 0) return true;
        return false;
    }

    public String die() {
        this.hp = 0;
        return this.name + " dies";
    }

    @Override
    public String toString() {
        if (hp == INFINITE){
            return this.name + " (∞) - cost: " + sun_cost;
        }
        return this.name + " (" + hp + ") - cost: " + sun_cost;
    }

    // Add Plant subclasses here, and
    // Hint: You can also add Comparator inner classes here
    // WallNut and CoffeeBean given for free
    public static class Sunflower extends Plant implements SunProducer, Upgradable {
        public Sunflower() {
            super("Sunflower", 50);
        }

        @Override
        public int produce_sun() {
            System.out.println(this.name + " produces 25 suns");
            return 25;
        }

        @Override
        public PlantUpgrade upgrade() {
            PlantUpgrade twin = new TwinSunflower();
            return twin;
        }
    }
    public static class TwinSunflower extends Plant implements SunProducer, PlantUpgrade {
        public TwinSunflower() {
            super("Twin Sunflower", 250);
        }

        @Override
        public int produce_sun() {
            System.out.println(this.name + " produces 50 suns");
            return 50;
        }

        @Override
        public int concurrentSunCost() {
            return 50;
        }
    }

    public static class Peashooter extends Plant implements Attacker {
        public Peashooter() {
            super("Peashooter", 100);

        }

        @Override
        public int attack() {
            System.out.println(name+" attacks ");
            return 1;
        }

        @Override
        public int rangeType() {
            return 1;
        }
    }

    public static class WallNut extends Plant{
        public WallNut() {
            super("Wall Nut", 25, 50);
        }
    }

    public static class Squash extends Plant implements InstantKiller, Attacker{

        public Squash() {
            super("Squash", INFINITE, 50);
        }
        //1 tile range, 3 damage
        @Override
        public int killType() {
            return 2;
        }
        @Override
        public String die () {
            this.hp = 0;
            return super.die() + " while squashing zombies";
        }

        @Override
        public int attack() {
            System.out.println(name+" attacks ");
            System.out.println(die());
            return 3;
        }

        @Override
        public int rangeType() {
            return 3;
        }
    }

    public static class Jalapeno extends Plant implements InstantKiller, Attacker{

        public Jalapeno() {
            super("Jalapeno", INFINITE, 125);
        }

        @Override
        public int killType() {
            return 1;
        }

        @Override
        public String die () {
            this.hp = 0;
            return super.die() + " while exploding";
        }

        @Override
        public int attack() {
            System.out.println(name+" attacks ");
            System.out.println(die());
            return 5;
        }

        @Override
        public int rangeType() {
            return 1;
        }
    }

    public static class CoffeeBean extends Plant{
        public CoffeeBean() {
            super("Coffee Bean", INFINITE, 75);
        }
    }

    public static class LilyPad extends Plant implements Upgradable {

        public LilyPad() {
            super("Lily Pad", 25);
        }

        @Override
        public PlantUpgrade upgrade() {
            PlantUpgrade cat = new Cattail();
            return cat;
        }

    }

    public static class Cattail extends Plant implements Attacker, PlantUpgrade {

        public Cattail() {
            super("Cattail", 225);
        }

        @Override
        public int attack() {
            System.out.println(name+" attacks");
            return 1;
        }

        @Override
        public int rangeType() {
            return 4;
        }

        @Override
        public int concurrentSunCost() {
            return 25;
        }
    }

    public static class HPSort implements Comparator<Plant>{
        public int compare(Plant o1, Plant o2){
            if(Integer.compare(o2.hp,o1.hp) == 0){
                return o1.name.compareTo(o2.name);
            }
            return Integer.compare(o2.hp,o1.hp);
        }
    }

    public int compareTo(Plant other) {
        return this.name.compareTo(other.name);
    }



    public static class NameSort implements Comparator<Plant>{
        public int compare(Plant o1, Plant o2){
            return o1.name.compareTo(o2.name);
        }
    }


    public static class SunSort implements Comparator<Plant> {
        @Override
        public int compare(Plant o1, Plant o2){
            if(Integer.compare(o2.sun_cost,o1.sun_cost) == 0){
                return o1.name.compareTo(o2.name);
            }
            return Integer.compare(o2.sun_cost,o1.sun_cost);
        }
    }
}