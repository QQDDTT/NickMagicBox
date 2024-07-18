package works.animals.anni;

public class Dog extends Beasts{

    @Override
    public void eat(String food){
        super.eat(food);
        class Bone{
            void reason(){
                System.out.println("Bone is dog's favorites.");
            }
        }
        if (food == "bone"){
            new Bone().reason();
        }
    }
    public static void print(){
        System.out.println("I am Dog.");
    }

}
