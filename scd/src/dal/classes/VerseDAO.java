package dal.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dal.interfaces.IVerseDAO;
import transferObjects.VerseTO;

public class VerseDAO implements IVerseDAO {
	DBconfig dbconnection = DBconfig.getInstance();
	private static final Logger logger = LogManager.getLogger(VerseDAO.class);

	@Override
	public void saveVerse(VerseTO verse) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dbconnection.getConnection();
			String sql = "INSERT INTO verses (misra1, misra2, poem_id) VALUES (?, ? , ?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, verse.getMisra1());
			preparedStatement.setString(2, verse.getMisra2());
			preparedStatement.setInt(3, verse.getPoem_id());

			preparedStatement.executeUpdate();
			updateVerseCount(verse.getPoem_id());

		} catch (Exception e) {
			logger.debug("saveVerse func triggerd an exception");
			// TODO: handle exception

			e.printStackTrace();

		} finally {
			DBconfig.close(connection, preparedStatement);
		}
	}

	@Override
	public void updateVerse(String existingMisra1, String existingMisra2, VerseTO verse) throws SQLException {
		try (Connection connection = dbconnection.getConnection()) {
			String updateBookSQL = "UPDATE verses SET misra1 = ?, misra2 = ? WHERE misra1 = ? AND misra2 = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(updateBookSQL)) {
				preparedStatement.setString(1, verse.getMisra1());
				preparedStatement.setString(2, verse.getMisra2());

				preparedStatement.setString(3, existingMisra1);
				preparedStatement.setString(4, existingMisra2);
				preparedStatement.executeUpdate();

			}
		}
	}

	@Override
	public void deleteVerse(String misra1, String misra2) throws SQLException {
		try (Connection connection = dbconnection.getConnection()) {
			String deleteBookSQL = "DELETE FROM verses WHERE misra1 = ? AND misra2 = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(deleteBookSQL)) {
				preparedStatement.setString(1, misra1);
				preparedStatement.setString(2, misra2);
				preparedStatement.executeUpdate();
			}
		}
	}

	
	@Override
	public List<Map<String, Object>> getAllVerses(int poem_id) throws SQLException {
		List<Map<String, Object>> verses = new ArrayList<>();

		try (Connection connection = dbconnection.getConnection()) {
			String selectAllVersesSQL = "SELECT * FROM verses WHERE poem_id = ?";

			try (PreparedStatement preparedStatement = connection.prepareStatement(selectAllVersesSQL)) {
				preparedStatement.setInt(1, poem_id);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						Map<String, Object> verse = new HashMap<>();
						int verseId = resultSet.getInt("verse_id");
						verse.put("verseId", verseId);
						verse.put("misra1", resultSet.getString("misra1"));
						verse.put("misra2", resultSet.getString("misra2"));
						verse.put("verseNumber", resultSet.getInt("verse_number"));
						verse.put("poemId", resultSet.getInt("poem_id"));

						verses.add(verse);
					}
				}
			}
		} catch (SQLException e) {
			logger.debug("getAllVerses func triggerd an exception");
			e.printStackTrace();
		}

		if (verses.isEmpty()) {

			System.out.println("verses is empty =======================");
		}

		return verses;
	}
	
	void updateVerseCount(int id) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dbconnection.getConnection();
			String sql2 = "UPDATE poems SET total_verses = (total_verses + 1) WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql2);
			preparedStatement.setInt(1, id);
//			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			logger.debug("updateVerseCount func triggerd an exception");
			e.printStackTrace();
		} finally {
			DBconfig.close(connection, preparedStatement);
		}

	}

	@Override
	public List<Map<String, Object>> getAllVersesByRoots(String rootVal) throws SQLException {
		List<Map<String, Object>> verses = new ArrayList<>();

		try (Connection connection = dbconnection.getConnection()) {
			String selectAllVersesSQL = "SELECT * FROM verses INNER JOIN root ON (verses.verse_id = root.verse_id) WHERE root.root LIKE '% ? %'";

			try (PreparedStatement preparedStatement = connection.prepareStatement(selectAllVersesSQL)) {
				preparedStatement.setString(1, rootVal);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						Map<String, Object> verse = new HashMap<>();
						int verseId = resultSet.getInt("verse_id");
						verse.put("verseId", verseId);
						verse.put("misra1", resultSet.getString("misra1"));
						verse.put("misra2", resultSet.getString("misra2"));
						verse.put("verseNumber", resultSet.getInt("verse_number"));
						verse.put("poemId", resultSet.getInt("poem_id"));

						verses.add(verse);
					}
				}
			}
		} catch (SQLException e) {
			logger.debug("getAllVersesByRoots func triggerd an exception");
			e.printStackTrace();
		}

		if (verses.isEmpty()) {

			System.out.println("verses is empty =======================");
		}

		return verses;
	}

	/// ================================
	/// ================================

	@Override
	public String searchAndDisplayVerses(String root) {
//		String root = rootTextField.getText().trim();

		try (Connection connection = dbconnection.getConnection()) {
			// Check if the root exists in the verse table
			if (VerseHavingRoot(root)) {
				String query = "SELECT verse_number, misra1, misra2 " + "FROM verses "
						+ "WHERE verse_id IN (SELECT verse_id FROM tokens WHERE tokens.token = ?)";

				try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
					preparedStatement.setString(1, root.toLowerCase());

					try (ResultSet resultSet = preparedStatement.executeQuery()) {
						StringBuilder result = new StringBuilder();
						while (resultSet.next()) {
							int verseNumber = resultSet.getInt("verse_number");
							String misra1 = resultSet.getString("misra1");
							String misra2 = resultSet.getString("misra2");
							result.append("Verse ").append(verseNumber).append(": ").append(misra1).append(" ")
									.append(misra2).append("\n");
						}

						if (result.length() == 0) {
//							
							System.out.println("No verses found for the given root.");
						} else {
							return result.toString(); 
//							verseTextArea.setText(result.toString());
						}
					}
				}
			} else {

				System.out.println("Root does not exist in the verse table.");
			}
		} catch (Exception e) {
			logger.debug("searchAndDisplayVerses func triggerd an exception");
			e.printStackTrace();

			System.out.println("Error occurred. Check console for details.");
		}
		System.out.println("Search completed for root: " + root);
		return "---";
	}

	private boolean VerseHavingRoot(String root) {

		try (Connection connection = dbconnection.getConnection()) {

			String query = "SELECT COUNT(*) AS count FROM verses WHERE verse_id IN (SELECT verse_id FROM tokens WHERE tokens.token = ?)";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, root.toLowerCase());

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						int count = resultSet.getInt("count");
						return count > 0;
					}
				}
			} catch (Exception e) {
				logger.debug("rootExistsInVerse func triggerd an exception");
				e.printStackTrace();
			}

		} catch (Exception e) {
			logger.debug("rootExistsInVerse func triggerd an exception");
			// TODO: handle exception
		}

		return false;
	}


}
