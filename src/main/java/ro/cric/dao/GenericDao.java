package ro.cric.dao;

import java.util.List;

public interface GenericDao<T> {

	T getById(Long id);

	List<T> getAll();

	T persist(T entity);

	void deleteById(long id);

}
