package application;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Printer {
    private static final String APPLICATION_NAME = "MAGIC BOX";
    // 
    private static final ZoneId ZONE_ID = ZoneId.of("Asia/Tokyo");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
    // ANSI 转义码
    private static final String RESET = "\033[0m";  // 重置颜色
    private static final String RED = "\033[0;31m"; // 红色
    private static final String GREEN = "\033[0;32m"; // 绿色
    private static final String YELLOW = "\033[0;33m"; // 黄色
    private static final String BLUE = "\033[0;34m"; // 蓝色
    private static final String PURPLE = "\033[0;35m"; // 紫色
    private static final String CYAN = "\033[0;36m"; // 青色
    private static final String WHITE = "\033[0;37m"; // 白色
    
    /**
     * 
     * 
     * @return 
     */
    private static String getDateTime(){
        return ZonedDateTime.now().withZoneSameInstant(ZONE_ID).format(DATE_TIME_FORMATTER);
    }

    private static String getSpaces(int length) {
        String result = "";
        for (int i = 0 ; i < length ; i++) {
            result += " ";
        }
        return result;
    }

    public static void info(String className, String method, String...message) {
        System.out.print(WHITE + getDateTime() + RESET);
        System.out.print(" - ");
        System.out.print(CYAN + APPLICATION_NAME + RESET);
        System.out.print(" - ");
        System.out.print(PURPLE + className + RESET);
        System.out.print(" - ");
        System.out.print(BLUE + method + RESET);
        System.out.print(" - ");
        System.out.print(GREEN + " [INFO] " + RESET);
        int len = 32 + className.length() + method.length();
        for(String m : message) {
            System.out.print(getSpaces(len));
            System.out.println(WHITE + m + RESET);
        }
    }

    public static void info(String...text) {
        System.out.print(WHITE + getDateTime() + RESET);
        System.out.print(" - ");
        System.out.print(CYAN + APPLICATION_NAME + RESET);
        System.out.print(" - ");
        System.out.print(GREEN + " [INFO] " + RESET);
        int len = 12;
        for(String t : text) {
            System.out.print(getSpaces(len));
            System.out.println(WHITE + t + RESET);
        }
    }

    public static void err(String className, String method, String...message) {
        System.out.print(WHITE + getDateTime() + RESET);
        System.out.print(" - ");
        System.out.print(CYAN + APPLICATION_NAME + RESET);
        System.out.print(" - ");
        System.out.print(PURPLE + className + RESET);
        System.out.print(" - ");
        System.out.print(BLUE + method + RESET);
        System.out.print(" - ");
        System.out.print(RED + " [ERROR] " + RESET);
        int len = 32 + className.length() + method.length();
        for(String m : message) {
            System.out.print(getSpaces(len));
            System.out.println(RED + m + RESET);
        }
    }
    public static void err(String...error) {
        System.out.print(WHITE + getDateTime() + RESET);
        System.out.print(" - ");
        System.out.print(CYAN + APPLICATION_NAME + RESET);
        System.out.print(" - ");
        System.out.print(GREEN + " [INFO] " + RESET);
        int len = 12;
        for(String e : error) {
            System.out.print(getSpaces(len));
            System.out.println(RED + e + RESET);
        }
    }

    public static void warn(String className, String method, String...message) {
        System.out.print(WHITE + getDateTime() + RESET);
        System.out.print(" - ");
        System.out.print(CYAN + APPLICATION_NAME + RESET);
        System.out.print(" - ");
        System.out.print(PURPLE + className + RESET);
        System.out.print(" - ");
        System.out.print(BLUE + method + RESET);
        System.out.print(" - ");
        System.out.print(YELLOW + " [WARNNING] " + RESET);
        int len = 32 + className.length() + method.length();
        for(String m : message) {
            System.out.print(getSpaces(len));
            System.out.println(YELLOW + m + RESET);
        }
    }

    public static void warn(String...text) {
        System.out.print(WHITE + getDateTime() + RESET);
        System.out.print(" - ");
        System.out.print(CYAN + APPLICATION_NAME + RESET);
        System.out.print(" - ");
        System.out.print(GREEN + " [INFO] " + RESET);
        int len = 12;
        for(String t : text) {
            System.out.print(getSpaces(len));
            System.out.println(YELLOW + t + RESET);
        }
    }
}
