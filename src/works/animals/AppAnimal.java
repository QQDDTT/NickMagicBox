package works.animals;

import works.animals.anni.*;


public class AppAnimal {
    public static void main(String... args){
        
        Beasts beasts = new Beasts(); 
        beasts.setName("Bill");
        beasts.run();

        Beasts b = new Beasts("Joe");
        b.run();
    } 

}

