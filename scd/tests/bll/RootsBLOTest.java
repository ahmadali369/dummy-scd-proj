package bll;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
import transferObjects.RootTO;

public class RootsBLOTest {

	private IBLLFacade facadeBLL;

	@BeforeEach
	void setUp() {
		IDaoPo dPo = new DaoStubPO(); 

		IDalFacade facadeDAL = DalFacade.getInstance(dPo);
		
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

		RootTO rootTO = new RootTO(1, "testRoot", "status");

		facadeBLL.insertRoot(rootTO);

		List<Map<String, Object>> allRoots = facadeBLL.getAllRoots();
		Assertions.assertTrue(containsRootWithProperties(allRoots, rootTO), "Inserted root should be in the list");
	}

	@Test
	void testGetAllRoots() throws SQLException {

		List<Map<String, Object>> allRoots = facadeBLL.getAllRoots();

		Assertions.assertNotNull(allRoots, "Returned list should not be null");

	}

	@Test
	void testGetAllRootsByVerseId() throws SQLException {

		RootTO rootTO1 = new RootTO(1, "root1", "status");
		RootTO rootTO2 = new RootTO(2, "root2", "status");
		facadeBLL.insertRoot(rootTO1);
		facadeBLL.insertRoot(rootTO2);

		List<Map<String, Object>> rootsByVerseId = facadeBLL.getAllRootsByVerseId(1);

		Assertions.assertNotNull(rootsByVerseId, "Returned list should not be null");
		Assertions.assertEquals(1, rootsByVerseId.size(), "List should contain roots for the specified verseId");
		Assertions.assertTrue(containsRootWithProperties(rootsByVerseId, rootTO1),
				"List should contain the correct root");
	}

	@Test
	void testUpdateStatus() throws SQLException {

		RootTO rootTO1 = new RootTO(1, "root1", "status");
		RootTO rootTO2 = new RootTO(2, "root2", "status");
		facadeBLL.insertRoot(rootTO1);
		facadeBLL.insertRoot(rootTO2);

		facadeBLL.updateStatus(1);

		List<Map<String, Object>> allRoots = facadeBLL.getAllRoots();
		Assertions.assertTrue(containsRootsWithUpdatedStatus(allRoots), "Status should be updated");
	}

	@Test
	void testUpdateRootStatus() throws SQLException {

		RootTO rootTO1 = new RootTO(1, "root1", "status");
		RootTO rootTO2 = new RootTO(2, "root2", "status");
		facadeBLL.insertRoot(rootTO1);
		facadeBLL.insertRoot(rootTO2);

		facadeBLL.updateRootStatus("root1", "1");

		List<Map<String, Object>> allRoots = facadeBLL.getAllRoots();
		Assertions.assertTrue(containsRootsWithUpdatedStatus(allRoots), "Status should be updated");
	}

	private boolean containsRootWithProperties(List<Map<String, Object>> allRoots, RootTO root) {
		for (Map<String, Object> rootMap : allRoots) {
			if (rootMap.get("id").equals(root.getRoot_id()) && rootMap.get("verse_id").equals(root.getVerse_id())
					&& rootMap.get("root").equals(root.getRoot()) && rootMap.get("status").equals(root.getStatus())) {
				return true;
			}
		}
		return false;
	}

	private boolean containsRootsWithUpdatedStatus(List<Map<String, Object>> roots) {
		for (Map<String, Object> rootMap : roots) {
			if (rootMap.get("status").equals("Updated Status")) {
				return true;
			}
		}
		return false;
	}

}
