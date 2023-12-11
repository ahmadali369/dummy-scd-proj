package ParameterObjects;

import dal.BooksDAOStub;
import dal.PoemDAOStub;
import dal.RootDAOStub;
import dal.VersesDAOStub;
import dal.TokenDAOStub;
import dal.interfaces.IBookDAO;
import dal.interfaces.IPoemDAO;
import dal.interfaces.IRootDAO;
import dal.interfaces.ITokenDAO;
import dal.interfaces.IVerseDAO;

public class DaoStubPO implements IDaoPo{

	IPoemDAO poemDAO;
	IBookDAO bookDAO;
	IRootDAO rootDAO;
	ITokenDAO tokenDAO;
	IVerseDAO verseDAO;

	public DaoStubPO(){
		poemDAO = new PoemDAOStub();
		bookDAO = new BooksDAOStub();
		rootDAO = new RootDAOStub();
		tokenDAO = new TokenDAOStub();
		verseDAO = new VersesDAOStub();
		
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
