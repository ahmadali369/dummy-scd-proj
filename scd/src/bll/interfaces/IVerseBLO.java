package bll.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import transferObjects.VerseTO;

public interface IVerseBLO {
	
	public List<Map<String, Object>> getAllVerses(int poem_id); 
	
	public void saveVerse(VerseTO verse) throws SQLException;

	public void updateVerse(String existingMisra1, String existingMisra2, VerseTO verse);

	public void deleteVerse(String misra1, String misra2);
	
	
//==
	
//	public List<Map<String, Object>> getAllVersesByRoots(String rootVal);   //
//
//	public String searchAndDisplayVerses(String root);    //
//	
//	public int getVerseId(String selectedVerse);   //
	 

}
