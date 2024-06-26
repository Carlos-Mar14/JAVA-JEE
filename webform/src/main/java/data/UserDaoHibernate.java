package data;

import java.util.List;

import domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class UserDaoHibernate implements UserDao {

	@Override
	public boolean create(User user) {
		EntityManager em = JpaUtils.getEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();

			em.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<User> getAll() {
		EntityManager em = JpaUtils.getEntityManager();
		Query q = em.createQuery("select e from User e");
		List<User> usuarios = q.getResultList();
		em.close();
		return usuarios;
	}

}
