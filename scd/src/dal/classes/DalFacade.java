package dal.classes;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import ParameterObjects.DaoPO;
import ParameterObjects.IDaoPo;
import bll.interfaces.IPeomBLO;
import dal.interfaces.IBookDAO;
import dal.interfaces.IDalFacade;
import dal.interfaces.IPoemDAO;
import dal.interfaces.IRootDAO;
import dal.interfaces.ITokenDAO;
import dal.interfaces.IVerseDAO;
import transferObjects.BookTO;
import transferObjects.PoemTO;
import transferObjects.RootTO;
import transferObjects.TokenTO;
import transferObjects.VerseTO;

public class DalFacade implements IDalFacade {

//	private IBookDAO bookDAO;
//	private IPoemDAO poemDAO;
//	private IRootDAO rootDAO;
//	private ITokenDAO tokenDAO;
//	private IVerseDAO verseDAO;
	IDaoPo daoPO; 

	private static DalFacade obj;

	public static DalFacade getInstance(IDaoPo dp) {
		if (obj == null)
			obj = new DalFacade(dp);
		return obj;
	}

//	private DalFacade(IPoemDAO poemDAO, IBookDAO bookDAO, IRootDAO rootDAO, ITokenDAO tokenDAO, IVerseDAO verseDAO) {
//		super();
//		this.tokenDAO = tokenDAO;
//		this.bookDAO = bookDAO;
//		this.poemDAO = poemDAO;
//		this.rootDAO = rootDAO;
//		this.verseDAO = verseDAO;
//
//	}
	
	
	private DalFacade(IDaoPo dp) {
		super();
		this.daoPO = dp; 

	}

	@Override
	public void insertBook(BookTO book) throws SQLException {
		daoPO.getBookDAO().insertBook(book);

	}

	@Override
	public List<Map<String, Object>> getAllBooks() throws SQLException {
		return daoPO.getBookDAO().getAllBooks();
	}
//
//	@Override
//	public Map<String, Object> getBook(int bookId) throws SQLException {
//		return bookDAO.getBook(bookId);
//	}

	@Override
	public void updateBook(String existingTitle, String existingAuthorName, BookTO book) throws SQLException {
		daoPO.getBookDAO().updateBook(existingTitle, existingAuthorName, book);

	}

	@Override
	public void deleteBook(String title, String authorName) throws SQLException {
		daoPO.getBookDAO().deleteBook(title, authorName);

	}

	@Override
	public void savePoem(PoemTO poem) throws SQLException {
		daoPO.getPoemDAO().savePoem(poem);

	}

	@Override
	public List getAllRoots() throws SQLException {

		return daoPO.getRootDAO().getAllRoots();

	}

//	@Override
//	public void insertrootword(String root) throws SQLException {
//
//		rootDAO.insertrootword(root);
//
//	}

	@Override
	public void updateroot(String rootw, String id) {

		daoPO.getRootDAO().updateroot(rootw, id);

	}

	@Override
	public void deleteroot(String id) {

		daoPO.getRootDAO().deleteroot(id);

	}

	@Override
	public List<Map<String, Object>> getAllPoems(int bookid) throws SQLException {
		// TODO Auto-generated method stub
		return daoPO.getPoemDAO().getAllPoems(bookid);
	}

	@Override
	public void importPoem(int bookid, File file) throws SQLException {
		daoPO.getPoemDAO().importPoem(bookid, file);
	}

//	@Override
//	public void choosePoemFile(JFrame frame, int bookid) {
//		poemDAO.choosePoemFile(frame, bookid);
//
//	}

	@Override
	public List<Map<String, Object>> getAllVerses(int poem_id) throws SQLException {
		// TODO Auto-generated method stub

		return daoPO.getVerseDAO().getAllVerses(poem_id);
	}

	@Override
	public void insertToken(TokenTO token) throws SQLException {
		// TODO Auto-generated method stub
		daoPO.getTokenDAO().insertToken(token);
	}

	@Override
	public void saveVerse(VerseTO verse) throws SQLException {
		// TODO Auto-generated method stub
		daoPO.getVerseDAO().saveVerse(verse);

	}

	@Override
	public void updatePoem(String existingTitle, PoemTO poem) throws SQLException {
		// TODO Auto-generated method stub
		daoPO.getPoemDAO().updatePoem(existingTitle, poem);

	}

	@Override
	public void deletePoem(String title) throws SQLException {
		// TODO Auto-generated method stub
		daoPO.getPoemDAO().deletePoem(title);

	}

	@Override
	public void updateVerse(String existingMisra1, String existingMisra2, VerseTO verse) throws SQLException {
		// TODO Auto-generated method stub
		daoPO.getVerseDAO().updateVerse(existingMisra1, existingMisra2, verse);
	}

	@Override
	public void deleteVerse(String misra1, String misra2) throws SQLException {
		// TODO Auto-generated method stub
		daoPO.getVerseDAO().deleteVerse(misra1, misra2);

	}

	@Override
	public List<Map<String, Object>> getAllTokens(int verseId) throws SQLException {
		// TODO Auto-generated method stub
		return daoPO.getTokenDAO().getAllTokens(verseId);
	}

	@Override
	public void insertRoot(RootTO root) throws SQLException {
		// TODO Auto-generated method stub
		daoPO.getRootDAO().insertRoot(root);

	}

	@Override
	public List<Map<String, Object>> getAllRootsByVerseId(int verseId) throws SQLException {
		// TODO Auto-generated method stub
		return daoPO.getRootDAO().getAllRootsByVerseId(verseId);
	}

	@Override
	public void updateStatus(int verse_id) throws SQLException {
		// TODO Auto-generated method stub
		daoPO.getRootDAO().updateStatus(verse_id);
	}

	@Override
	public void updateRootStatus(String selectedRoot, String selectedVerse) {
		// TODO Auto-generated method stub

		daoPO.getRootDAO().updateRootStatus(selectedRoot, selectedVerse);
	}

	@Override
	public int getVerseId(String selectedVerse) {
		// TODO Auto-generated method stub
		return daoPO.getRootDAO().getVerseId(selectedVerse);
	}

//	@Override
//	public List<Map<String, Object>> getAllVersesByRoots(String rootVal) throws SQLException {
//		// TODO Auto-generated method stub
//		return verseDAO.getAllVersesByRoots(rootVal);
//	}
//
//	@Override
//	public String goToPoem(String root) {
//		// TODO Auto-generated method stub
//		return poemDAO.goToPoem(root);
//	}
//
//	@Override
//	public String searchAndDisplayVerses(String root) {
//		// TODO Auto-generated method stub
//		return verseDAO.searchAndDisplayVerses(root);
//	}

	@Override
	public List<Map<String, Object>> getPoemsByRoot(String root) throws SQLException {
		// TODO Auto-generated method stub
		return daoPO.getPoemDAO().getPoemsByRoot(root);
	}

}
