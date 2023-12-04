package bll.interfaces;

import java.util.List;
import java.util.Map;

import transferObjects.BookTO;

public interface IBooksBLO {
	public void insertBook(BookTO bookTO);

	public List<Map<String, Object>> getAllBooks();

	public Map<String, Object> getBook(int bookId);

	public void updateBook(String existingTitle, String existingAuthorName, BookTO book);

	public void deleteBook(String title, String authorName);



}
