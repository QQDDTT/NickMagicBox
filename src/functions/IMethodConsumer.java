package functions;

import java.lang.reflect.Method;
import java.util.function.Consumer;

public interface IMethodConsumer extends Consumer<Method>{
    /*
     * @Param path method
     */
    @Override
    public  void accept(Method method);
}
