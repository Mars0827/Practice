import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Plant> plants = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Game Mode: ");
        String mode = sc.nextLine();
        boolean mstate = (mode.equals("Night")||mode.equals("Fog"));
        List<Plant> plants2 = new ArrayList<>(plants);

        String input;
        do {
            System.out.print("Add a plant: ");
            input = sc.nextLine();
            switch (input) {
                case "DONE":
                    break;
                case "Wall Nut":
                    plants.add(new Plant.WallNut());
                    break;
                case "Sun-shroom":
                    if (mstate){
                        plants.add(new Mushroom.SunShroom(true));
                    } else {
                        plants.add(new Mushroom.SunShroom(false));
                    }
                    break;

                case "Puff-shroom":
                    if (mstate){
                        plants.add(new Mushroom.PuffShroom(true));
                    } else {
                        plants.add(new Mushroom.PuffShroom(false));
                    }
                    break;
                case "Doom-shroom":
                    if (mstate) {
                        plants.add(new Mushroom.DoomShroom(true));
                    } else {
                        plants.add(new Mushroom.DoomShroom(false));
                    }
                    break;
                case "Sunflower":
                    plants.add(new Plant.Sunflower());
                    break;
                case "Twin Sunflower":
                    boolean tf = false;
                    for(Plant p : plants){
                        if(p instanceof Plant.Sunflower){
                            /*p = (Plant) TF;*/
                            plants.set(plants.indexOf(p), (Plant) ((Upgradable) p).upgrade());
                            tf = true;
                            /* plants2.add(p); */
                            break;
                        }
                    }
                    if(!tf){
                        plants.add(new Plant.TwinSunflower());
                    }

                    break;
                case "Peashooter":
                    plants.add(new Plant.Peashooter());
                    break;
                case "Squash":
                    plants.add(new Plant.Squash());
                    break;
                case "Jalapeno":
                    plants.add(new Plant.Jalapeno());
                    break;
                case "Coffee Bean":
                    Plant.CoffeeBean cb = new Plant.CoffeeBean();
                    plants.add(cb);
                    for(Plant p : plants){
                        if(p instanceof Mushroom){
                            if(!((Mushroom) p).isAwake()){
                                ((Mushroom) p).awaken(cb);
                                plants2.add(cb);
                                break;
                            }
                        }

                    }

                    plants.removeAll(plants2);

                    break;
                case "Lily Pad":
                    plants.add(new Plant.LilyPad());
                    break;
                case "Cattail":
                    boolean cat = false;
                    for(Plant p : plants){
                        if(p instanceof Plant.LilyPad){
                            /*p = (Plant) TF;*/
                            plants.set(plants.indexOf(p), (Plant) ((Upgradable) p).upgrade());
                            cat = true;
                            /* plants2.add(p); */
                            break;
                        }

                    }
                    if(!cat){
                        plants.add(new Plant.Cattail());
                    }
                    break;

                // add more plants here
                default:
                    System.out.println(input + " is not a plant");
            }
        } while (!input.equals("DONE"));

        /*plants.removeAll(plants2);*/
        do {
            System.out.print("Do something: ");
            input = sc.nextLine();
            switch (input) {
                case "DONE":
                    break;
                case "Produce Sun":
                    int sy = 0;
                    int sx = 0;
                    for (Plant p : plants) {
                        if (p instanceof SunProducer) {
                            sy += ((SunProducer) p).produce_sun();
                            sx++;
                        }
                    }

                   /* for (Plant p : plants2) {
                        System.out.println(p);
                    }*/

                    System.out.println(sx + " sun producers gather " + sy + " suns");
                    break;
                case "Attack":
                    int ay = 0;
                    int ax = 0;
                    for (Plant p : plants) {
                        if (p instanceof Attacker) {
                            if(p.isAlive()){
                                ay += ((Attacker) p).attack();
                                ax++;
                            }
                        }
                    }
                    if (ax == 0) {
                        System.out.println("You have no attackers");
                    } else {
                        System.out.println(ax + " attackers dealing " + ay + " damage");
                    }

                    //plants.removeAll(plants2);
                    break;
                // add more cases here
                case "Instant Kill Status":
                    int ix = 0;
                    for (Plant p : plants) {
                        if (p instanceof InstantKiller && p.isAlive()) {
                            ix++;
                            if(p instanceof Mushroom){
                                if(true){
                                    if (((InstantKiller) p).killType() == 1) {
                                        System.out.println(p.name + " can kill instantly");
                                    }
                                }
                            }
                            if(!(p instanceof Mushroom)){
                                if (((InstantKiller) p).killType() == 1) {
                                    System.out.println(p.name + " can kill instantly");
                                }
                                if (((InstantKiller) p).killType() == 2) {
                                    System.out.println(p.name + " can kill on contact");
                                }
                            }

                        }
                    }
                    if (ix == 0) {
                        System.out.println("You have no plants which can kill instantly");
                    }
                    break;
                case "Attacker Status":
                    for (Plant p : plants) {
                        if (p instanceof Attacker && p.isAlive()) {
                            if (p instanceof Mushroom) {
                                if (((Mushroom) p).isAwake()) {
                                    switch (((Attacker) p).rangeType()) {
                                        case 1:
                                            System.out.println(p.name + " can attack on a single line");
                                            break;
                                        case 2:
                                            System.out.println(p.name + " can attack using area-of-effect");
                                            break;
                                        case 3:
                                            System.out.println(p.name + " can attack only when enemy is nearby");
                                            break;
                                        case 4:
                                            System.out.println(p.name + " can attack any enemies from anywhere");
                                            break;
                                    }
                                }
                            } else {
                                switch (((Attacker) p).rangeType()) {
                                    case 1:
                                        System.out.println(p.name + " can attack on a single line");
                                        break;
                                    case 2:
                                        System.out.println(p.name + " can attack using area-of-effect");
                                        break;
                                    case 3:
                                        System.out.println(p.name + " can attack only when enemy is nearby");
                                        break;
                                    case 4:
                                        System.out.println(p.name + " can attack any enemies from anywhere");
                                        break;
                                }
                            }
                        }
                    }
                    break;
                case "Sort by HP":
                    Collections.sort(plants, new Plant.HPSort());
                    //List<Plant> p1 = plants.addAll(p1);
                    for(Plant p : plants){
                        if(p instanceof Plant){
                            if(p instanceof InstantKiller && (p instanceof Mushroom)) {
                                if (((Mushroom) p).isAwake()) {
                                    System.out.println(p.name + " (∞) - cost: " + p.sun_cost);
                                } else {
                                    System.out.println(p.name + " (" + p.hp + ") - cost: " + p.sun_cost);
                                }
                            } else if (p instanceof InstantKiller && p.isAlive()){
                                System.out.println(p.name + " (∞) - cost: " + p.sun_cost);
                            } else {
                                System.out.println(p.name + " (" + p.hp + ") - cost: " + p.sun_cost);
                            }

                        }
                    }
                    break;
                case "Sort by Name":
                    plants.sort(new Plant.NameSort());
                    for(Plant p : plants){
                        if(p instanceof Plant){
                            if(p instanceof InstantKiller && p.isAlive()){
                                System.out.println(p.name + " (∞) - cost: " + p.sun_cost);
                            }else{
                                System.out.println(p.name + " (" + p.hp + ") - cost: "+ p.sun_cost);
                            }
                        }
                    }
                    break;
                case "Sort by Sun Cost":
                    Collections.sort(plants, new Plant.SunSort());
                    //List<Plant> p1 = plants.addAll(p1);
                    for (Plant p : plants){
                        System.out.println(p);
                    }
                    break;
                default:
                    System.out.println("Unknown action: " + input);
            }
        } while (!input.equals("DONE"));
    }
}

//

        /* for (Plant p : plants2) {
                System.out.println(p);
            }*/