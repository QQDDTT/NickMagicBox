package works.dataFactory.functions;

@FunctionalInterface
public interface ISort<O extends Object,I extends Number> {
    public I doing(O arg);
}
