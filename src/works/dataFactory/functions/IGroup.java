package works.dataFactory.functions;

@FunctionalInterface
public interface IGroup <O extends Object,R extends Object>{
    public R doing(O arg);
}
