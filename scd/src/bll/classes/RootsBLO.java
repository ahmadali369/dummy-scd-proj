package bll.classes;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bll.interfaces.IRootsBLO;
import dal.interfaces.IDalFacade;
import transferObjects.RootTO;

public class RootsBLO implements IRootsBLO {

	public IDalFacade facadeDal;
	private static final Logger logger = LogManager.getLogger(RootsBLO.class);

	public RootsBLO(IDalFacade facadeDal) {
		this.facadeDal = facadeDal;
	}

	@Override
	public List getAllRoots() throws SQLException {

		return facadeDal.getAllRoots();

	}

//	@Override
//	public void insertroot(String rootdata) throws SQLException {
//
//		facadeDal.insertrootword(rootdata);
//
//	}

	@Override
	public void updateWord(String rootw, String id) {

		facadeDal.updateroot(rootw, id);

	}

	@Override
	public void deleteroot(String id) {
		facadeDal.deleteroot(id);

	}

	@Override
	public void insertRoot(RootTO root) {
		// TODO Auto-generated method stub
		try {
			facadeDal.insertRoot(root);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.debug("insertRoot func triggerd an exception");
//			e.printStackTrace();
		}

	}

	@Override
	public List<Map<String, Object>> getAllRootsByVerseId(int verseId) {
		// TODO Auto-generated method stub
		try {
			return facadeDal.getAllRootsByVerseId(verseId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.debug("getAllRoots func triggerd an exception");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateStatus(int verse_id) {
		// TODO Auto-generated method stub
		try {
			facadeDal.updateStatus(verse_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.debug("updateStatus func triggerd an exception");
			e.printStackTrace();
		}

	}

	@Override
	public void updateRootStatus(String selectedRoot, String selectedVerse) {
		// TODO Auto-generated method stub
		facadeDal.updateRootStatus(selectedRoot, selectedVerse);
	}

}
