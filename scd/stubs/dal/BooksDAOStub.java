package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dal.interfaces.IBookDAO;
import transferObjects.BookTO;

public class BooksDAOStub implements IBookDAO {

    // Simulate an in-memory database
    private static final List<BookTO> booksDatabase = new ArrayList<>();

    @Override
    public void insertBook(BookTO book) throws SQLException {
        // Simulate inserting a book into the database
    	System.out.println(book.getAuthorDateOfBirth());
        booksDatabase.add(book);
    }

    @Override
    public List<Map<String, Object>> getAllBooks() throws SQLException {
        // Simulate retrieving all books from the database
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
        // Simulate updating a book in the database
        for (int i = 0; i < booksDatabase.size(); i++) {
            BookTO currentBook = booksDatabase.get(i);
            if (currentBook.getTitle().equals(existingTitle) && currentBook.getAuthorName().equals(existingAuthorName)) {
                // Found the book to update
                booksDatabase.set(i, book);
                break;
            }
        }
    }

    @Override
    public void deleteBook(String title, String authorName) throws SQLException {
        // Simulate deleting a book from the database
        booksDatabase.removeIf(book -> book.getTitle().equals(title) && book.getAuthorName().equals(authorName));
    }
}

