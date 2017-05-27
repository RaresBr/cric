package ro.cric.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ro.cric.dao.GenericDao;

public class GenericDaoImpl<T> implements GenericDao<T> {

	@PersistenceContext
	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public T getById(Long id) {
		String entityName = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]
				.getTypeName();
		Query query = entityManager.createQuery("from " + entityName + " where id=:entityId");
		query.setParameter("entityId", id);

		T result = (T) query.getSingleResult();

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		String entityName = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]
				.getTypeName();
		Query query = entityManager.createQuery("from " + entityName);
		return (List<T>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteById(long id) {
		T object = (T) entityManager.find(
				(Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0], id);
		entityManager.remove(object);
	}

	@Override
	public T persist(T entity) {
		T persistedEntity = entityManager.merge(entity);
		return persistedEntity;
	}

}
