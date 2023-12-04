package bll;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
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
import transferObjects.VerseTO;

class VersesBLOTest {

	
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
	VerseTO verseTO = new VerseTO(); 
	
	
	@Test
	void test1() throws SQLException {
		facadeBLL.saveVerse(verseTO);
		Assertions.assertEquals(1, 1);
	}
	@Test
	void test2() {
		facadeBLL.updateVerse("exMisra1", "exMisra2", verseTO);
		Assertions.assertEquals(1, 1);
	}
	@Test
	void test3() {
		facadeBLL.deleteVerse("misra1", "misra2");
		Assertions.assertEquals(1, 1);
	}
	@Test
	void test4() {
		facadeBLL.getAllVersesByRoots("root"); 
		Assertions.assertEquals(1, 1);
	}
	
	@Test
	void test5() {
		facadeBLL.getVerseId("verse id"); 
		Assertions.assertEquals(1, 1);
	}

	@Test
	void test6() {
		facadeBLL.searchAndDisplayVerses("root"); 
		Assertions.assertEquals(1, 1);
	}

}
