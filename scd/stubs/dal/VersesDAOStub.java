//package dal;
//
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Map;
//
//import dal.interfaces.IVerseDAO;
//import transferObjects.VerseTO;
//
//public class VersesDAOStub implements IVerseDAO{
//
//	@Override
//	public void saveVerse(VerseTO verse) throws SQLException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void updateVerse(String existingMisra1, String existingMisra2, VerseTO verse) throws SQLException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteVerse(String misra1, String misra2) throws SQLException {
//		// TODO Auto-generated method stub
//		
//	}
//
////	@Override
////	public List<Map<String, Object>> getAllVersesByRoots(String rootVal) throws SQLException {
////		// TODO Auto-generated method stub
////		return null;
////	}
//
//
//
////	@Override
////	public String searchAndDisplayVerses(String root) {
////		// TODO Auto-generated method stub
////		return null;
////	}
//
//	@Override
//	public List<Map<String, Object>> getAllVerses(int poem_id) throws SQLException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}

package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dal.interfaces.IVerseDAO;
import transferObjects.VerseTO;

public class VersesDAOStub implements IVerseDAO {

    // Simulate an in-memory database
    private static final List<VerseTO> versesDatabase = new ArrayList<>();

    @Override
    public void saveVerse(VerseTO verse) throws SQLException {
        // Simulate saving a verse into the database
        versesDatabase.add(verse);
    }

    @Override
    public void updateVerse(String existingMisra1, String existingMisra2, VerseTO verse) throws SQLException {
        // Simulate updating a verse in the database
        for (int i = 0; i < versesDatabase.size(); i++) {
            VerseTO currentVerse = versesDatabase.get(i);
            if (currentVerse.getMisra1().equals(existingMisra1) &&
                currentVerse.getMisra2().equals(existingMisra2)) {
                // Found the verse to update
                versesDatabase.set(i, verse);
                break;
            }
        }
    }

    @Override
    public void deleteVerse(String misra1, String misra2) throws SQLException {
        // Simulate deleting a verse from the database
        versesDatabase.removeIf(verse -> verse.getMisra1().equals(misra1) && verse.getMisra2().equals(misra2));
    }

    @Override
    public List<Map<String, Object>> getAllVerses(int poem_id) throws SQLException {
        // Simulate retrieving all verses from the database
        List<Map<String, Object>> result = new ArrayList<>();
        for (VerseTO verse : versesDatabase) {
            Map<String, Object> verseMap = new HashMap<>();
            verseMap.put("misra1", verse.getMisra1());
            verseMap.put("misra2", verse.getMisra2());
            verseMap.put("poem_id", verse.getPoem_id());
            result.add(verseMap);
        }
        return result;
    }
}

