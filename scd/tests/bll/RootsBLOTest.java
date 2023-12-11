package bll;
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
//import dal.facadeBLL;
//import dal.TokenDAOStub;
//import dal.VersesDAOStub;
//import dal.classes.DalFacade;
//import dal.interfaces.IBookDAO;
//import dal.interfaces.IDalFacade;
//import dal.interfaces.IPoemDAO;
//import dal.interfaces.IRootDAO;
//import dal.interfaces.ITokenDAO;
//import dal.interfaces.IVerseDAO;
//import transferObjects.RootTO;
//
//class RootsBLOTest {
//
//	IPoemDAO poemDAO = new PoemDAOStub();
//	IBookDAO bookDAO = new BooksDAOStub();
//	IRootDAO rootDAO = new facadeBLL();
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
//	RootTO rootTO = new RootTO(1, "a", "b"); 
//	
//	
//	@Test
//	void test1() throws SQLException {
//		facadeBLL.getAllRoots(); 
//		Assertions.assertEquals(1, 1);
//	}
//	@Test
//	void test2() throws SQLException {
//		int poemid = 1; 
//		facadeBLL.rootProcessing(poemid);
//		Assertions.assertEquals(1, 1);
//	}
//	@Test
//	void test3() {
//		 
//		facadeBLL.updateWord("root", "id");
//		Assertions.assertEquals(1, 1);
//	}
//	@Test
//	void test4() {
//		facadeBLL.deleteroot("id");
//		Assertions.assertEquals(1, 1);
//	}
//	@Test
//	void test5() {
//		facadeBLL.insertRoot(rootTO);
//		Assertions.assertEquals(1, 1);
//	}
//	@Test
//	void test6() {
//		int verseid = 0; 
//		facadeBLL.getAllRootsByVerseId(verseid); 
//		Assertions.assertEquals(1, 1);
//	}
//	@Test
//	void test7() {
//		int verseid = 0; 
//		facadeBLL.updateStatus(verseid);
//		Assertions.assertEquals(1, 1);
//	}
//	@Test
//	void test8() {
//		facadeBLL.updateRootStatus("selected root", "selected verse");
//		Assertions.assertEquals(1, 1);
//	}
//
//
//}


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
//import dal.facadeBLL;
import dal.classes.DalFacade;
import dal.interfaces.IBookDAO;
import dal.interfaces.IDalFacade;
import dal.interfaces.IPoemDAO;
import dal.interfaces.IRootDAO;
import dal.interfaces.ITokenDAO;
import dal.interfaces.IVerseDAO;
import transferObjects.RootTO;

public class RootsBLOTest {

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
    void testInsertRoot() throws SQLException {
        // Arrange
//        facadeBLL facadeBLL = new facadeBLL();
        RootTO rootTO = new RootTO(1, "testRoot", "status");

        // Act
        facadeBLL.insertRoot(rootTO);

        // Assert
        List<Map<String, Object>> allRoots = facadeBLL.getAllRoots();
        Assertions.assertTrue(containsRootWithProperties(allRoots, rootTO), "Inserted root should be in the list");
    }

    @Test
    void testGetAllRoots() throws SQLException {
        // Arrange
//        facadeBLL facadeBLL = new facadeBLL();

        // Act
        List<Map<String, Object>> allRoots = facadeBLL.getAllRoots();

        // Assert
        Assertions.assertNotNull(allRoots, "Returned list should not be null");
//        Assertions.assertEquals(1, allRoots.size(), "List should be empty initially");
    }



    @Test
    void testGetAllRootsByVerseId() throws SQLException {
        // Arrange
//        facadeBLL facadeBLL = new facadeBLL();
        RootTO rootTO1 = new RootTO(1, "root1", "status");
        RootTO rootTO2 = new RootTO(2, "root2", "status");
        facadeBLL.insertRoot(rootTO1);
        facadeBLL.insertRoot(rootTO2);

        // Act
        List<Map<String, Object>> rootsByVerseId = facadeBLL.getAllRootsByVerseId(1);

        // Assert
        Assertions.assertNotNull(rootsByVerseId, "Returned list should not be null");
        Assertions.assertEquals(1, rootsByVerseId.size(), "List should contain roots for the specified verseId");
        Assertions.assertTrue(containsRootWithProperties(rootsByVerseId, rootTO1), "List should contain the correct root");
    }

    @Test
    void testUpdateStatus() throws SQLException {
        // Arrange
//        facadeBLL facadeBLL = new facadeBLL();
        RootTO rootTO1 = new RootTO(1, "root1", "status");
        RootTO rootTO2 = new RootTO(2, "root2", "status");
        facadeBLL.insertRoot(rootTO1);
        facadeBLL.insertRoot(rootTO2);

        // Act
        facadeBLL.updateStatus(1);

        // Assert
        List<Map<String, Object>> allRoots = facadeBLL.getAllRoots();
        Assertions.assertTrue(containsRootsWithUpdatedStatus(allRoots), "Status should be updated");
    }

    @Test
    void testUpdateRootStatus() throws SQLException {
        // Arrange
//        facadeBLL facadeBLL = new facadeBLL();
        RootTO rootTO1 = new RootTO(1, "root1", "status");
        RootTO rootTO2 = new RootTO(2, "root2", "status");
        facadeBLL.insertRoot(rootTO1);
        facadeBLL.insertRoot(rootTO2);

        // Act
        facadeBLL.updateRootStatus("root1", "1");

        // Assert
        List<Map<String, Object>> allRoots = facadeBLL.getAllRoots();
        Assertions.assertTrue(containsRootsWithUpdatedStatus(allRoots), "Status should be updated");
    }



    // Helper method to check if a list of roots contains a root with specific properties
    private boolean containsRootWithProperties(List<Map<String, Object>> allRoots, RootTO root) {
        for (Map<String, Object> rootMap : allRoots) {
            if (rootMap.get("id").equals(root.getRoot_id()) &&
                    rootMap.get("verse_id").equals(root.getVerse_id()) &&
                    rootMap.get("root").equals(root.getRoot()) &&
                    rootMap.get("status").equals(root.getStatus())) {
                return true;
            }
        }
        return false;
    }

    // Helper method to check if a list of roots contains roots with updated status
    private boolean containsRootsWithUpdatedStatus(List<Map<String, Object>> roots) {
        for (Map<String, Object> rootMap : roots) {
            if (rootMap.get("status").equals("Updated Status")) {
                return true;
            }
        }
        return false;
    }
    
    
}

