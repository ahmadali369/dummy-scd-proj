package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dal.interfaces.IBookDAO;
import transferObjects.BookTO;

public class BooksDAOStub implements IBookDAO {

	private static final List<BookTO> booksDatabase = new ArrayList<>();

	@Override
	public void insertBook(BookTO book) throws SQLException {

		System.out.println(book.getAuthorDateOfBirth());
		booksDatabase.add(book);
	}

	@Override
	public List<Map<String, Object>> getAllBooks() throws SQLException {

		List<Map<String, Object>> result = new ArrayList<>();
		for (BookTO book : booksDatabase) {
			Map<String, Object> bookMap = new HashMap<>();
			bookMap.put("title", book.getTitle());
			bookMap.put("authorName", book.getAuthorName());
			bookMap.put("authorDateOfBirth", book.getAuthorDateOfBirth());
			bookMap.put("authorDateOfDeath", book.getAuthorDateOfDeath());
			bookMap.put("totalPoems", book.getTotalPoems());
			result.add(bookMap);
		}
		return result;

	}

	@Override
	public void updateBook(String existingTitle, String existingAuthorName, BookTO book) throws SQLException {

		for (int i = 0; i < booksDatabase.size(); i++) {
			BookTO currentBook = booksDatabase.get(i);
			if (currentBook.getTitle().equals(existingTitle)
					&& currentBook.getAuthorName().equals(existingAuthorName)) {

				booksDatabase.set(i, book);
				break;
			}
		}
	}

	@Override
	public void deleteBook(String title, String authorName) throws SQLException {

		booksDatabase.removeIf(book -> book.getTitle().equals(title) && book.getAuthorName().equals(authorName));
	}
}
