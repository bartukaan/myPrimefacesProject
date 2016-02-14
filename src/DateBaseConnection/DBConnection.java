package DateBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection getConnection()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		Connection conn = null;
		String url = "jdbc:sqlserver://localhost;database=MEALORDERS";
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String userName = "testLogin";
		String password = "welcome1";
		Class.forName(driver).newInstance();
		conn = DriverManager.getConnection(url, userName, password);
		System.out.println("Connected to the database");
		return conn;
	}
}
