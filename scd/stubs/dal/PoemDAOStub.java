package dal;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import dal.interfaces.IPoemDAO;
import transferObjects.PoemTO;
import transferObjects.VerseTO;

public class PoemDAOStub implements IPoemDAO{

	@Override
	public void savePoem(PoemTO poem) throws SQLException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void importPoem(int bookid, File file) throws SQLException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void updatePoem(String existingTitle, PoemTO poem) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePoem(String title) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<Map<String, Object>> getAllPoems(int bookid) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public String goToPoem(String root) {
//		// TODO Auto-generated method stub
//		return null;
//	}



	@Override
	public List<Map<String, Object>> getPoemsByRoot(String root) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
