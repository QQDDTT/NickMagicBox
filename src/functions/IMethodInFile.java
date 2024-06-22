package functions;

import java.util.function.Consumer;

@FunctionalInterface
public interface IMethodInFile extends Consumer<Class<?>>{

    /*
     * @Param path classname
     */
    @Override
    public  void accept(Class<?> path);
}
