package arboleos;

public interface SearchMethods<T> {
    SearchRoute<T> depthSearch(T data);

    SearchRoute<T> breadthSearch(T data);


}
