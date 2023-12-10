package bll.interfaces;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import transferObjects.PoemTO;
import transferObjects.VerseTO;

public interface IPeomBLO {

	public void savePoem(PoemTO poem) throws SQLException;

	public void importPoem(int bookid, File file) throws SQLException;

	public void updatePoem(String existingTitle, PoemTO poem);

	public void deletePoem(String title);
	
	public List<Map<String, Object>> getAllPoems(int bookid);
	public List<Map<String, Object>> getPoemsByRoot(String root); 

	
	//=
//	public String goToPoem(String root);  //
}
