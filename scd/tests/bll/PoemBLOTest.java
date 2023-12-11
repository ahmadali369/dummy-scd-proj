//package bll;
//
//import java.io.File;
//import java.sql.SQLException;
//
//import javax.swing.JFrame;
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
//import transferObjects.PoemTO;
//import transferObjects.VerseTO;
//
//class PoemBLOTest {
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
//
//	PoemTO poemTO = new PoemTO();
//	VerseTO verseTO = new VerseTO();
//
//	@Test
//	void test1() throws SQLException {
//
//		facadeBLL.savePoem(poemTO);
//
//		Assertions.assertEquals(1, 1);
//	}
//
//	@Test
//	void test2() throws SQLException {
//
//		int bookid = 0;
//		File file = new File("");
//		facadeBLL.importPoem(bookid, file);
//
//		Assertions.assertEquals(1, 1);
//	}
//
//	@Test
//	void test3() throws SQLException {
//
//		facadeBLL.getPoemsByRoot("root");
//
//		Assertions.assertEquals(1, 1);
//	}
//
//	@Test
//	void test4() {
//		String existingTitleString = " ";
//		facadeBLL.updatePoem(existingTitleString, poemTO);
//		Assertions.assertEquals(1, 1);
//	}
//
//	@Test
//	void test5() {
//
//		facadeBLL.deletePoem("");
//		Assertions.assertEquals(1, 1);
//
//	}
//
//	@Test
//	void test6() {
//
//		int bookid = 0;
//		facadeBLL.getAllPoems(bookid);
//		Assertions.assertEquals(1, 1);
//
//	}
//
//}



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
        // Arrange
        PoemTO poemTO = createSamplePoem();

        // Act
        facadeBLL.savePoem(poemTO);

        // Assert
        List<Map<String, Object>> allPoems = facadeBLL.getAllPoems(poemTO.getBookId());
        Assertions.assertTrue(containsPoemWithProperties(allPoems, poemTO), "Saved poem should be in the list");
    }

    @Test
    void testImportPoem() throws SQLException {
        // Arrange
        int bookid = 0;
        File file = new File("test-file");

        // Act
        facadeBLL.importPoem(bookid, file);

        // Assert
        // You can implement assertions based on your import logic
    }

    @Test
    void testUpdatePoem() {
        // Arrange
        PoemTO existingPoem = createSamplePoem();
        try {
			facadeBLL.savePoem(existingPoem);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        PoemTO updatedPoem = createSamplePoem(); // You can modify properties here
        updatedPoem.setTitle("Updated Title");

        // Act
        facadeBLL.updatePoem(existingPoem.getTitle(), updatedPoem);

        // Assert
        List<Map<String, Object>> allPoems = facadeBLL.getAllPoems(existingPoem.getBookId());
        Assertions.assertTrue(containsPoemWithProperties(allPoems, updatedPoem), "Updated poem should be in the list");
    }

    @Test
    void testDeletePoem() throws SQLException {
        // Arrange
        PoemTO poemTO = createSamplePoem();
        facadeBLL.savePoem(poemTO);

        // Act
        facadeBLL.deletePoem(poemTO.getTitle());

        // Assert
        List<Map<String, Object>> allPoems = facadeBLL.getAllPoems(poemTO.getBookId());
        Assertions.assertFalse(allPoems.contains(poemTO), "Deleted poem should not be in the list");
    }

 // ... (existing methods)

//    @Test
//    void testGetAllPoems() throws SQLException {
//        // Arrange
//        PoemTO poem1 = createSamplePoem();
//        PoemTO poem2 = createSamplePoem(); // Create another poem for diversity
//        facadeBLL.savePoem(poem1);
//        facadeBLL.savePoem(poem2);
//
//        // Act
//        List<Map<String, Object>> allPoems = facadeBLL.getAllPoems(poem1.getBookId());
//
//        // Print debug information
//        System.out.println("Debug - All Poems: " + allPoems);
//
//        // Assert
//        Assertions.assertEquals(2, allPoems.size(), "There should be two poems in the list");
//        Assertions.assertTrue(containsPoemWithProperties(allPoems, poem1), "Poem 1 should be in the list");
//        Assertions.assertTrue(containsPoemWithProperties(allPoems, poem2), "Poem 2 should be in the list");
//    }


    @Test
    void testGetPoemsByRoot() throws SQLException {
        // Arrange
        String root = "sampleRoot";
        PoemTO poem1 = createSamplePoem();
        PoemTO poem2 = createSamplePoem(); // Create another poem for diversity
        facadeBLL.savePoem(poem1);
        facadeBLL.savePoem(poem2);

        // Act
        List<Map<String, Object>> poemsByRoot = facadeBLL.getPoemsByRoot(root);

        // Assert
        Assertions.assertNotNull(poemsByRoot, "Returned list should not be null");
        Assertions.assertTrue(poemsByRoot.isEmpty(), "List should be empty as no poems have the specified root");
    }

    // ... (other test methods)



    private boolean containsPoemWithProperties(List<Map<String, Object>> allPoems, PoemTO poem) {
        for (Map<String, Object> poemMap : allPoems) {
            if (poemMap.get("title").equals(poem.getTitle()) &&
                    poemMap.get("bookId").equals(poem.getBookId()) &&
                    poemMap.get("total_verses").equals(poem.getTotal_verses())) {
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

