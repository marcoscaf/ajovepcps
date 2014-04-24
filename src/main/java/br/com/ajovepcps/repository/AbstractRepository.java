package br.com.ajovepcps.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;

import br.com.ajovepcps.exceptions.MemberConstraintViolationException;
import br.com.ajovepcps.factory.EntityManagerFactory;

public abstract class AbstractRepository<T> implements Repository<T> {
	
	protected EntityManager em;

	private T entity;
	
	public AbstractRepository(){
		em = EntityManagerFactory.getEntityManager();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T find(Long id) {
		return (T) em.find(entity.getClass(), id);
	}

	@Override
	public void remove(T entity) {
		em.remove(em.merge(entity));
	}

	@Override
	public void save(T entity) throws Exception {

		try {

			em.getTransaction().begin();

			em.persist(entity);

			em.getTransaction().commit();
			
		} catch (Exception e) {
			
			em.getTransaction().rollback();
			throw e;

		}

	}

	@Override
	public T update(T entity) {
		entity = em.merge(entity);
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByNamedQuery(String namedQuery) {
		return em.createQuery(namedQuery).getResultList();
	}

	public EntityManager getEm() {
		return em;
	}



}
