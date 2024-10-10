package works.lambda;

import java.util.function.*;
public class Dog {
    private int id;
    private String name;
    private int age;
    private String namePlate;
    public Dog (int id, String name,int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public void setId (int id) {
        this.id = id;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId () {
        return this.id;
    }
    public String getName () {
        return this.name;
    }

    public int getAge () {
        return this.age;
    }

    public Dog createNameplate (Function<Dog, String> creater) {
        this.namePlate = creater.apply(this);
        System.out.println("Dog " + this.name + " create nameplate:");
        System.out.println(this.namePlate);
        return this;
    }

    public void run (Consumer<String> con) {
        con.accept(this.namePlate);
    }
}
