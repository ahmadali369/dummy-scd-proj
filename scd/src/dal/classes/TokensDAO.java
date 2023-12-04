package dal.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bll.classes.BooksBLO;
import dal.interfaces.ITokenDAO;
import transferObjects.TokenTO;

public class TokensDAO implements ITokenDAO {

	DBconfig dbconnection = DBconfig.getInstance();
	private static final Logger logger = LogManager.getLogger(TokensDAO.class);



	@Override
	public List<Map<String, Object>> getAllTokens(int verseId) throws SQLException {
		List<Map<String, Object>> tokens = new ArrayList<>();

		try (Connection connection = dbconnection.getConnection()) {
			String selectAllTokensSQL = "SELECT * FROM tokens WHERE verse_id = ?";

			try (PreparedStatement preparedStatement = connection.prepareStatement(selectAllTokensSQL)) {
				preparedStatement.setInt(1, verseId);

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					while (resultSet.next()) {
						Map<String, Object> token = new HashMap<>();

						int tokenId = resultSet.getInt("token_id");
						token.put("tokenId", tokenId);
						token.put("token", resultSet.getString("token"));
						token.put("verseId", resultSet.getInt("verse_id"));
//	                    token.put("rootId", resultSet.getInt("root_id"));
						token.put("pos", resultSet.getString("pos"));

						tokens.add(token);
					}

				}
			}
		}

		return tokens;
	}

	@Override
	public void insertToken(TokenTO token) throws SQLException {
		Connection connection = null;
		try {
			connection = dbconnection.getConnection();
			connection.setAutoCommit(false);

			String insertTokenSQL = "INSERT INTO tokens (token, verse_id, pos) VALUES (?, ?, ?)";
			try (PreparedStatement preparedStatement = connection.prepareStatement(insertTokenSQL,
					Statement.RETURN_GENERATED_KEYS)) {

				if (!token.getToken().contains("(") && !token.getToken().contains(")")
						&& !token.getToken().contains(" ")) {

					preparedStatement.setString(1, token.getToken());
					preparedStatement.setInt(2, token.getVerse_id());
//	            preparedStatement.setInt(3, token.getRoot_id());
					preparedStatement.setString(3, token.getPos());
					preparedStatement.executeUpdate();

					connection.commit();

				}
			}

		} catch (SQLException e) {
			logger.debug("insertToken func triggerd an exception");
			if (connection != null) {
				connection.rollback();
			}

			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

}
