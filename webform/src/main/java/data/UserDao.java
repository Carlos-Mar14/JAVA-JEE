package data;

import java.util.List;

import domain.User;

public interface UserDao {
	boolean create(User user);
	
	List<User> getAll();
}
