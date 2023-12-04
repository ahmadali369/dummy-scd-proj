package dal;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import dal.interfaces.ITokenDAO;
import transferObjects.TokenTO;

public class TokenDAOStub implements ITokenDAO{



	@Override
	public void insertToken(TokenTO token) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Map<String, Object>> getAllTokens(int verseId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
