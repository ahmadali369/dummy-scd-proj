package bll;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bll.classes.BLLFacade;
import bll.classes.BooksBLO;
import bll.classes.PoemBLO;
import bll.classes.RootsBLO;
import bll.classes.TokenBLO;
import bll.classes.VerseBLO;
import bll.interfaces.IBLLFacade;
import bll.interfaces.IBooksBLO;
import bll.interfaces.IPeomBLO;
import bll.interfaces.IRootsBLO;
import bll.interfaces.ITokenBLO;
import bll.interfaces.IVerseBLO;
import dal.BooksDAOStub;
import dal.PoemDAOStub;
import dal.RootDAOStub;
import dal.TokenDAOStub;
import dal.VersesDAOStub;
import dal.classes.DalFacade;
import dal.interfaces.IBookDAO;
import dal.interfaces.IDalFacade;
import dal.interfaces.IPoemDAO;
import dal.interfaces.IRootDAO;
import dal.interfaces.ITokenDAO;
import dal.interfaces.IVerseDAO;
import transferObjects.BookTO;

class BooksBLOTest {

	private IBLLFacade facadeBLL;

	@BeforeEach
	void setUp() {
		IPoemDAO poemDAO = new PoemDAOStub();
		IBookDAO bookDAO = new BooksDAOStub();
		IRootDAO rootDAO = new RootDAOStub();
		ITokenDAO tokenDAO = new TokenDAOStub();
		IVerseDAO verseDAO = new VersesDAOStub();

		IDalFacade facadeDAL = DalFacade.getInstance(poemDAO, bookDAO, rootDAO, tokenDAO, verseDAO);

		IBooksBLO booksBLO = new BooksBLO(facadeDAL);
		IPeomBLO peomBLO = new PoemBLO(facadeDAL);
		IRootsBLO rootsBLO = new RootsBLO(facadeDAL);
		ITokenBLO tokenBLO = new TokenBLO(facadeDAL);
		IVerseBLO verseBLO = new VerseBLO(facadeDAL);

		IBLLFacade facadeBLL = BLLFacade.getInstance(booksBLO, peomBLO, rootsBLO, tokenBLO, verseBLO);

		this.facadeBLL = facadeBLL;
	}

	@Test
	void testInsertBook() {
	    // Arrange
	    BookTO bookTO = createSampleBook();

	    // Act
	    facadeBLL.insertBook(bookTO);

	    // Assert
	    List<Map<String, Object>> allBooks = facadeBLL.getAllBooks();
	    Assertions.assertTrue(containsBookWithProperties(allBooks, bookTO), "Inserted book should be in the list");
	}

	@Test
	void testGetAllBooks() {
		// Act
		List<Map<String, Object>> allBooks = facadeBLL.getAllBooks();

		// Assert
		Assertions.assertNotNull(allBooks, "Returned list should not be null");
		Assertions.assertEquals(0, allBooks.size(), "List should be empty initially");
	}
	

	@Test
	void testUpdateBook() {
	    // Arrange
	    BookTO existingBook = createSampleBook();
	    facadeBLL.insertBook(existingBook);

	    BookTO updatedBook = createSampleBook(); // You can modify properties here
	    updatedBook.setTitle("Updated Title");

	    // Act
	    facadeBLL.updateBook(existingBook.getTitle(), existingBook.getAuthorName(), updatedBook);

	    // Assert
	    List<Map<String, Object>> allBooks = facadeBLL.getAllBooks();
	    Assertions.assertTrue(containsBookWithProperties(allBooks, updatedBook), "Updated book should be in the list");
	}
	
	@Test
	void testDeleteBook() {
		// Arrange
		BookTO bookTO = createSampleBook();
		facadeBLL.insertBook(bookTO);

		// Act
		facadeBLL.deleteBook(bookTO.getTitle(), bookTO.getAuthorName());

		// Assert
		List<Map<String, Object>> allBooks = facadeBLL.getAllBooks();
		Assertions.assertFalse(allBooks.contains(bookTO), "Deleted book should not be in the list");
	}

	
	
	// Add this utility method to check if a list of maps contains a book with specific properties
	private boolean containsBookWithProperties(List<Map<String, Object>> allBooks, BookTO book) {
	    for (Map<String, Object> bookMap : allBooks) {
	        if (bookMap.get("title").equals(book.getTitle()) &&
	            bookMap.get("authorName").equals(book.getAuthorName()) &&
	            bookMap.get("authorDateOfBirth").equals(book.getAuthorDateOfBirth()) &&
	            bookMap.get("authorDateOfDeath").equals(book.getAuthorDateOfDeath()) &&
	            bookMap.get("totalPoems").equals(book.getTotalPoems())) {
	            return true;
	        }
	    }
	    return false;
	}

	private BookTO createSampleBook() {
		BookTO bookTO = new BookTO();
		bookTO.setTitle("Sample Title");
		bookTO.setAuthorName("Sample Author");
		bookTO.setAuthorDateOfBirth("1990-01-01");
		bookTO.setAuthorDateOfDeath("2020-12-31");
		bookTO.setTotalPoems(10);
		return bookTO;
	}
}
