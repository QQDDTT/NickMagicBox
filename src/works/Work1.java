package works;

import pointers.MethodAnnotation;

public class Work1 {
    public static void main(String...args){
        System.out.println("Work1 main : " + String.join(" & ", args));
    }

    @MethodAnnotation(args={"1","2","3"})
    public static void run(String...args){
        System.out.println("Work1 run : " + String.join(" & ", args));
    }
}
