package works.colorPrint;

import application.annotation.Argument;

public class ANSI {
    // ANSI 转义码
    public static final String RESET = "\033[0m";  // 重置颜色
    public static final String RED = "\033[0;31m"; // 红色
    public static final String GREEN = "\033[0;32m"; // 绿色
    public static final String YELLOW = "\033[0;33m"; // 黄色
    public static final String BLUE = "\033[0;34m"; // 蓝色
    public static final String PURPLE = "\033[0;35m"; // 紫色
    public static final String CYAN = "\033[0;36m"; // 青色
    public static final String WHITE = "\033[0;37m"; // 白色

    @Argument
    public static void ansiTest(String[] args) {
        System.out.println(RED + "red" + RESET);
        System.out.println(GREEN + "green" + RESET);
        System.out.println(YELLOW + "yellow" + RESET);
        System.out.println(BLUE + "blue" + RESET);
        System.out.println(PURPLE + "purple" + RESET);
        System.out.println(CYAN + "cyan" + RESET);
        System.out.println(WHITE + "white" + RESET);
    }
}