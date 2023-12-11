
package bll;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ParameterObjects.DaoStubPO;
import ParameterObjects.IDaoPo;
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

	IDaoPo dPo = new DaoStubPO(); 

	IDalFacade facadeDAL = DalFacade.getInstance(dPo);
	
	IBooksBLO booksBLO = new BooksBLO(facadeDAL);
	IPeomBLO peomBLO = new PoemBLO(facadeDAL);
	IRootsBLO rootsBLO = new RootsBLO(facadeDAL);
	ITokenBLO tokenBLO = new TokenBLO(facadeDAL);
	IVerseBLO verseBLO = new VerseBLO(facadeDAL);

	IBLLFacade facadeBLL = BLLFacade.getInstance(booksBLO, peomBLO, rootsBLO, tokenBLO, verseBLO);

	@Test
	void testSaveVerse() throws SQLException {
		VerseTO verseTO = createSampleVerse();

		int initialSize = facadeBLL.getAllVerses(verseTO.getPoem_id()).size();

		facadeBLL.saveVerse(verseTO);

		List<Map<String, Object>> allVerses = facadeBLL.getAllVerses(verseTO.getPoem_id());
		Assertions.assertEquals(initialSize + 1, allVerses.size(), "Saved verse should be in the list");
	}

	@Test
	void testUpdateVerse() throws SQLException {

		VerseTO verseTO = createSampleVerse();
		facadeBLL.saveVerse(verseTO);
		int poemId = verseTO.getPoem_id();
		String existingMisra1 = verseTO.getMisra1();
		String existingMisra2 = verseTO.getMisra2();

		VerseTO updatedVerse = new VerseTO();
		updatedVerse.setMisra1("Updated Misra1");
		updatedVerse.setMisra2("Updated Misra2");
		updatedVerse.setPoem_id(poemId);

		facadeBLL.updateVerse(existingMisra1, existingMisra2, updatedVerse);

		List<Map<String, Object>> allVerses = facadeBLL.getAllVerses(poemId);
		Assertions.assertTrue(containsVerseWithProperties(allVerses, updatedVerse),
				"Updated verse should be in the list");
	}

	@Test
	void testGetAllVerses() {

		int poemId = 1;

		List<Map<String, Object>> allVerses = facadeBLL.getAllVerses(poemId);

		Assertions.assertNotNull(allVerses, "Returned list should not be null");

	}

	@Test
	void testDeleteVerse() throws SQLException {

		VerseTO verseTO = createSampleVerse();
		facadeBLL.saveVerse(verseTO);
		int poemId = verseTO.getPoem_id();

		facadeBLL.deleteVerse(verseTO.getMisra1(), verseTO.getMisra2());

		List<Map<String, Object>> allVerses = facadeBLL.getAllVerses(poemId);
		Assertions.assertFalse(containsVerseWithProperties(allVerses, verseTO),
				"Deleted verse should not be in the list");

	}

	private boolean containsVerseWithProperties(List<Map<String, Object>> allVerses, VerseTO verse) {
		for (Map<String, Object> verseMap : allVerses) {
			if (verseMap.get("misra1").equals(verse.getMisra1()) && verseMap.get("misra2").equals(verse.getMisra2())
					&& verseMap.get("poem_id").equals(verse.getPoem_id())) {
				return true;
			}
		}
		return false;
	}

	private VerseTO createSampleVerse() {
		VerseTO verseTO = new VerseTO();
		verseTO.setMisra1("Sample Misra1");
		verseTO.setMisra2("Sample Misra2");
		verseTO.setPoem_id(1);
		return verseTO;
	}

}
