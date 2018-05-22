package jdbc;

import java.util.List;


public interface DAO<T> {
	public void add(T t);
    public void update(T t);
    public void delete(int id);
    public T get(int id);
    public List<T> list();
    public List<T> list(int start, int count);
}
