import java.util.Random;
import java.util.Scanner;

// Clase bas

class SuperHero {
    // definir el constructor

    String nombre;
    int fuerza;
    int energy;
    int vida_hp;
    int defensa;

    public SuperHero(int fuerza, int energy, int vida_hp, int defensa){

        this.fuerza = fuerza;
        this.energy = energy;
        this.vida_hp = vida_hp;
        this.defensa = defensa;

    }
    public void Bono(int bono){
        if(bono == 1){
            this.fuerza += 5;
        }
        if(bono == 2){
            this.vida_hp += 30;
        }
    }
    public void Info(){
        
        System.out.println(nombre + " :" + vida_hp + " hp   energy: " + energy + " defensa: " + defensa );

    }
}

class Villano{
    // definir el constructor
    int fuerza;
    int energy;
    int vida_hp;
    int defensa;

    public Villano(int fuerza, int energy, int vida_hp,  int defensa){

        this.fuerza = fuerza;
        this.energy = energy;
        this.vida_hp = vida_hp;
        this.defensa = defensa;

    }
}


public class App {

    public static boolean combate(SuperHero s, boolean victory){

        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        Random randomNumbers = new Random();


        Villano villain = new Villano(15, 0, 100, 0);
        

        /*inicio del combate*/
        while (villain.vida_hp > 0 && s.vida_hp > 0){

            int accion = 0;
            System.out.println("villano: " + villain.vida_hp + " hp");
            s.Info();
            System.out.println("");
            System.out.println("elija una accion");
            System.out.println("_____________________________");
            System.out.println("1. atacar          2. Defender");
            System.out.println("3. Especial(60e)   4. Info");

            while(accion < 1  || accion > 3){
       
                System.out.println("turno del heroe");
                accion = scanner.nextInt();

                if(accion < 1  || accion > 4){
                    System.out.println("el numero de la accion no existe");
                }

                if(accion == 4){
                    System.out.println("informacion");
                    System.out.println("hero:");
                    s.Info();
                    System.out.println("villano:");
                    System.out.println(villain.vida_hp + " hp   energy: " + villain.energy + " defensa: " + s.defensa);
                    accion = 0;
                }

                if(accion == 3 && s.energy < 60){
                    accion = 0;
                    System.out.println("energia insuficiente");
                }

            }
            if(accion == 1){
                villain.vida_hp -= (s.fuerza - villain.defensa);
                s.energy += 10;
            }
            if(accion == 2){
                if(s.defensa < 10){
                    s.defensa += 5;
                }
                s.energy += 30;
            }
            if(accion == 3){
                villain.vida_hp -= ((s.fuerza - villain.defensa) + 20);
                s.energy -= 60;
            }

            if(villain.vida_hp > 0 && s.vida_hp > 0){
                System.out.println("turno del villano");

                int villainAction = (randomNumbers.nextInt(2) + 1);

                if (villainAction == 1){
                    System.out.println("el villano ataca");
                    s.vida_hp -= (villain.fuerza - s.defensa);
                    villain.energy += 10;
                }
                if(villainAction == 2){

                    System.out.println("el villano se defiende");
                    if(villain.defensa < 10){
                        villain.defensa += 5;
                    }
                    villain.energy += 30;

                }
                if(villain.energy >= 60){
                    System.out.println("el villano hace trampa y ataca de nuevo");
                    villain.energy -= 60;
                    s.vida_hp -= (villain.fuerza - s.defensa);

                }
                System.out.println("");
            }

        }

        if(villain.vida_hp <= 0){
            victory = true;
        }

        if(villain.vida_hp > 0){
            victory = false;
        }
        return victory;
    }
    public static void main(String[] args) throws Exception {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        int selectBono = 0;
        
        System.out.println("la ciudad se encuentra en peligro");
        System.out.println("un supervillano y sus minions estan atacando el centro de la ciudad");
        System.out.println("Escriba el nombre del heroe");
        SuperHero hero = new SuperHero( 20, 0, 100, 0);
        hero.nombre = scanner.nextLine();

        System.out.println("un minion del villano se encuentra en tu camino");

        boolean Win_Loose_First_Battle = combate(hero, false);
        if(Win_Loose_First_Battle == true){
            System.out.println("you win");
        }else{
            System.out.println("you loose");
            System.out.println("un minion del supervillano te a derrotado");
        }

        if(Win_Loose_First_Battle == true){

            System.out.println("estadisticas actuales: ");
            hero.Info();
            System.out.println("elije un potenciador por tu victoria");
            System.out.println("1. para + 5 en fuerza");
            System.out.println("2. para + 30 en vida");

            while(selectBono < 1  || selectBono > 2 ){
                selectBono = scanner.nextInt();
                System.out.println("el numero de la accion no existe");
            }
            selectBono = 0;

            hero.Bono(selectBono);

            System.out.println("Otro minion del villano se encuentra en tu camino");
            boolean Win_Loose_Second_Battle = combate(hero, false);

            if(Win_Loose_Second_Battle == true){
                System.out.println("you win");
            }else{
                System.out.println("you loose");
                System.out.println("un minion del supervillano te a derrotado");
            }
            if(Win_Loose_Second_Battle == true){

                System.out.println("estadisticas actuales: ");
                hero.Info();
                System.out.println("elije un potenciador por tu victoria");
                System.out.println("1. para + 5 en fuerza");
                System.out.println("2. para + 30 en vida");

                while(selectBono < 1  || selectBono > 2 ){
                    selectBono = scanner.nextInt();
                    System.out.println("el numero de la accion no existe");
                }
                selectBono = 0;

                hero.Bono(selectBono);

                System.out.println("el jefe villano se encuentra en tu camino");
                boolean Win_Loose_Third_Battle = combate(hero, false);

                if(Win_Loose_Third_Battle == true){
                    System.out.println("you win");
                    System.out.println("Derrotaste a todos los villanos y salvaste la ciudad");
                    System.out.println("estadisticas finales");
                    hero.Info();
                }else{
                    System.out.println("you loose");
                    System.out.println("El jefe de los villanos te derroto, ya no queda esperanza");
                }

            }
        }

    }
}
