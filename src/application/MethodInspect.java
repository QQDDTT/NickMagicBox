package application;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;
import java.util.Map.Entry;

import application.annotation.Argument;
import application.annotation.Arguments;

public class MethodInspect {
    private static MethodInspect INSPECTOR = new MethodInspect();

    private Class<?> clazz;
    private Method method;
    private InspectEnum status;
    private Map<String,String[]> argsMap;

    private MethodInspect() {
        this.status = InspectEnum.START;
        this.argsMap = new TreeMap<String, String[]>();
    }

    public static void invok(Method method){
        INSPECTOR.before(method).doing(method).after();
    }

    private MethodInspect before(Method method) {
        this.status = InspectEnum.START;
        this.clazz = method.getDeclaringClass();
        this.method = method;
        this.argsMap = new TreeMap<String, String[]>();
        Printer.info("************************************************************");
        Printer.info(clazz, method, new String[]{this.status.getMsg()});
        if(!Modifier.isStatic(method.getModifiers())){
            this.status = InspectEnum.ERROR;
            Printer.err(this.clazz, this.method, new String[]{InspectEnum.ERROR.getMsg(), "method must be static!"});
        }
        if(method.getParameterTypes().length != 1 && method.getParameterTypes()[0] != String[].class){
            this.status = InspectEnum.ERROR;
            Printer.err(this.clazz, this.method, new String[]{InspectEnum.ERROR.getMsg(), "method argument type must be Strinrg... or String[]"});
        }

        Annotation[] annos = method.getAnnotations();
        int argsLength = 0;
        if(annos.length > 0) {
            for(Annotation anno : annos){
                if(anno.annotationType().equals(Argument.class)) {
                    Argument arg = (Argument)anno;
                    if(arg.key() == null || arg.values() == null){
                        Printer.warn(clazz, method, new String[]{"Annotation value or key is null !"});
                        continue;
                    }
                    if(arg.key().isEmpty() || arg.values().length == 0){
                        Printer.warn(clazz, method, new String[]{"Annotation value or key is empty !"});
                        continue;
                    }
                    this.argsMap.put(arg.key(), arg.values());
                    argsLength++;
                }
            }
        }
        if (this.argsMap.size() == 0){
            Printer.warn(clazz, method, new String[]{"Do not have parameter to invok method !"});
        }
        if(argsLength != argsMap.size()) {
            Printer.warn(this.clazz, this.method, new String[]{"Annotation is incomplete !"
            ,"Annotation[" + argsLength+ "]"
            ,"map[" + this.argsMap.size() + "]"});
        }
        return this;
    }

    private MethodInspect doing(Method method){
        Printer.info(this.clazz, this.method, new String[]{"Inspect doing"});
        if (this.status != InspectEnum.ERROR) {
            try{
                if(this.argsMap.size() > 0) {
                    while(true){
                        Printer.info("Select key to invok");
                        Printer.info("0 Cancle");
                        int i = 0;
                        for(Entry<String,String[]> e : this.argsMap.entrySet()){
                            Printer.info(++i + "  " + e.getKey());
                        }
                        @SuppressWarnings("resource")
                        int key = new Scanner(System.in).nextInt();
                        if(key == 0){
                            break;
                        }
                        Printer.info("👇👇👇👇👇","");
                        method.invoke(null, (Object) this.argsMap.entrySet().toArray()[key]);
                        System.out.println();
                        Printer.info("👆👆👆👆👆");
                    }
                } else {
                    Printer.warn("Annotation have no parameter");
                    Printer.info("👇👇👇👇👇","");
                    method.invoke( null,(Object)new String[0]);
                    System.out.println();
                    Printer.info("👆👆👆👆👆");
                }
            } catch (Exception e) {
                Printer.err(this.clazz, this.method, new String[]{"invok faild", e.getMessage()});
            }
        }
        return this;
    }

    private void after(){
        this.status = InspectEnum.END;
        this.argsMap = new TreeMap<String, String[]>();
        Printer.info(this.clazz, this.method, new String[]{InspectEnum.END.getMsg()});
        Printer.info("************************************************************");
    }
    enum InspectEnum {
        START("Inspect start"),
        ERROR("Inspect error"),
        END("Inspect finished"),
        ;
        private String message;
        InspectEnum(String message){
            this.message = message;
        }
        private String getMsg(){
            return this.message;
        }
    }
}


