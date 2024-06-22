package works.dataFactory.functions;

import java.lang.reflect.*;

public interface IInvoke {
    public Method[] getMethodsInClass(Class<?> c);
    public Object invokeMethod(Class<?> c,Method m,Object...args);
    public void setArgs(Object... objs);
}
