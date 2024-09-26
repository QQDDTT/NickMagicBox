package application;

import java.time.ZonedDateTime;
import java.lang.reflect.Method;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Printer {
    private static final String APPLICATION_NAME = "MAGIC BOX";
    // 
    private static final ZoneId ZONE_ID = ZoneId.of("Asia/Tokyo");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.SSS");
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
        for (int i = 0 ; i < length / 2 + 5 ; i++) {
            result += " ";
        }
        return result;
    }

    public static void info(Class<?> clazz, Method method, String...message) {
        String frontText = WHITE + getDateTime() + RESET
                            + " - "
                            + CYAN + APPLICATION_NAME + RESET
                            + " - "
                            + PURPLE + clazz.getSimpleName() + RESET
                            + " - "
                            + BLUE + method.getName() + RESET
                            + " - "
                            + GREEN + " [INFO] " + RESET;
        for(int i = 0 ; i < message.length ; i++) {
            if(i == 0){
                System.out.print(frontText);
                System.out.println(WHITE + message[i] + RESET);
            } else {
                System.out.print(getSpaces(frontText.length()));
                System.out.println(WHITE + message[i] + RESET);
            }
        }
    }

    public static void info(String...message) {
        String frontText = WHITE + getDateTime() + RESET
                            + " - "
                            + CYAN + APPLICATION_NAME + RESET
                            + " - "
                            + GREEN + " [INFO] " + RESET;
        for(int i = 0 ; i < message.length ; i++) {
            if(i == 0){
                System.out.print(frontText);
                System.out.println(WHITE + message[i] + RESET);
            } else {
                System.out.print(getSpaces(frontText.length()));
                System.out.println(WHITE + message[i] + RESET);
            }
        }
    }

    public static void err(Class<?> clazz, Method method, String...error) {
        System.out.print(WHITE + getDateTime() + RESET);
        String frontText = WHITE + getDateTime() + RESET
                            + " - "
                            + CYAN + APPLICATION_NAME + RESET
                            + " - "
                            + PURPLE + clazz.getSimpleName() + RESET
                            + " - "
                            + BLUE + method.getName() + RESET
                            + " - "
                            + RED + " [ERROR] " + RESET;
        for(int i = 0 ; i < error.length ; i++) {
            if(i == 0){
                System.out.print(frontText);
                System.out.println(RED + error[i] + RESET);
            } else {
                System.out.print(getSpaces(frontText.length()));
                System.out.println(RED + error[i] + RESET);
            }
        }
    }

    public static void err(String...error) {
        String frontText = WHITE + getDateTime() + RESET
                            + " - "
                            + CYAN + APPLICATION_NAME + RESET
                            + " - "
                            + RED + " [ERROR] " + RESET;
        for(int i = 0 ; i < error.length ; i++) {
            if(i == 0){
                System.out.print(frontText);
                System.out.println(RED + error[i] + RESET);
            } else {
                System.out.print(getSpaces(frontText.length()));
                System.out.println(RED + error[i] + RESET);
            }
        }
    }

    public static void warn(Class<?> clazz, Method method, String...message) {
        String frontText = WHITE + getDateTime() + RESET
                            + " - "
                            + CYAN + APPLICATION_NAME + RESET
                            + " - "
                            + PURPLE + clazz.getSimpleName() + RESET
                            + " - "
                            + BLUE + method.getName() + RESET
                            + " - "
                            + YELLOW + " [WARNNING] " + RESET;
        for(int i = 0 ; i < message.length ; i++) {
            if(i == 0){
                System.out.print(frontText);
                System.out.println(YELLOW + message[i] + RESET);
            } else {
                System.out.print(getSpaces(frontText.length()));
                System.out.println(YELLOW + message[i] + RESET);
            }
        }
    }

    public static void warn(String...message) {
        String frontText = WHITE + getDateTime() + RESET
                            + " - "
                            + CYAN + APPLICATION_NAME + RESET
                            + " - "
                            + YELLOW + " [WARNNING] " + RESET;
        for(int i = 0 ; i < message.length ; i++) {
            if(i == 0){
                System.out.print(frontText);
                System.out.println(YELLOW + message[i] + RESET);
            } else {
                System.out.print(getSpaces(frontText.length()));
                System.out.println(YELLOW + message[i] + RESET);
            }
        }
    }
}
