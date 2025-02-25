package jsonplaceholder.requests;

public interface CrudInterface<T> {
    T create(T item);

    T read(int id);

    T update(int id, T item);

    String delete(int id);

}
