import java.util.Random;

public class Main {
    public static int bossHealth = 1000;
    public static int bossDamage = 50;
    public static String bossDefenseType;
    public static int[] heroesHealth = {350, 300, 250, 350, 250};
    public static String[] heroesAttackType = {"Physical", "Magical ", "Kinetic ","Medic   ", "luccky  "};
    public static int[] heroesDamage = {10, 15, 20, 0, 10};
    public static int numberOfRounds;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()){
            round();
        }

    }

    public static void chooseBossDefence(){
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefenseType = heroesAttackType[randomIndex];
    }
    public static void bossHits(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if(heroesHealth[i] - bossDamage < 0){
                    heroesHealth[i] = 0;
                    heroesDamage[i] = 0;
                }else {
                heroesHealth[i] = heroesHealth[i] - bossDamage;}}
        }
    }
    public static void round(){
        numberOfRounds++;
        chooseBossDefence();
        bossHits();
        heroesHits();
        medicHeals();
        Lucky();
        printStatistics();
    }

    public static void heroesHits(){
        for (int i = 0; i < heroesDamage.length; i++) {

            if(heroesHealth[i] > 0 && bossHealth >0 ) {
                int damage = heroesDamage[i];
                if ( heroesAttackType[i] == bossDefenseType){
                    Random random = new Random();
                    int coef = random.nextInt(10)+1;
                    damage = heroesDamage[i] * coef;
                    System.out.println("Critical Damage: "+ damage);
                }
                if(bossHealth - damage < 0){
                    bossHealth = 0;
                    bossDamage = 0;
                }else{
                    bossHealth = bossHealth - damage;}
        }}
    }
    public static void printStatistics(){
        System.out.println("Round " + numberOfRounds  + "  ====================");
        System.out.println("BOSS     health:      " + bossHealth + "  damage: " + bossDamage +
                " defencetype: " +(bossDefenseType == null ? "No defence" : bossDefenseType));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health:  " + heroesHealth[i] + "  damage: " + heroesDamage[i]);
        }
                
    }
    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        } return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead){
            System.out.println("Boss won!!!");
         } return allHeroesDead;
    }
    public static void medicHeals (){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesAttackType[i] != "Medic" && heroesHealth[i] > 0 && heroesHealth[i] < 100 && heroesHealth[3] >0)
            { heroesHealth[i] = heroesHealth[i] + 111;
                System.out.println(heroesAttackType[i] + " healing + 111" + " by Medic");
            break;
                }
            }
        }
        public static void Lucky (){

        int Misss = 0;
            for (int i = 0; i < heroesAttackType.length; i++) {
                if( heroesAttackType[i] != "luccky"){
                    Misss = i;
                    break;}
                      }
                Random random = new Random();
            boolean chance = random.nextBoolean();
            if(heroesHealth[4] <= 0){
                heroesDamage[4] = 0;

            }
            if (chance && heroesHealth[Misss] > 0){
                heroesHealth[4] += bossDamage;
                System.out.println(heroesAttackType[4] + " Misss + 50 HP");

            }
        }
    }

