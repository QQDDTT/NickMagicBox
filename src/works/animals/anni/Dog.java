package works.animals.anni;

public class Dog extends Beasts{
    // private static String className = Dog.class.getSimpleName();
    private String name;
    public Dog(){
        super("marry");
        this.name = "bill";
    }

    public static void getClassName(){
        System.out.println(className);
    }

    @Override
    public void eat(String food){
        super.eat(food);
        // class Bone{
        //     void reason(){
        //         System.out.println("Bone is dog's favorites.");
        //     }
        // }
        // if (food == "bone"){
        //     new Bone().reason();
        // }
    }
    public static void print(){
        System.out.println("I am Dog.");
    }

    @Override
    public void run(){
        System.out.println(String.format("Dog %s is running...", this.name));
    }
}
