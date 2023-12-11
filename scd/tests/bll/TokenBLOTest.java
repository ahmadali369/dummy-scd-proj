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
import transferObjects.TokenTO;

public class TokenBLOTest {

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
	
	
	
	

	@Test
	void test2()  {
		int verseid = 0; 
		TokenTO tokenTO = new TokenTO("token", verseid, "pos");
		
		facadeBLL.insertToken(tokenTO);
		Assertions.assertEquals(1, 1);
	}
	@Test
	void test3() {
		int verseid = 0; 
		facadeBLL.getAllTokens(verseid);
		Assertions.assertEquals(1, 1);
	}
	@Test
	void test4() {
		facadeBLL.createPos("word");
		Assertions.assertEquals(1, 1);
	}
	@Test
	void test5() {
		int verseid = 0; 
		facadeBLL.generateTokens("misra1", "misra2", verseid);
		Assertions.assertEquals(1, 1);
	}
	
	@Test
	void test6() {
		int poemid = 0; 
		facadeBLL.tokenProcessing(poemid);
		Assertions.assertEquals(1, 1);
	}
	
	
	
}
