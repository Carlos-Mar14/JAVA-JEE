package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConecctionJdbc {
    private Connection connection;

    public ConecctionJdbc(String fabricante, String servidor, int puerto, String db, String usuario, String pwd, String opciones) throws SQLException {
        final String cadenaConexion = "jdbc:" + fabricante + "://" + servidor + ":" + puerto + "/" + db + "?" + opciones;
        this.connection = DriverManager.getConnection(cadenaConexion, usuario, pwd);
    }

    public Connection getConnection() {
        return connection;
    }
}
