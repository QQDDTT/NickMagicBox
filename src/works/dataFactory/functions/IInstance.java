package works.dataFactory.functions;
import java.lang.reflect.Constructor;

@SuppressWarnings("rawtypes")
public interface IInstance {
    public Constructor[] getConstructorsInClass(Class<?> c);
    public Object instanceConstructor(Class<?> c,Constructor con,Object...args);
    public void setArgs(Object... objs);
}
