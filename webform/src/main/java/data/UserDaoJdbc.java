package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Statement;

import domain.User;

public class UserDaoJdbc implements UserDao {

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager
					.getConnection("jdbc:mysql://localhost:8888/Formacion?user=root&password=root");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	private static final String URL = "jdbc:mysql://localhost:8888/jakartaee_hibernate?user=root&password=root";

	@Override
	public boolean create(User user) {
		try {
			Connection conn = DriverManager.getConnection(URL);
			String creacion = "insert into user (id, empresa, login, pwd) values (?,?,?)";
			PreparedStatement pst = conn.prepareStatement(creacion);
			pst.setString(1, user.getEmpresa());
			pst.setString(2, user.getLogin());
			pst.setString(3, user.getPwd());
			pst.execute();
			pst.close();
			conn.close();

		} catch (Exception e) {

		}
		return false;
	}

	@Override
	public List<User> getAll() {
		try {
			Connection conn = DriverManager.getConnection(URL);
			Statement statement = conn.createStatement();
			ResultSet res = statement.executeQuery("select empresa, login, pwd, from User");
			List<User> usuario = new ArrayList<>();
			while (res.next()) {
				User type = (type) res.nextElement();
				
			}
;		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}