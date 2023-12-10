package bll.classes;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bll.interfaces.IPeomBLO;
import dal.interfaces.IDalFacade;
import transferObjects.PoemTO;
import transferObjects.VerseTO;

public class PoemBLO implements IPeomBLO {

	private IDalFacade dal;
	private static final Logger logger = LogManager.getLogger(PoemBLO.class);

	public PoemBLO(IDalFacade obj) {
		this.dal = obj;
	}

	@Override
	public void savePoem(PoemTO poem) throws SQLException {
		dal.savePoem(poem);
	}

	@Override
	public void updatePoem(String existingTitle, PoemTO poem) {
		// TODO Auto-generated method stub
		try {
			dal.updatePoem(existingTitle, poem);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.debug("updatePoem func triggerd an exception");
			e.printStackTrace();
		}

	}

	@Override
	public void deletePoem(String title) {
		// TODO Auto-generated method stub
		try {
			dal.deletePoem(title);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.debug("deletePoem func triggerd an exception");
			e.printStackTrace();
		}

	}

	@Override
	public void importPoem(int bookid, File file) throws SQLException {
		// TODO Auto-generated method stub
		dal.importPoem(bookid, file);

	}
	
	@Override
	public List<Map<String, Object>> getAllPoems(int bookid) {
		// TODO Auto-generated method stub

		try {
			return dal.getAllPoems(bookid);
		} catch (SQLException e) {
			logger.debug("getAllpoem func triggerd an exception");

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	@Override
	public String goToPoem(String root) {
		// TODO Auto-generated method stub
		return dal.goToPoem(root);
	}

	@Override
	public List<Map<String, Object>> getPoemsByRoot(String root) {
		// TODO Auto-generated method stub
		try {
			return dal.getPoemsByRoot(root);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}


}
