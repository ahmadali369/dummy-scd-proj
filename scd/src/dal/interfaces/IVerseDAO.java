package dal.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import transferObjects.VerseTO;

public interface IVerseDAO {
	public void saveVerse(VerseTO verse) throws SQLException ; 
	public void updateVerse(String existingMisra1, String existingMisra2, VerseTO verse) throws SQLException; 
	public void deleteVerse(String misra1, String misra2) throws SQLException ; 
	
	public List<Map<String, Object>> getAllVerses(int poem_id) throws SQLException; 
	
	public List<Map<String, Object>> getAllVersesByRoots(String rootVal) throws SQLException; 

//

	public String searchAndDisplayVerses(String root); 

}


