package ar.com.api.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Statement;

public class ConnectionUtils {

	private static ConnectionUtils CONNECTION;
	private Connection connection;
	private Properties properties;

	private ConnectionUtils() {

	}

	public static ConnectionUtils getIntancesConnection() {
		if (CONNECTION == null) {
			CONNECTION = new ConnectionUtils();
		}
		return CONNECTION;
	}

	private Properties getProperties() {
		if (properties == null) {
			properties = new Properties();
			properties.setProperty("user", Utils.USERNAME);
			properties.setProperty("password", Utils.PASSWORD);
			properties.setProperty("MaxPooledStatements", Utils.MAX_POOL);
		}
		return properties;
	}

	// connect database
	public void connect() throws ClassNotFoundException, SQLException {
		if (connection == null) {
			Class.forName(Utils.DATABASE_DRIVER);
			connection = DriverManager.getConnection(Utils.DATABASE_URL, getProperties());
		}
	}
	
	public ResultSet ejecutarConsulta(String consulta) throws SQLException{
		return connection.prepareStatement(consulta).executeQuery();
	}
	
	public PreparedStatement consultaPreparadaPorID(String consulta) throws SQLException{
		return connection.prepareStatement(consulta,Statement.RETURN_GENERATED_KEYS);
	}

	public PreparedStatement consultaPreparada(String consulta) throws SQLException{
		return connection.prepareStatement(consulta);
	}
	// disconnect database
	public void disconnect() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
