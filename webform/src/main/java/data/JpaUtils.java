package data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtils {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("webform");
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
