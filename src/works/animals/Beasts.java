package works.animals;

public class Beasts implements IAnimalDo{

    public static  String className;
    private final String name;

    public Beasts(){
        this.name = "";
    }
    public static void getClassName(){
        System.out.println(className);

    }
    @Override
    public void run() {
        System.out.println(this.name + " is running..");
    }

    @Override
    public void eat(String food) {
        System.out.println(this.name + " is eating " + food);
    }

    @Override
    public void sleep(int time) {
        System.out.println(this.name + " is sleeping " + time + " s.");
    }


    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
