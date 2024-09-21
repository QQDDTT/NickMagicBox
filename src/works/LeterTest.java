package works;

import java.time.*;
import java.time.format.*;

import application.annotation.Argument;

public class LeterTest {

    @Argument
    public static void letterTest(String[] args) {
        String[] letters = {"", "", "", ""};
        int j = 0;
        for(int i = 0; i < 26 ;i++){
            letters[j] += (char)(i + 65);
            if(i == 6 || i == 13 || i == 19){
                j++;
            }
            if(i == 16 || i == 22){
                letters[j] += " ";
            }
        }
        printForm("letters", letters);

        String a = "123";
        String b = "123";
        String c = new String(a);
        print("a == b", "" + (a == b));
        print("a == c", "" + (a == c));
        print("b == c", "" + (b == c));

    }

    private static void print(String message, String text){
        System.out.print("【");
        // date();
        // time();
        dateTime();
        System.out.print("】 - ");
        System.out.print(message);
        System.out.print(" - ");
        System.out.println(text);
    }

    private static void printForm(String message, String...text){
        String header = "【" + dateTime() + "】 - " + message + " - ";
        String spaces = "";
        for(int i = 0;i < header.length();i++){
            spaces += " ";
        }
        System.err.println(header);
        for(String t : text){
            if(t == null){
                System.out.println();
            } else{
                System.out.print(spaces);
                System.out.println(t);
            }

        }

    }

    private static void date(){
        LocalDate date = LocalDate.now();
        DateTimeFormatter date_1 = DateTimeFormatter.BASIC_ISO_DATE;
        DateTimeFormatter date_2 = DateTimeFormatter.ISO_LOCAL_DATE;
        DateTimeFormatter date_0 = DateTimeFormatter.ofPattern("yyyy 年 MM 月 dd 日");
        System.out.print(date.format(date_0));
    }

    private static void time(){
        LocalTime time = LocalTime.now();
        DateTimeFormatter time_1 = DateTimeFormatter.ISO_TIME;
        System.out.print(time.format(time_1));
    }

    private static String dateTime(){
        ZonedDateTime dateTime = ZonedDateTime.now();
        ZonedDateTime nyTime = dateTime.withZoneSameInstant(ZoneId.of("Asia/Shanghai"));
        DateTimeFormatter date_1 = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        DateTimeFormatter date_0 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        return nyTime.format(date_0);
    }
}
