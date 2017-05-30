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

	@SuppressWarnings("unchecked")
	@Override
	public boolean doesEmailExist(String email) {
		String entityName = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]
				.getTypeName();
		Query query = entityManager.createQuery("from " + entityName + " as u where u.email=:email")
				.setParameter("email", email);

		List<T> list = query.getResultList();

		if (list.isEmpty())
			return false;

		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean doesUsernameExist(String username) {
		String entityName = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]
				.getTypeName();
		Query query = entityManager.createQuery("from " + entityName + " as u where u.username=:username")
				.setParameter("username", username);

		List<T> list = query.getResultList();

		if (list.isEmpty())
			return false;

		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getByCredentials(String password, String username) {
		String entityName = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]
				.getTypeName();
		Query query = entityManager
				.createQuery("from " + entityName
						+ " as u where u.username=:searched_username and u.password=:searched_password")
				.setParameter("searched_username", username).setParameter("searched_password", password);
		List<T> list = query.getResultList();

		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

}
