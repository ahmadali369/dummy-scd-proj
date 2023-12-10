package dal;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import dal.interfaces.IRootDAO;
import transferObjects.RootTO;

public class RootDAOStub implements IRootDAO{

	@Override
	public List getAllRoots() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public void insertrootword(String root) throws SQLException {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void updateroot(String rootw, String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteroot(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertRoot(RootTO root) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Map<String, Object>> getAllRootsByVerseId(int verseId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(int verse_id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRootStatus(String selectedRoot, String selectedVerse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getVerseId(String selectedVerse) {
		// TODO Auto-generated method stub
		return 0;
	}

}
