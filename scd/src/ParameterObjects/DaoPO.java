package ParameterObjects;

import dal.classes.BooksDAO;
import dal.classes.PoemDAO;
import dal.classes.RootDAO;
import dal.classes.TokensDAO;
import dal.classes.VerseDAO;
import dal.interfaces.IBookDAO;
import dal.interfaces.IPoemDAO;
import dal.interfaces.IRootDAO;
import dal.interfaces.ITokenDAO;
import dal.interfaces.IVerseDAO;

public class DaoPO implements IDaoPo{

	IPoemDAO poemDAO;
	IBookDAO bookDAO;
	IRootDAO rootDAO;
	ITokenDAO tokenDAO;  
	IVerseDAO verseDAO; 
	
	public DaoPO(){
		poemDAO = new PoemDAO();
		bookDAO = new BooksDAO();
		rootDAO = new RootDAO();
		tokenDAO = new TokensDAO();
		verseDAO = new VerseDAO();
		
	}
	
	
	
	public IPoemDAO getPoemDAO() {
		return poemDAO;
	}
	public void setPoemDAO(IPoemDAO poemDAO) {
		this.poemDAO = poemDAO;
	}
	public IBookDAO getBookDAO() {
		return bookDAO;
	}
	public void setBookDAO(IBookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}
	public IRootDAO getRootDAO() {
		return rootDAO;
	}
	public void setRootDAO(IRootDAO rootDAO) {
		this.rootDAO = rootDAO;
	}
	public ITokenDAO getTokenDAO() {
		return tokenDAO;
	}
	public void setTokenDAO(ITokenDAO tokenDAO) {
		this.tokenDAO = tokenDAO;
	}
	public IVerseDAO getVerseDAO() {
		return verseDAO;
	}
	public void setVerseDAO(IVerseDAO verseDAO) {
		this.verseDAO = verseDAO;
	}
	
	
	
	
}
