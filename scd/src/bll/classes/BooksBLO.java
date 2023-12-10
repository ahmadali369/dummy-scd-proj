package bll.classes;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bll.interfaces.IBooksBLO;
import dal.interfaces.IDalFacade;
import transferObjects.BookTO;

public class BooksBLO implements IBooksBLO {

	private static final Logger logger = LogManager.getLogger(BooksBLO.class);

	private IDalFacade dal;

	public BooksBLO(IDalFacade obj) {
		this.dal = obj;

	}

	@Override
	public void insertBook(BookTO book) {
		try {
			dal.insertBook(book);
		} catch (SQLException e) {

			logger.debug("insertBook func triggerd and exception");

			e.printStackTrace();
		}

	}

	@Override
	public List<Map<String, Object>> getAllBooks() {
		try {
			return dal.getAllBooks();
		} catch (SQLException e) {

			logger.debug("getallbooks func triggerd and exception");

			e.printStackTrace();
		}

		return null;
	}

//	@Override
//	public Map<String, Object> getBook(int bookId) {
//		try {
//			return dal.getBook(bookId);
//		} catch (SQLException e) {
//			logger.debug("getbook func triggerd and exception");
//			e.printStackTrace();
//		}
//
//		return null;
//	}

	@Override
	public void updateBook(String existingTitle, String existingAuthorName, BookTO book) {
		try {
			dal.updateBook(existingTitle, existingAuthorName, book);
		} catch (SQLException e) {

			logger.debug("updatebook func triggerd and exception");
			e.printStackTrace();
		}

	}

	@Override
	public void deleteBook(String title, String authorName) {
		try {
			dal.deleteBook(title, authorName);
		} catch (SQLException e) {
			logger.debug("deletebook func triggerd and exception");

			e.printStackTrace();
		}

	}



}