package dbapi.repository;
import java.util.List;

public interface CRUDRepository<T> {
    List<T> findAll();
    T findById(int id);
    T save(T character);
    T update(int id, T item);
    T delete(int id);
}
