package dal.classes;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBconfig {
	Properties properties;
	String DB_URL;
	String DB_USER;
	String DB_NAME;
	String DB_PASSWORD = "";
//    String DB_PASSWORD = properties.getProperty("db.password");
	
	private static final Logger logger = LogManager.getLogger(DBconfig.class);

	private DBconfig() {
		
		properties = new Properties();
		try (FileInputStream input = new FileInputStream("config.properties")) {
			properties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}

		DB_URL = properties.getProperty("db.server.address");
		DB_USER = properties.getProperty("db.username");
		DB_NAME = properties.getProperty("db.dbname");

		createDatabaseAndTableIfNotExists();
	}

	private static DBconfig obj;

	public static DBconfig getInstance() {
		if (obj == null)
			obj = new DBconfig();
		return obj;
	}

	public Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		return connection;
	}

	public boolean doesDatabaseExist() {
		try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {

			ResultSet resultSet = statement.executeQuery("SHOW DATABASES LIKE '" + DB_NAME + "'");
			return resultSet.next();
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.debug("DB Auto Created.");
			System.out.println("DB Auto Created.");
			return false;
		}
	}

	public void createDatabaseAndTableIfNotExists() {
		if (!doesDatabaseExist()) {

			try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", DB_USER,
					DB_PASSWORD); Statement statement = connection.createStatement()) {

				String createDatabaseSQL = "CREATE DATABASE " + DB_NAME;
				statement.executeUpdate(createDatabaseSQL);
			} catch (SQLException e) {
				logger.debug("createDatabaseAndTableIfNotExists func triggerd an exception");
				e.printStackTrace();
			}
		}

		try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {

			String useDatabaseSQL = "USE " + DB_NAME;
			statement.executeUpdate(useDatabaseSQL);



				String createBooksTableSQL = "CREATE TABLE IF NOT EXISTS books (" +
				    "book_id INT AUTO_INCREMENT PRIMARY KEY," +
				    "title VARCHAR(255) NOT NULL," +
				    "author_name VARCHAR(255) NOT NULL," +
				    "author_date_of_birth VARCHAR(30) NOT NULL," +
				    "author_date_of_death VARCHAR(30) NOT NULL," +
				    "total_poems INT NOT NULL" +
				    ")";
				statement.executeUpdate(createBooksTableSQL);

				String createPoemsTableSQL = "CREATE TABLE IF NOT EXISTS poems (" +
					    "id INT AUTO_INCREMENT PRIMARY KEY," +
					    "title VARCHAR(255) UNIQUE," + 
					    
					    "total_verses INT NOT NULL," +
					    "book_id INT," +
					    "FOREIGN KEY (book_id) REFERENCES books (book_id) ON DELETE CASCADE ON UPDATE CASCADE" +
					    ")";

				statement.executeUpdate(createPoemsTableSQL);

				String createVersesTableSQL = "CREATE TABLE IF NOT EXISTS verses (" +
				    "verse_id INT AUTO_INCREMENT PRIMARY KEY," +
				    "misra1 TEXT," +
				    "misra2 TEXT," +
				    "verse_number INT," +
				    "poem_id INT," +
				    "FOREIGN KEY (poem_id) REFERENCES poems (id) ON DELETE CASCADE ON UPDATE CASCADE" +
				    ")";
				statement.executeUpdate(createVersesTableSQL);
				
							
				String createTokenTableSQL = "CREATE TABLE IF NOT EXISTS tokens (" +
					    "token_id INT AUTO_INCREMENT PRIMARY KEY," +
					    "token TEXT," +
					    "verse_id INT," +
					    "pos TEXT," +
					    "FOREIGN KEY (verse_id) REFERENCES verses (verse_id) ON DELETE CASCADE ON UPDATE CASCADE" +

					    ")";
					statement.executeUpdate(createTokenTableSQL);
					
					
					
					String createRootTableSQL = "CREATE TABLE IF NOT EXISTS root (" +
						    "id INT AUTO_INCREMENT PRIMARY KEY," +                          
						    "verse_id INT," +                          
						    "root VARCHAR(500) UNIQUE," +                     
						    "status VARCHAR(500)" +                    
						    ")";

						statement.executeUpdate(createRootTableSQL);
					
					
					
					String createVerseTokenJunctionTableSQL = "CREATE TABLE IF NOT EXISTS verse_token_junction (" +
					        "verse_id INT," +
					        "token_id INT," +
					        "PRIMARY KEY (verse_id, token_id)," +
					        "FOREIGN KEY (verse_id) REFERENCES verses (verse_id) ON DELETE CASCADE ON UPDATE CASCADE," +
					        "FOREIGN KEY (token_id) REFERENCES tokens (token_id) ON DELETE CASCADE ON UPDATE CASCADE" +
					        ")";
					statement.executeUpdate(createVerseTokenJunctionTableSQL);
		
					
					
					String createVerseRootJunctionTableSQL = "CREATE TABLE IF NOT EXISTS verse_root_junction (" +
					        "verse_id INT," +
					        "root_id INT," +
					        "PRIMARY KEY (verse_id, root_id)," +
					        "FOREIGN KEY (verse_id) REFERENCES verses (verse_id) ON DELETE CASCADE ON UPDATE CASCADE," +
					        "FOREIGN KEY (root_id) REFERENCES root (id) ON DELETE CASCADE ON UPDATE CASCADE" +
					        ")";
					statement.executeUpdate(createVerseRootJunctionTableSQL);

			

		} catch (SQLException e) {
			logger.debug("createDatabaseAndTableIfNotExists func triggerd an exception");
			e.printStackTrace();
		}

	}

	public static void close(Connection connection, PreparedStatement preparedStatement) {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			 logger.debug("close func triggerd an exception");

			e.printStackTrace();
		}
	}

}






