package br.com.ajovepcps.factory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * @author Marcos
 * 
 *         F�brica de EntityManager
 * 
 */

public class EntityManagerFactory {

	private static final String AJOVEPCPS_PERSISTENCE_UNIT = "ajovepcps_PU";

	/**
	 * M�todo est�tico para obter uma instancia de EntityManager
	 */

	public static EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory(
				AJOVEPCPS_PERSISTENCE_UNIT).createEntityManager();
	}

}
