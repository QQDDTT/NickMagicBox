package pointers;

import java.lang.reflect.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;

import application.Printer;
import application.annotation.Argument;
import application.annotation.Arguments;

public class MethodInspect {
    private static MethodInspect INSPECTOR = new MethodInspect();

    private String className;
    private String methodName;
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
        this.className = method.getDeclaringClass().getSimpleName();
        this.methodName = method.getName();
        Printer.info("************************************************************");
        Printer.info(className, methodName, new String[]{this.status.getMsg()});
        if(method.getParameterTypes().length != 1 && method.getParameterTypes()[0] != String[].class){
            this.status = InspectEnum.ERROR;
            Printer.err(this.className, this.methodName, new String[]{InspectEnum.ERROR.getMsg(), "method argument type must be Strinrg... or String[]"});
        }
        Argument[] args = method.getAnnotation(Arguments.class).value();
        for(Argument arg : args) {
            argsMap.put(arg.key(), arg.values());
        }
        if(args.length != argsMap.size()) {
            Printer.warn(this.className, this.methodName, new String[]{"Annotation is incomplete !"});
        }
        return this;
    }

    private MethodInspect doing(Method method){
        if (this.status != InspectEnum.ERROR) {
            try{
                Printer.info("Select key to invok");
                Printer.info(this.argsMap.entrySet().toArray(new String[]{}));
                String key = new Scanner(System.in).nextLine();
                method.invoke(null, this.argsMap.get(key));
            } catch (Exception e) {
                Printer.err(className, methodName, new String[]{"invok faild", e.getMessage()});
            }
        }
        return this;
    }

    private void after(){
        this.status = InspectEnum.END;
        this.argsMap = new TreeMap<String, String[]>();
        Printer.info(this.className, this.methodName, new String[]{InspectEnum.END.getMsg()});
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


