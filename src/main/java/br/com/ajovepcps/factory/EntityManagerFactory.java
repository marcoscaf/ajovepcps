package br.com.ajovepcps.factory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * @author Marcos
 * 
 *         Fábrica de EntityManager
 * 
 */

public class EntityManagerFactory {

	private static final String AJOVEPCPS_PERSISTENCE_UNIT = "ajovepcps_PU";

	/**
	 * Método estático para obter uma instancia de EntityManager
	 */

	public static EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory(
				AJOVEPCPS_PERSISTENCE_UNIT).createEntityManager();
	}

}
