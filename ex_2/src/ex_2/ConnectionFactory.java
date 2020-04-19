package ex_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
		
		static {
			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
				}
		}
		
		public static Connection obtemConexao() throws SQLException {
			return DriverManager.getConnection("jdbc:mysql://localhost/Aula1?user=root&password=pedro&useTimezone=true&serverTimezone=UTC");
			}

}
