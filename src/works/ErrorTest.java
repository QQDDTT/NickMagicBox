package works;

public class ErrorTest {
    public static void main(String...args) {
        try(MySource s = new MySource()){
            s.test();
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static String step1(String str){
        try {
            fun(1);
            System.out.println("Next");
            return str;
        } catch (Exception e) {
            // e.printStackTrace();
            System.err.println(e.getMessage());
            str = "Error";
            return str;
        }
        finally{
            str = "Final";
            System.out.println("Finally");
        }
    }

    static void fun(int a) {
        if(a == 1){
            throw new InnerExeption("InnerExeption");
        }else{
            throw new InnerExeption2();
        }
    }
}

class InnerExeption extends RuntimeException{

    public InnerExeption(String string) {
        super(string);
    }

}

class InnerExeption2 extends RuntimeException{

}

class MySource implements AutoCloseable{

    @Override
    public void close() throws Exception {
        System.out.println("close");
    }

    public void test(){
        System.out.println("test");
        throw new InnerExeption("Faild");
    }
}
