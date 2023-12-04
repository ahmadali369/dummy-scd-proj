package dal.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dal.interfaces.IBookDAO;
import transferObjects.BookTO;

public class BooksDAO implements IBookDAO {

	DBconfig dbconnection = DBconfig.getInstance();

	private static final Logger logger = LogManager.getLogger(BooksDAO.class);
	
	@Override
	public void insertBook(BookTO book) throws SQLException {
		Connection connection = null;
		try {
			connection = dbconnection.getConnection();
			connection.setAutoCommit(false);

			String insertBookSQL = "INSERT INTO books (title, author_name, author_date_of_birth, author_date_of_death, total_poems) VALUES (?, ?, ?, ?, ?)";
			try (PreparedStatement preparedStatement = connection.prepareStatement(insertBookSQL,
					Statement.RETURN_GENERATED_KEYS)) {
				preparedStatement.setString(1, book.getTitle());
				preparedStatement.setString(2, book.getAuthorName());
				preparedStatement.setString(3, book.getAuthorDateOfBirth());
				preparedStatement.setString(4, book.getAuthorDateOfDeath());
				preparedStatement.setInt(5, book.getTotalPoems());
				preparedStatement.executeUpdate();

				connection.commit();
			}
		} catch (SQLException e) {

			logger.debug("InsetBook Func thrown an Exception");

			if (connection != null) {
				connection.rollback();
			}

			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	@Override
	public List<Map<String, Object>> getAllBooks() throws SQLException {
		List<Map<String, Object>> books = new ArrayList<>();

		try (Connection connection = dbconnection.getConnection()) {
			String selectAllBooksSQL = "SELECT * FROM books";

			try (PreparedStatement preparedStatement = connection.prepareStatement(selectAllBooksSQL)) {
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						Map<String, Object> book = new HashMap<>();
						int bookId = resultSet.getInt("book_id");
						book.put("bookId", bookId);
						book.put("title", resultSet.getString("title"));
						book.put("authorName", resultSet.getString("author_name"));
						book.put("authorDateOfBirth", resultSet.getString("author_date_of_birth"));
						book.put("authorDateOfDeath", resultSet.getString("author_date_of_death"));
						book.put("totalPoems", resultSet.getInt("total_poems"));

						books.add(book);
					}
				}
			}
		}

		// ---------------------------------------------------------------------------------------------------

		if (books.isEmpty()) {
			logger.debug("No books found in the database.");
		}

		return books;
	}



	@Override
	public Map<String, Object> getBook(int bookId) throws SQLException {
		try (Connection connection = dbconnection.getConnection()) {
			String selectBookSQL = "SELECT * FROM books WHERE book_id = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(selectBookSQL)) {
				preparedStatement.setInt(1, bookId);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						Map<String, Object> book = new HashMap<>();
						book.put("bookId", resultSet.getInt("book_id"));
						book.put("title", resultSet.getString("title"));
						book.put("authorName", resultSet.getString("author_name"));
						book.put("authorDateOfBirth", resultSet.getDate("author_date_of_birth"));
						book.put("authorDateOfDeath", resultSet.getDate("author_date_of_death"));
						book.put("totalPoems", resultSet.getInt("total_poems"));

						return book;
					}
				}
			}
		}
		logger.debug("No books found in the database.");
		return null; // Book not found
	}

	@Override
	public void updateBook(String existingTitle, String existingAuthorName, BookTO book) throws SQLException {
		try (Connection connection = dbconnection.getConnection()) {
			String updateBookSQL = "UPDATE books SET title = ?, author_name = ?, author_date_of_birth = ?, author_date_of_death = ?, total_poems = ? WHERE title = ? AND author_name = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(updateBookSQL)) {
				preparedStatement.setString(1, book.getTitle());
				preparedStatement.setString(2, book.getAuthorName());
				preparedStatement.setString(3, book.getAuthorDateOfBirth());
				preparedStatement.setString(4, book.getAuthorDateOfDeath());
				preparedStatement.setInt(5, book.getTotalPoems());
				preparedStatement.setString(6, existingTitle);
				preparedStatement.setString(7, existingAuthorName);
				preparedStatement.executeUpdate();

			}
		}
	}

	@Override
	public void deleteBook(String title, String authorName) throws SQLException {
		try (Connection connection = dbconnection.getConnection()) {
			String deleteBookSQL = "DELETE FROM books WHERE title = ? AND author_name = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(deleteBookSQL)) {
				preparedStatement.setString(1, title);
				preparedStatement.setString(2, authorName);
				preparedStatement.executeUpdate();
			}
		}
	}

}
