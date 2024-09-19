package works;

import application.annotation.Argument;
import application.annotation.Arguments;

public class Work1 {
    public static void main(String...args){
        System.out.println("Work1 main : " + String.join(" & ", args));
    }

    @Arguments({@Argument})
    public static void run(String...args){
        System.out.println("Work1 run : " + String.join(" & ", args));
    }
}
