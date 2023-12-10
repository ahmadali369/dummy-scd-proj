package bll.classes;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bll.interfaces.IVerseBLO;
import dal.interfaces.IDalFacade;
import transferObjects.VerseTO;

public class VerseBLO implements IVerseBLO {

	public IDalFacade facadeDal;
	private static final Logger logger = LogManager.getLogger(VerseBLO.class);
	public VerseBLO(IDalFacade facadeDal) {
		this.facadeDal = facadeDal;
	}

	@Override
	public void saveVerse(VerseTO verse) throws SQLException {

		facadeDal.saveVerse(verse);

	}

	@Override
	public void updateVerse(String existingMisra1, String existingMisra2, VerseTO verse) {
		// TODO Auto-generated method stub
		try {
			facadeDal.updateVerse(existingMisra1, existingMisra2, verse);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.debug("updateVerse func triggerd an exception");
			e.printStackTrace();
		}

	}
	
	
	@Override
	public List<Map<String, Object>> getAllVerses(int poem_id) {
		// TODO Auto-generated method stub
		try {

//			List<Map<String, Object>> verses = facadeDal.getAllVerses(poem_id);
//			for (Map<String, Object> verse : verses) {
//
//				String misra1 = (String) verse.get("misra1");
//				String misra2 = (String) verse.get("misra2");
//				int verse_id = (int) verse.get("verseId");
//
//				List<Map<String, Object>> tokens = generateTokens(misra1, misra2, verse_id);
//
//				for (Map<String, Object> token : tokens) {
//
//					String tokenValue = (String) token.get("token");
//
//					String pos = (String) token.get("pos");
////					    System.out.println("pos----------------" + pos);
//
//					int verseId = (int) token.get("verseId");
//					int rootId = (int) token.get("rootId");
//
//					TokenTO tokenn = new TokenTO(tokenValue, verseId, pos);
//					insertToken(tokenn);
//					RootTO root = new RootTO(verse_id, createRoot(tokenValue), "Auto");
//
//					facadeDal.insertRoot(root);
//
//					
//
//				}
//
//			}

			return facadeDal.getAllVerses(poem_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.debug("getAllVerses func triggerd an exception");
			e.printStackTrace();

		}

		return null;
	}

	@Override
	public void deleteVerse(String misra1, String misra2) {
		// TODO Auto-generated method stub
		try {
			facadeDal.deleteVerse(misra1, misra2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.debug("deleteVerse func triggerd an exception");
			e.printStackTrace();
		}

	}

//	@Override
//	public List<Map<String, Object>> getAllVersesByRoots(String rootVal) {
//		// TODO Auto-generated method stub
//		try {
//			return facadeDal.getAllVersesByRoots(rootVal);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			logger.debug("getAllVersesByRoots func triggerd an exception");
//			e.printStackTrace();
//		}
//		return null; 
//	}



//	@Override
//	public String searchAndDisplayVerses(String root) {
//		// TODO Auto-generated method stub
//		return facadeDal.searchAndDisplayVerses(root);
//	}
//	@Override
//	public int getVerseId(String selectedVerse) {
//		// TODO Auto-generated method stub
//		return facadeDal.getVerseId(selectedVerse);
//	}

}
