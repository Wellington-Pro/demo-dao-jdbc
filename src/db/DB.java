package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null;

	/*
	 * conectar com o banco de dados no jdbc é instanciar um objeto do tipo
	 * Connection
	 * 
	 */
	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
				// primeiro parametro url do banco e segundo parametro propriedades de conexao
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}

		return conn;
	}

	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();

			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	private static Properties loadProperties() {

		try (FileInputStream fs = new FileInputStream("db.properties")) {

			Properties props = new Properties();
			props.load(fs);
			return props;
			/*
			 * faz a leitura do arquivo properties apontado peleo imputstream fs, e vai
			 * guarda os dados dentro do objeto properties
			 * 
			 */

		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}

	}
	public static void closeStatemnt(Statement st) {
		if(st!=null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			throw new DbException(e.getMessage());
/*
 * método feito para fechar o stament e n ficar precisando tratar 
 * excessao do Statement
 */
		}
	}
}
	public static void closeResultSet(ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			throw new DbException(e.getMessage());
/*
 * método feito para fechar o ResultSet e n ficar precisando tratar 
 * excessao do ResultSet
 */
		}
	}
}

	
}