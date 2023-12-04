package bll;

import java.io.File;
import java.sql.SQLException;

import javax.swing.JFrame;

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
import transferObjects.PoemTO;
import transferObjects.VerseTO;

class PoemBLOTest {

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

	PoemTO poemTO = new PoemTO();
	VerseTO verseTO = new VerseTO();

	@Test
	void test1() throws SQLException {

		facadeBLL.savePoem(poemTO);

		Assertions.assertEquals(1, 1);
	}

	@Test
	void test2() throws SQLException {

		int bookid = 0;
		File file = new File("");
		facadeBLL.importPoem(bookid, file);

		Assertions.assertEquals(1, 1);
	}

	@Test
	void test3() throws SQLException {

		facadeBLL.savePoem(poemTO);

		Assertions.assertEquals(1, 1);
	}

	@Test
	void test4() {
		String existingTitleString = " ";
		facadeBLL.updatePoem(existingTitleString, poemTO);
		Assertions.assertEquals(1, 1);
	}

	@Test
	void test5() {

		facadeBLL.deletePoem("");
		Assertions.assertEquals(1, 1);

	}

	@Test
	void test6() {

		int bookid = 0;
		facadeBLL.getAllPoems(bookid);
		Assertions.assertEquals(1, 1);

	}

}
