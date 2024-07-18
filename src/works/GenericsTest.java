package works;

public class GenericsTest {
    @SuppressWarnings("unchecked")
    public static void main(String...args){
        @SuppressWarnings("rawtypes")
        InnerClass i = new InnerSubClass();
        i.fun("");
    }
}

class InnerClass <E> {
    public void fun(E e){
        System.out.println(e.getClass());
    }
}

@SuppressWarnings("rawtypes")
class InnerSubClass extends InnerClass{
    public <T extends Object> void doing(T t){
        System.out.println(t.getClass());
    }
}