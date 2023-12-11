
package bll;

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
import dal.classes.DalFacade;
import dal.interfaces.IBookDAO;
import dal.interfaces.IDalFacade;
import dal.interfaces.IPoemDAO;
import dal.interfaces.IRootDAO;
import dal.interfaces.ITokenDAO;
import dal.interfaces.IVerseDAO;
import transferObjects.TokenTO;

class TokenBLOTest {

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
	void testInsertToken() {

		TokenTO tokenTO = createSampleToken();

		facadeBLL.insertToken(tokenTO);

		List<Map<String, Object>> allTokens = facadeBLL.getAllTokens(tokenTO.getVerse_id());
		Assertions.assertTrue(containsTokenWithProperties(allTokens, tokenTO), "Inserted token should be in the list");
	}

	@Test
	void testGetAllTokens() {

		List<Map<String, Object>> allTokens = facadeBLL.getAllTokens(0);
		System.out.println("Number of tokens retrieved: " + allTokens.size());

		Assertions.assertNotNull(allTokens, "Returned list should not be null");
		Assertions.assertEquals(1, allTokens.size(), "List should be empty initially");
	}

	private boolean containsTokenWithProperties(List<Map<String, Object>> allTokens, TokenTO token) {
		for (Map<String, Object> tokenMap : allTokens) {
			if (tokenMap.get("token").equals(token.getToken()) && tokenMap.get("verse_id").equals(token.getVerse_id())
					&& tokenMap.get("pos").equals(token.getPos())) {
				return true;
			}
		}
		return false;
	}

	private TokenTO createSampleToken() {
		TokenTO tokenTO = new TokenTO("SampleToken", 0, "Noun");
		return tokenTO;
	}
}
