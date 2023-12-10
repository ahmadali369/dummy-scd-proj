package bll.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import transferObjects.RootTO;

public interface IRootsBLO {

	public List getAllRoots() throws SQLException;

//	public void insertroot(String rootdata) throws SQLException;    //

	public void updateWord(String rootw, String id);

	public void deleteroot(String id);

	
	//
	public void insertRoot(RootTO root); 
	public List<Map<String, Object>> getAllRootsByVerseId(int verseId) ; 
	public void updateStatus(int verse_id) ; 
	///
	
	
	public void updateRootStatus(String selectedRoot, String selectedVerse); 
	
	
}
