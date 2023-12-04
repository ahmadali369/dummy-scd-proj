package dal.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import transferObjects.TokenTO;

public interface ITokenDAO {

		
	public void insertToken(TokenTO token) throws SQLException; 
	public List<Map<String, Object>> getAllTokens(int verseId) throws SQLException ; 
}
