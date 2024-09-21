package works;

import application.annotation.Argument;

public class GenericsTest {
    @SuppressWarnings("unchecked")
    @Argument
    public static void genericsTest(String...args){
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