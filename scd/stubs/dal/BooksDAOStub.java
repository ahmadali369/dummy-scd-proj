package dal;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import dal.interfaces.IBookDAO;
import transferObjects.BookTO;

public class BooksDAOStub implements IBookDAO {

	@Override
	public void insertBook(BookTO book) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Map<String, Object>> getAllBooks() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getBook(int bookId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBook(String existingTitle, String existingAuthorName, BookTO book) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBook(String title, String authorName) throws SQLException {
		// TODO Auto-generated method stub
		
	}



}
