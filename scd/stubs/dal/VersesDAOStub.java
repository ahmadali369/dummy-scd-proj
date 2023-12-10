package dal;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import dal.interfaces.IVerseDAO;
import transferObjects.VerseTO;

public class VersesDAOStub implements IVerseDAO{

	@Override
	public void saveVerse(VerseTO verse) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateVerse(String existingMisra1, String existingMisra2, VerseTO verse) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteVerse(String misra1, String misra2) throws SQLException {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public List<Map<String, Object>> getAllVersesByRoots(String rootVal) throws SQLException {
//		// TODO Auto-generated method stub
//		return null;
//	}



//	@Override
//	public String searchAndDisplayVerses(String root) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<Map<String, Object>> getAllVerses(int poem_id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
