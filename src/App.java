import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
import functions.IClassConsumer;
import functions.IMethodConsumer;
import pointers.MethodAnnotation;

public class App {
    private static final String BASE_PATH = "./src";
    private static List<Method> METHODS = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        while(true){
            
            print("select number to invok :");
            viewMainMethods();
            viewAnnotationMethods();
            System.out.println("[Count : 0] Quit");
            // runMainMethods();
            // runAnnotationMethods();
            @SuppressWarnings("resource")
            int num = new Scanner(System.in).nextInt();
            if(num == 0){
                break;
            }else{
                runMethod(METHODS.get(num - 1));
            }
        }
    }

    //view main method
    private static void viewMainMethods(){
        eachMainMethods(new IMethodConsumer() {
            @Override
            public void accept(Method method) {
                METHODS.add(method);
                System.out.println("[Count : "  + (METHODS.indexOf(method) + 1) + "] Main Method : " + method.toGenericString());
            }
        });
    }

    //view methods have annotation
    private static void viewAnnotationMethods(){
        eachAnnotationMethods(new IMethodConsumer() {
            @Override
            public void accept(Method method) {
                METHODS.add(method);
                System.out.println("[Count : "  + (METHODS.indexOf(method) + 1) + "] Annotation Method : " + method.toGenericString());
            }
        });
    }

    //invoke methods have annotation at count
    private static void runMethod(Method method){
        print("[Run] : "  + method.toGenericString());
            // Stream.of(Runtime.class.getMethods())
            //     .filter(m -> m.getName().startsWith("get"))
            //     .forEach(m -> {
            //         try {
            //             m.invoke(Runtime.getRuntime());
            //         } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            //             print(e);
            //         }
            // });
            try {
                method.invoke(null, (Object)new String[]{});
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                print(e);
            }
        print("[end] : " + method.toGenericString());
    }
    //each main method
    private static void eachMainMethods(IMethodConsumer func){
        eachJavaFile(new IClassConsumer() {
            @Override
            public void accept(Class<?> clazz) {
                if(clazz ==  null) return;
                Stream.of(clazz.getMethods())
                .filter(m -> m.getName() == "main")
                .filter(m -> Modifier.isStatic(m.getModifiers()) && Modifier.isPublic(m.getModifiers()))
                .filter(m -> m.getReturnType() == void.class)
                .filter(m -> m.getParameterTypes()[0] == String[].class)
                .forEach(func);
            }
        });
    }

    //each method has MethodAnnotation
    private static void eachAnnotationMethods(IMethodConsumer func){
        eachJavaFile(new IClassConsumer() {

            @Override
            public void accept(Class<?> clazz) {
                if(clazz == null)return;
                Stream.of(clazz.getMethods())
                .filter(m -> m.isAnnotationPresent(MethodAnnotation.class))
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
                        print(e);
                        return null;
                    }
                })
                .filter(c -> App.class != c)
                .forEach(func);
        } catch (IOException e) {
            print(e);
        }
    }


    
    private static void print(Object msg){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(msg instanceof Throwable){
            System.err.print(sdf.format(new Date()) + " [MagicBox ERROR] ");
            System.err.println(msg);
        }else{
            System.out.print(sdf.format(new Date()) + " [MagicBox] ");
            System.out.println(msg);
        }
    }
}
