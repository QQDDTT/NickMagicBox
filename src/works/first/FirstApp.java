package works.first;

public class FirstApp {

    // @SuppressWarnings("unused")
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Hello World!");
        } else if (args.length == 1) {
            switch (args[0]) {
                case "1":
                    System.out.println("Hello World!");
                    break;
                default:
                    break;
            }
        } else {
            System.out.println("Hello " + args[0] + " and " + args[1] + "!");
        }

        int i = 0;
        while (true) {
            try {
                System.out.println("ARGUMENT " + i + ":" + args[i]);
            } catch (Exception e) {
                break;
            }
        }

        do {
            System.out.println("Hello World!");
        } while (false);
        

        for (int j = 0; j < 10; j++) {
            System.out.println("Hello World!");
        }

        for (String arg : args) {
            System.out.println("Hello " + arg + "!");
        }

        boolean b = true;

        byte  b1 = 1;
        short s = 1;
        int i1 = 1;
        long l = 1L;
        float f = 1.0f;
        double d = 1.0;
        char c = 'a';
        String str = "Hello World!";

        // 运算符
        int a1 = 0;
        int a2 = 1;
        int a0 = a1 + a2;
        a0 = a1 - a2;
        a0 = a1 * a2;
        a0 = a1 / a2;
        a0 = a1 % a2;
        System.out.println(a0);

        // 逻辑运算
        boolean b11 = true && false;
        boolean b12 = true || false;
        boolean b13 = !true;
        boolean b14 = true ^ false; // 异或
        boolean b15 = true & false;
        boolean b16 = true | false;

        // 位运算
        int a3 = 0b1010;
        int a4 = 0b1100;
        int a5 = a3 & a4;
        int a6 = a3 | a4;
        int a7 = a3 ^ a4;
        int a8 = ~a3; // 取反
        int a9 = a3 << 2; // 左移
        int a10 = a3 >> 2; // 右移
        int a11 = a3 >>> 2; // 无符号右移

        String str0 = "Hello World!";
        if (str0 instanceof String) {
            System.out.println("str0 is a String");
        }

    }
}
