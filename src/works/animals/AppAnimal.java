package works.animals;

import application.annotation.Argument;
import works.animals.anni.*;


public class AppAnimal {
    @Argument
    public static void animalTest(String... args){
        
        Dog d = new Dog();
        d.run();
        Beasts b = new Dog();
        Dog d2 = (Dog)b;
        d2.run();
        b.getClassName();
        d2.getClassName();
    } 

}

