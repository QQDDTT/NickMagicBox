package works;

import application.Printer;
import application.annotation.Argument;

public class ExceptionTest {

    @Argument
    public static void exceptionTest(String...args){
        Printer.info("main", work(""));
    }

    private static String work(String a){
        try{
            testA();
            testB();
            a = "true";
            return a;
        }catch(AException e){
            Printer.err(e.getMessage());
            a = "false";
            Printer.err(a);
            return a;
        }catch(BException e){
            return "";
        }finally{
            a = "finally";
            Printer.info(a);
        }
    
    }

    private static void test() throws Exception{
        throw new Exception("message");
    }

    private static void test(String a){
        throw new RuntimeException("RuntimeException");
    }

    private static void testA() throws AException{
        throw new AException("A");
    }

    private static void testB() throws BException{
        throw new BException("B");
    }
}

class AException extends Exception {
    public AException(String message){
        super(message);
    }
}

class BException extends Exception {
    public BException(String message){
        super(message);
    }
}