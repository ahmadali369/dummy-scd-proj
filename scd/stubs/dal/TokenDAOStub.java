
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dal.interfaces.ITokenDAO;
import transferObjects.TokenTO;

public class TokenDAOStub implements ITokenDAO {

	private static final List<TokenTO> tokensDatabase = new ArrayList<>();

	@Override
	public void insertToken(TokenTO token) throws SQLException {

		tokensDatabase.add(token);
	}

	@Override
	public List<Map<String, Object>> getAllTokens(int verseId) throws SQLException {

		List<Map<String, Object>> result = new ArrayList<>();
		for (TokenTO token : tokensDatabase) {
			if (token.getVerse_id() == verseId) {
				Map<String, Object> tokenMap = new HashMap<>();
				tokenMap.put("token", token.getToken());
				tokenMap.put("verse_id", token.getVerse_id());
				tokenMap.put("pos", token.getPos());
				result.add(tokenMap);
			}
		}
		return result;
	}
}
