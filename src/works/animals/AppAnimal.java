package works.animals;

public class AppAnimal {
    public static void main(String... args){
        Beasts b1 = new Dog();
        b1.setName("Jack");
        System.out.println(Beasts.className);
        System.out.println(Dog.className);
        System.out.println(b1.className);
        b1.className = "Dog";
        System.out.println(Beasts.className);
        System.out.println(Dog.className);
        System.out.println(b1.className);
        Dog b2 = (Dog)b1;
        System.out.println(Beasts.className);
        System.out.println(Dog.className);
        System.out.println(b2.className);
    } 
}
