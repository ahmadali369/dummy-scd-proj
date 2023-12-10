package dal.interfaces;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import transferObjects.PoemTO;
import transferObjects.VerseTO;

public interface IPoemDAO {

	public void savePoem(PoemTO poem) throws SQLException;

	public List<Map<String, Object>> getAllPoems(int bookid) throws SQLException;
	
	public List<Map<String, Object>> getPoemsByRoot(String root) throws SQLException; 

//	public void insertDataFromJTable(PoemTO poem, VerseTO verse);

	public void importPoem(int bookid, File file) throws SQLException;

//	public void choosePoemFile(JFrame frame, int bookid);

	public void updatePoem(String existingTitle, PoemTO poem) throws SQLException;

	public void deletePoem(String title) throws SQLException;

	public String goToPoem(String root);

}
