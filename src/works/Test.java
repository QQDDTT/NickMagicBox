package works;

import static  works.demo.demo01;
public class Test {

    public static void main(String[] args) {
        String t = text("hello world")
                    .concat(" ")
                    .concat("a")
                    .concat(" ")
                    .replace("l","X")
                    .substring(0, 2)
                    .toUpperCase()
                    .toLowerCase()
                    .split("").toString();
        System.out.println(t);
        demo01();
    }

    private static String text(String a) {
        System.err.println("text length : " + a.length());
        return a;
    }
}
