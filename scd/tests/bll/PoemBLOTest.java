
package bll;

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
import transferObjects.PoemTO;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

class PoemBLOTest {

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
	void testSavePoem() throws SQLException {

		PoemTO poemTO = createSamplePoem();

		facadeBLL.savePoem(poemTO);

		List<Map<String, Object>> allPoems = facadeBLL.getAllPoems(poemTO.getBookId());
		Assertions.assertTrue(containsPoemWithProperties(allPoems, poemTO), "Saved poem should be in the list");
	}

	@Test
	void testImportPoem() throws SQLException {

		int bookid = 0;
		File file = new File("test-file");

		facadeBLL.importPoem(bookid, file);

	}

	@Test
	void testUpdatePoem() {

		PoemTO existingPoem = createSamplePoem();
		try {
			facadeBLL.savePoem(existingPoem);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		PoemTO updatedPoem = createSamplePoem();
		updatedPoem.setTitle("Updated Title");

		facadeBLL.updatePoem(existingPoem.getTitle(), updatedPoem);

		List<Map<String, Object>> allPoems = facadeBLL.getAllPoems(existingPoem.getBookId());
		Assertions.assertTrue(containsPoemWithProperties(allPoems, updatedPoem), "Updated poem should be in the list");
	}

	@Test
	void testDeletePoem() throws SQLException {

		PoemTO poemTO = createSamplePoem();
		facadeBLL.savePoem(poemTO);

		facadeBLL.deletePoem(poemTO.getTitle());

		List<Map<String, Object>> allPoems = facadeBLL.getAllPoems(poemTO.getBookId());
		Assertions.assertFalse(allPoems.contains(poemTO), "Deleted poem should not be in the list");
	}

	@Test
	void testGetPoemsByRoot() throws SQLException {

		String root = "sampleRoot";
		PoemTO poem1 = createSamplePoem();
		PoemTO poem2 = createSamplePoem();
		facadeBLL.savePoem(poem1);
		facadeBLL.savePoem(poem2);

		List<Map<String, Object>> poemsByRoot = facadeBLL.getPoemsByRoot(root);

		Assertions.assertNotNull(poemsByRoot, "Returned list should not be null");
		Assertions.assertTrue(poemsByRoot.isEmpty(), "List should be empty as no poems have the specified root");
	}

	private boolean containsPoemWithProperties(List<Map<String, Object>> allPoems, PoemTO poem) {
		for (Map<String, Object> poemMap : allPoems) {
			if (poemMap.get("title").equals(poem.getTitle()) && poemMap.get("bookId").equals(poem.getBookId())
					&& poemMap.get("total_verses").equals(poem.getTotal_verses())) {
				return true;
			}
		}
		return false;
	}

	private PoemTO createSamplePoem() {
		PoemTO poemTO = new PoemTO();
		poemTO.setTitle("Sample Poem");
		poemTO.setBookId(1);
		poemTO.setTotal_verses(20);
		return poemTO;
	}
}
