//package bll;
//
//import java.sql.SQLException;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import bll.classes.BLLFacade;
//import bll.classes.BooksBLO;
//import bll.classes.PoemBLO;
//import bll.classes.RootsBLO;
//import bll.classes.TokenBLO;
//import bll.classes.VerseBLO;
//import bll.interfaces.IBLLFacade;
//import bll.interfaces.IBooksBLO;
//import bll.interfaces.IPeomBLO;
//import bll.interfaces.IRootsBLO;
//import bll.interfaces.ITokenBLO;
//import bll.interfaces.IVerseBLO;
//import dal.BooksDAOStub;
//import dal.PoemDAOStub;
//import dal.RootDAOStub;
//import dal.TokenDAOStub;
//import dal.VersesDAOStub;
//import dal.classes.DalFacade;
//import dal.interfaces.IBookDAO;
//import dal.interfaces.IDalFacade;
//import dal.interfaces.IPoemDAO;
//import dal.interfaces.IRootDAO;
//import dal.interfaces.ITokenDAO;
//import dal.interfaces.IVerseDAO;
//import transferObjects.VerseTO;
//
//class VersesBLOTest {
//
//	
//	IPoemDAO poemDAO = new PoemDAOStub();
//	IBookDAO bookDAO = new BooksDAOStub();
//	IRootDAO rootDAO = new RootDAOStub();
//	ITokenDAO tokenDAO = new TokenDAOStub();
//	IVerseDAO verseDAO = new VersesDAOStub();
//
//	IDalFacade facadeDAL = DalFacade.getInstance(poemDAO, bookDAO, rootDAO, tokenDAO, verseDAO);
//
//	IBooksBLO booksBLO = new BooksBLO(facadeDAL);
//	IPeomBLO peomBLO = new PoemBLO(facadeDAL);
//	IRootsBLO rootsBLO = new RootsBLO(facadeDAL);
//	ITokenBLO tokenBLO = new TokenBLO(facadeDAL);
//	IVerseBLO verseBLO = new VerseBLO(facadeDAL);
//
//	IBLLFacade facadeBLL = BLLFacade.getInstance(booksBLO, peomBLO, rootsBLO, tokenBLO, verseBLO);
//	VerseTO verseTO = new VerseTO(); 
//	
//	
//	@Test
//	void test0()  {
//		int poemid = 0; 
//		facadeBLL.getAllVerses(poemid);
//		Assertions.assertEquals(1, 1);
//	}
//	@Test
//	void test1() throws SQLException {
//		facadeBLL.saveVerse(verseTO);
//		Assertions.assertEquals(1, 1);
//	}
//	@Test
//	void test2() {
//		facadeBLL.updateVerse("exMisra1", "exMisra2", verseTO);
//		Assertions.assertEquals(1, 1);
//	}
//	@Test
//	void test3() {
//		facadeBLL.deleteVerse("misra1", "misra2");
//		Assertions.assertEquals(1, 1);
//	}
//
//
//}
///////////////////////////////////








package bll;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
//    VerseTO verseTO = new VerseTO();

    @Test
    void testSaveVerse() throws SQLException {
    	VerseTO verseTO = createSampleVerse(); 
    	
        // Arrange
        int initialSize = facadeBLL.getAllVerses(verseTO.getPoem_id()).size();

        // Act
        facadeBLL.saveVerse(verseTO);

        // Assert
        List<Map<String, Object>> allVerses = facadeBLL.getAllVerses(verseTO.getPoem_id());
        Assertions.assertEquals(initialSize + 1, allVerses.size(), "Saved verse should be in the list");
    }

    @Test
    void testUpdateVerse() throws SQLException {
        // Arrange
    	
    	VerseTO verseTO = createSampleVerse(); 
        facadeBLL.saveVerse(verseTO);
        int poemId = verseTO.getPoem_id();
        String existingMisra1 = verseTO.getMisra1();
        String existingMisra2 = verseTO.getMisra2();

        VerseTO updatedVerse = new VerseTO();
        updatedVerse.setMisra1("Updated Misra1");
        updatedVerse.setMisra2("Updated Misra2");
        updatedVerse.setPoem_id(poemId);

        // Act
        facadeBLL.updateVerse(existingMisra1, existingMisra2, updatedVerse);

        // Assert
        List<Map<String, Object>> allVerses = facadeBLL.getAllVerses(poemId);
        Assertions.assertTrue(containsVerseWithProperties(allVerses, updatedVerse), "Updated verse should be in the list");
    }

    @Test
    void testGetAllVerses() {
        // Arrange
        int poemId = 1; // Replace with an existing poem ID

        // Act
        List<Map<String, Object>> allVerses = facadeBLL.getAllVerses(poemId);

        // Assert
        Assertions.assertNotNull(allVerses, "Returned list should not be null");
        // Add more specific assertions based on your test data
    }

    @Test
    void testDeleteVerse() throws SQLException {
        // Arrange
    	
    	VerseTO verseTO = createSampleVerse(); 
        facadeBLL.saveVerse(verseTO);
        int poemId = verseTO.getPoem_id();

        // Act
        facadeBLL.deleteVerse(verseTO.getMisra1(), verseTO.getMisra2());

        // Assert
        List<Map<String, Object>> allVerses = facadeBLL.getAllVerses(poemId);
        Assertions.assertFalse(containsVerseWithProperties(allVerses, verseTO), "Deleted verse should not be in the list");

    }

    private boolean containsVerseWithProperties(List<Map<String, Object>> allVerses, VerseTO verse) {
        for (Map<String, Object> verseMap : allVerses) {
            if (verseMap.get("misra1").equals(verse.getMisra1()) &&
                verseMap.get("misra2").equals(verse.getMisra2()) &&
                verseMap.get("poem_id").equals(verse.getPoem_id())) {
                return true;
            }
        }
        return false;
    }
    
    private VerseTO createSampleVerse() {
        VerseTO verseTO = new VerseTO();
        verseTO.setMisra1("Sample Misra1");
        verseTO.setMisra2("Sample Misra2");
        verseTO.setPoem_id(1); // Assuming there is an existing poem with ID 1
        return verseTO;
    }
    
    
}

