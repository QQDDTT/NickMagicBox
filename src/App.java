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
            @SuppressWarnings("resource")
            int num = new Scanner(System.in).nextInt();
            if(num == 0){
                break;
            }else{
                runMethod(METHODS.get(num - 1));
            }
        }
    }


    //view methods have annotation
    private static void viewAnnotationMethods(){
        Queue<String> viewForm = new LinkedBlockingQueue<String>();
        viewForm.add("select number to invok :");
        viewForm.add("[Count : 0] - Quit");
        for(int i = 0 ; i < METHODS.size() ; i++) {
            Method m = METHODS.get(i);
            viewForm.add("[Count : "  + (i + 1) + "] - class : " + m.getDeclaringClass().getSimpleName() + " - Method : " + m.getName());
        }
        Printer.info(viewForm.toArray(new String[]{}));
    }

    //invoke methods have annotation at count
    private static void runMethod(Method method){
        MethodInspect.invok(method);
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
