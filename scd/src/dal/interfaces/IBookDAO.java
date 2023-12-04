package dal.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import transferObjects.BookTO;

public interface IBookDAO {
	public void insertBook(BookTO book) throws SQLException;

	public List<Map<String, Object>> getAllBooks() throws SQLException;

	public Map<String, Object> getBook(int bookId) throws SQLException;

	public void updateBook(String existingTitle, String existingAuthorName, BookTO book) throws SQLException;

	public void deleteBook(String title, String authorName) throws SQLException;
	


}
