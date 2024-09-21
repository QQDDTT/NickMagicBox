package functions;

import java.util.function.Consumer;

@FunctionalInterface
public interface IClassConsumer extends Consumer<Class<?>>{
    /*
     * @Param path classname
     */
    @Override
    public  void accept(Class<?> path);
}
