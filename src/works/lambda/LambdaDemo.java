package works.lambda;
import java.util.function.*;
public class LambdaDemo {

    // () -> {*****}
    // a -> "TEST"
    // (a) -> {return "TEST"}
    /* a, b -> { 
        int a = 1;
        return b;
    } */ 

    public static void main(String[] args) {
        
        func(new Function<Integer,Integer>(){
                    @Override
                    public Integer apply(Integer a) {
                        return a + 1;
                    }
        });

        func(a -> a + 1);

        pre(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer a) {
                        return a > 1;
                    }
        });

        pre(a -> a > 1);

        con(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer a) {
                        System.out.println(a);
                    }
        });

        con(a -> System.out.println(a));

        sup(new Supplier<Integer>() {
                    @Override
                    public Integer get() {
                        return 1;
                    }
        });

        sup(() -> 1);

        Dog d = new Dog (001, "Bill", 3);

        d.createNameplate(a -> "<ID:" + a.getId() + " Name:" + a.getName() + ">")
        .run(a -> System.out.println("I have plate :" + a));

    }

    static void func(Function<Integer,Integer> func){

    }

    static void pre(Predicate<Integer> func){

    }

    static void con(Consumer<Integer> func){

    }

    static void sup(Supplier<Integer> func){

    }

    static void ope(UnaryOperator<Integer> func){

    }
}
