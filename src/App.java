import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Stream;

import application.MethodInspect;
import application.Printer;
import application.annotation.Argument;

import functions.IClassConsumer;
import functions.IMethodConsumer;

public class App {
    private static final String BASE_PATH = "./src/works";
    private static List<Method> METHODS = new ArrayList<>();
    public static void main(String...args) throws Exception {        
        eachAnnotationMethods(new IMethodConsumer() {
            @Override
            public void accept(Method method) {
                METHODS.add(method);
            }
        });
        while(true){
            viewAnnotationMethods();
            int num = inputNum();
            if(num == 0){
                break;
            }else{
                runMethod(num);
            }
            if(continueWork()){
                continue;
            }else{
                break;
            }
        }
        close();
    }


    //view methods have annotation
    private static void viewAnnotationMethods(){
        Queue<String> viewForm = new LinkedBlockingQueue<String>();
        viewForm.add("Select number to invok :");
        viewForm.add("[Count : 0] - Quit");
        for(int i = 0 ; i < METHODS.size() ; i++) {
            Method m = METHODS.get(i);
            viewForm.add("[Count : "  + (i + 1) + "] - " + m.getDeclaringClass().getSimpleName() + " - " + m.getName());
        }
        Printer.info(viewForm.toArray(new String[]{}));
    }

    private static int inputNum(){
        try{
            @SuppressWarnings("resource")
            int num = new Scanner(System.in).nextInt();
            return num;
        }catch(Exception e){
            Printer.err("Please enter number !");
            return inputNum();
        }
    }

    //invoke methods have annotation at count
    private static void runMethod(int index){
        try{
            MethodInspect.invok(METHODS.get(index - 1));
        }catch(Exception e){
            Printer.err(e.getMessage());
        }
    }

    //
    private static boolean continueWork(){
        Printer.info("Enter <Y/N> to continue");
        @SuppressWarnings("resource")
        String str = new Scanner(System.in).nextLine().toUpperCase();
        if("Y".equals(str)){
            return true;
        }else if("N".equals(str)){
            return false;
        }else{
            return continueWork();
        }
    }

    private static void close(){
        Printer.info("You are welcome !", "Bye...");
    }

    //each method has Annotation Main(**)
    private static void eachAnnotationMethods(IMethodConsumer func){
        eachJavaFile(new IClassConsumer() {
            @Override
            public void accept(Class<?> clazz) {
                if(clazz == null)return;
                Stream.of(clazz.getMethods())
                .filter(m -> m.isAnnotationPresent(Argument.class))
                .filter(m -> m.getParameterTypes()[0] == String[].class)
                .forEach(func);
            }
        });
    }

    //search main method by rule
    private static void eachJavaFile(IClassConsumer func){
        try {
            Files.walk(Paths.get(BASE_PATH))
                .filter(file -> !Files.isDirectory(file))
                .filter(file -> file.toString().endsWith(".java"))
                .map(p -> {
                    String path = "";
                    for(int i = 2 ; i < p.getNameCount() ; i++){
                        path += p.getName(i) + ".";
                    }
                    if(path.endsWith(".java.")){
                        path = path.substring(0,path.length() - 6);
                    }
                    try {
                        return ClassLoader.getSystemClassLoader().loadClass(path);
                    } catch (ClassNotFoundException e) {
                        Printer.err(e.getMessage());
                        return null;
                    }
                })
                .filter(c -> App.class != c)
                .forEach(func);
        } catch (IOException e) {
            Printer.err(e.getMessage());
        }
    }
}
