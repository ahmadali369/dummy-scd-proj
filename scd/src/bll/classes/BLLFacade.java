package bll.classes;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import bll.interfaces.IBLLFacade;
import bll.interfaces.IBooksBLO;
import bll.interfaces.IPeomBLO;
import bll.interfaces.IRootsBLO;
import bll.interfaces.ITokenBLO;
import bll.interfaces.IVerseBLO;
import dal.classes.DBconfig;
import transferObjects.BookTO;
import transferObjects.PoemTO;
import transferObjects.RootTO;
import transferObjects.TokenTO;
import transferObjects.VerseTO;

public class BLLFacade implements IBLLFacade {

	private IBooksBLO booksBLO;
	private IPeomBLO poemBLO;
	private IRootsBLO rootsBLO;
	private ITokenBLO tokenBLO;
	private IVerseBLO verseBLO; 

	
	
	private static BLLFacade obj;

	public static BLLFacade getInstance(IBooksBLO booksBLO, IPeomBLO poemBLO, IRootsBLO rootBLO, ITokenBLO tokenBLO, IVerseBLO verseBLO) {
		if (obj == null)
			obj = new BLLFacade(booksBLO, poemBLO, rootBLO, tokenBLO, verseBLO);
		return obj;
	}
	
	
	private BLLFacade(IBooksBLO booksBLO, IPeomBLO poemBLO, IRootsBLO rootBLO, ITokenBLO tokenBLO, IVerseBLO verseBLO) {
		this.booksBLO = booksBLO;
		this.poemBLO = poemBLO;
		this.rootsBLO = rootBLO;
		this.tokenBLO = tokenBLO; 
		this.verseBLO = verseBLO; 

	}

	@Override
	public void savePoem(PoemTO poem) throws SQLException {
		poemBLO.savePoem(poem);

	}



	@Override
	public void insertBook(BookTO book) {

		booksBLO.insertBook(book);

	}

	@Override
	public List<Map<String, Object>> getAllBooks() {
		return booksBLO.getAllBooks();
	}

	@Override
	public Map<String, Object> getBook(int bookId) {

		return booksBLO.getBook(bookId);
	}

	@Override
	public void updateBook(String existingTitle, String existingAuthorName, BookTO book) {
		booksBLO.updateBook(existingTitle, existingAuthorName, book);

	}

	@Override
	public void deleteBook(String title, String authorName) {
		booksBLO.deleteBook(title, authorName);

	}

	@Override
	public List viewTableRecords() throws SQLException {

		return rootsBLO.viewTableRecords();
	}

	@Override
	public void insertroot(String rootdata) throws SQLException {
		// TODO Auto-generated method stub
		rootsBLO.insertroot(rootdata);

	}

	@Override
	public void updateWord(String rootw, String id) {
		// TODO Auto-generated method stub
		rootsBLO.updateWord(rootw, id);
	}

	@Override
	public void deleteroot(String id) {
		// TODO Auto-generated method stub
		rootsBLO.deleteroot(id);

	}

	@Override
	public List<Map<String, Object>> getAllPoems(int bookid) {
		// TODO Auto-generated method stub
		return poemBLO.getAllPoems(bookid);

	}

//	@Override
//	public void chooseBookAndRead(JFrame frame, int bookid) {
//		// TODO Auto-generated method stub
//		poemBLO.chooseBookAndRead(frame, bookid);
//
//	}

	@Override
	public List<Map<String, Object>> getAllVersesAndGenerateTokensAndRootsList(int poem_id) {
		// TODO Auto-generated method stub
		 
		return tokenBLO.getAllVersesAndGenerateTokensAndRootsList(poem_id);
	}

	@Override
	public void insertToken(TokenTO token)  {
		// TODO Auto-generated method stub
		tokenBLO.insertToken(token);
	}

	@Override
	public void saveVerse(VerseTO verse) throws SQLException {
		// TODO Auto-generated method stub
		verseBLO.saveVerse(verse);
		
	}

	@Override
	public void updatePoem(String existingTitle, PoemTO poem) {
		// TODO Auto-generated method stub
		poemBLO.updatePoem(existingTitle, poem);
		
	}

	@Override
	public void deletePoem(String title) {
		// TODO Auto-generated method stub
		poemBLO.deletePoem(title);
		
	}

	@Override
	public void updateVerse(String existingMisra1, String existingMisra2, VerseTO verse) {
		// TODO Auto-generated method stub
		verseBLO.updateVerse(existingMisra1, existingMisra2, verse);
		
	}

	@Override
	public void deleteVerse(String misra1, String misra2) {
		// TODO Auto-generated method stub
		verseBLO.deleteVerse(misra1, misra2);
		
	}

	@Override
	public List<Map<String, Object>> getAllTokens(int verseId) {
		// TODO Auto-generated method stub
		return tokenBLO.getAllTokens(verseId);
	}

	@Override
	public String createPos(String word) {
		// TODO Auto-generated method stub
		return tokenBLO.createPos(word);
	}

	@Override
	public List<Map<String, Object>> generateTokens(String misra1, String misra2, int verse_id) {
		// TODO Auto-generated method stub
		return tokenBLO.generateTokens(misra1, misra2, verse_id);
	}

	@Override
	public void insertRoot(RootTO root) {
		// TODO Auto-generated method stub
		rootsBLO.insertRoot(root);
		
	}

	@Override
	public List<Map<String, Object>> getAllRoots(int verseId) {
		// TODO Auto-generated method stub
		return rootsBLO.getAllRoots(verseId);
	}

	@Override
	public void updateStatus(int verse_id) {
		// TODO Auto-generated method stub
		rootsBLO.updateStatus(verse_id);
		
	}

	@Override
	public void updateRootStatus(String selectedRoot, String selectedVerse) {
		// TODO Auto-generated method stub
		rootsBLO.updateRootStatus(selectedRoot, selectedVerse);
	}

	@Override
	public int getVerseId(String selectedVerse) {
		// TODO Auto-generated method stub
		return verseBLO.getVerseId(selectedVerse);
	}

	@Override
	public List<Map<String, Object>> getAllVersesByRoots(String rootVal) {
		// TODO Auto-generated method stub
		return verseBLO.getAllVersesByRoots(rootVal);
	}


	@Override
	public String goToPoem(String root) {
		// TODO Auto-generated method stub
		return poemBLO.goToPoem(root);
	}


	@Override
	public String searchAndDisplayVerses(String root) {
		// TODO Auto-generated method stub
		return verseBLO.searchAndDisplayVerses(root);
	}


	@Override
	public void importPoem(int bookid, File file) throws SQLException {
		// TODO Auto-generated method stub
		poemBLO.importPoem(bookid, file);
		
	}


	@Override
	public List<Map<String, Object>> getPoemsByRoot(String root) {
		// TODO Auto-generated method stub
		return poemBLO.getPoemsByRoot(root);
	}

}
