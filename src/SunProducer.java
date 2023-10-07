interface SunProducer {
    int produce_sun();
}

interface Attacker {
    int attack();
    int rangeType();
}


// add more interfaces here

interface InstantKiller{
    int killType();
}

interface PlantUpgrade {
    int concurrentSunCost();
}

interface Upgradable{
    PlantUpgrade upgrade();
}