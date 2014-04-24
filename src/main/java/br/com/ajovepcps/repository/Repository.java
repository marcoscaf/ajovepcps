package br.com.ajovepcps.repository;

import java.util.List;

/**
 * @author Marcos
 * 
 */

public interface Repository<T> {

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public void save(T entity) throws Exception;

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public T update(T entity);

	/**
	 * 
	 * @param entity
	 */
	public void remove(T entity);

	/**
	 * 
	 * @return
	 */
	public List<T> getAll();

	/**
	 * 
	 * @param id
	 * @return
	 */
	public T find(Long id);

	/**
	 * 
	 * @param namedQuery
	 * @return
	 */
	public List<T> findByNamedQuery(String namedQuery);
}