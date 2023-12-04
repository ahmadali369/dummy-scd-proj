package dal.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import transferObjects.RootTO;

public interface IRootDAO {

	public List getrootword() throws SQLException;

	public void insertrootword(String root) throws SQLException;

	public void updateroot(String rootw, String id);

	public void deleteroot(String id);
	
	// ahmad
	public void insertRoot(RootTO root) throws SQLException; 
	public List<Map<String, Object>> getAllRoots(int verseId) throws SQLException ; 
	public void updateStatus(int verse_id) throws SQLException; 
	
	// aqsa javaid
	public void updateRootStatus(String selectedRoot, String selectedVerse); 
	public int getVerseId(String selectedVerse); 
}
