package dal.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dal.interfaces.IRootDAO;
import transferObjects.RootTO;

public class RootDAO implements IRootDAO {

	static List<String> roots = new ArrayList<String>();
	private static final Logger logger = LogManager.getLogger(RootDAO.class);

	Connection connection;
	DBconfig dbconnection = DBconfig.getInstance();

	@Override
	public List getAllRoots() throws SQLException {
		roots.clear();
		try {

			connection = dbconnection.getConnection();
			if (!connection.isClosed()) {
				PreparedStatement ps = connection.prepareStatement("SELECT * from root");

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					int id = (int) rs.getObject("id");
					roots.add(Integer.toString(id));
					roots.add((String) rs.getObject("root"));
					roots.add((String) rs.getObject("status"));

				}

			} else
				JOptionPane.showMessageDialog(null, "Connection Not Found");
		} catch (SQLException e) {
			logger.debug("getrootword func triggerd an exception");
			JOptionPane.showMessageDialog(null, "Driver Error Found");
		}
		connection.close();
		return roots;

	}
//
//	@Override
//	public void insertrootword(String root) throws SQLException {
//
//		connection = dbconnection.getConnection();
//		String str = "";
//		try {
//
//			str = "insert into root values(" + null + ",'" + root + "')";
//			PreparedStatement st = connection.prepareStatement(str);
//			st.execute();
//
//			JOptionPane.showMessageDialog(null, "Data Inserted SUCCESSFULLY");
//		} catch (SQLException e) {
//			logger.debug("insertrootword func triggerd an exception");
//			System.out.println(e);
//		}
//		connection.close();
//	}

	@Override
	public void updateroot(String rootw, String id) {
		try {

			connection = dbconnection.getConnection();

			String query = "UPDATE root SET  root = '" + rootw + "' WHERE root like '" + id + "'";

			PreparedStatement preparedst = connection.prepareStatement(query);
			preparedst.execute();

			JOptionPane.showMessageDialog(null, "Root updated Successfully in Database");

			connection.close();
		} catch (SQLException e) {
			logger.debug("updateroot func triggerd an exception");
			JOptionPane.showMessageDialog(null, "Connection Not Found");
		}

	}

	@Override
	public void deleteroot(String id) {

		try {

			connection = dbconnection.getConnection();

			String query = "DELETE FROM root WHERE root = '" + id + "'";
			PreparedStatement preparedst = connection.prepareStatement(query);
			preparedst.execute();

			JOptionPane.showMessageDialog(null, "Root Deleted Successfully in Database");

			connection.close();
		} catch (SQLException e) {
			logger.debug("deleteroot func triggerd an exception");
			JOptionPane.showMessageDialog(null, "Connection Not Found");
		}
	}

	/// -----------------------------------------------------

	@Override
	public List<Map<String, Object>> getAllRootsByVerseId(int verseId) throws SQLException {
		List<Map<String, Object>> roots = new ArrayList<>();

		try (Connection connection = dbconnection.getConnection()) {
			String selectAllRootsSQL = "SELECT * FROM root WHERE verse_id = ?";

			try (PreparedStatement preparedStatement = connection.prepareStatement(selectAllRootsSQL)) {
				preparedStatement.setInt(1, verseId);

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					while (resultSet.next()) {
						Map<String, Object> root = new HashMap<>();

						int rootId = resultSet.getInt("id");
						root.put("rootId", rootId);
//						root.put("tokenId", resultSet.getInt("token_id"));
						root.put("verseId", resultSet.getInt("verse_id"));
						root.put("root", resultSet.getString("root"));
						root.put("status", resultSet.getString("status"));

						roots.add(root);
					}

				}
			}
		}

		return roots;
	}

	@Override
	public void insertRoot(RootTO root) throws SQLException {
		Connection connection = null;
		try {
			connection = dbconnection.getConnection();
			connection.setAutoCommit(false);

			String insertRootSQL = "INSERT INTO root (verse_id, root, status) VALUES (?, ?, ?)";
			try (PreparedStatement preparedStatement = connection.prepareStatement(insertRootSQL,
					Statement.RETURN_GENERATED_KEYS)) {

//				preparedStatement.setInt(1, root.getToken_id());
				preparedStatement.setInt(1, root.getVerse_id());
				preparedStatement.setString(2, root.getRoot());
				preparedStatement.setString(3, root.getStatus());
				preparedStatement.executeUpdate();

				connection.commit();

				try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						int generatedRootId = generatedKeys.getInt(1);

						verseRootJunction(generatedRootId, root.getVerse_id());

					} else {
						throw new SQLException("Failed to retrieve generated key for token.");
					}
				}

			}

		} catch (SQLException e) {
			logger.debug("insertRoot func triggerd an exception");
			if (connection != null) {
				connection.rollback();
			}

		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	public void verseRootJunction(int rootId, int verseId) throws SQLException {
		Connection connection = null;
		try {
			connection = dbconnection.getConnection();
			connection.setAutoCommit(false);

			String insertVerseRootSQL = "INSERT INTO verse_root_junction (verse_id, root_id) VALUES (?, ?)";
			try (PreparedStatement preparedStatement = connection.prepareStatement(insertVerseRootSQL,
					Statement.RETURN_GENERATED_KEYS)) {

				preparedStatement.setInt(1, verseId);
				preparedStatement.setInt(2, rootId);

				preparedStatement.executeUpdate();

				connection.commit();

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

	@Override
	public void updateStatus(int verse_id) throws SQLException {
		try (Connection connection = dbconnection.getConnection()) {
			String updateBookSQL = "UPDATE root SET status = 'Verified' WHERE verse_id = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(updateBookSQL)) {
				preparedStatement.setInt(1, verse_id);
				preparedStatement.executeUpdate();

			} catch (Exception e) {
				logger.debug("updateStatus func triggerd an exception");
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	// ==========================================

	@Override
	public void updateRootStatus(String selectedRoot, String selectedVerse) {

		int verseId = getVerseId(selectedVerse);

		try {
			connection = dbconnection.getConnection();
		} catch (SQLException e) {
			logger.debug("updateRootStatus func triggerd an exception");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String updateQuery = "INSERT INTO root (verse_id, root, status) VALUES (?, ?, 'Assigned')";

		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
//			preparedStatement.setInt(1, 1);
			preparedStatement.setInt(1, verseId);
			preparedStatement.setString(2, selectedRoot);

			int rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected > 0) {

				System.out.println("Root assigned successfully");
			} else {

				System.out.println("Cannot add duplicate entry");
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			logger.debug("updateRootStatus func triggerd an exception");

			System.out.println("Cannot add duplicate entry");
		} catch (SQLException e) {
			logger.debug("updateRootStatus func triggerd an exception");
			e.printStackTrace();
		}
	}

	@Override
	public int getVerseId(String selectedVerse) {

		int verseId = 0;

		try {
			connection = dbconnection.getConnection();
		} catch (SQLException e) {
			logger.debug("getVerseId func triggerd an exception");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String selectQuery = "SELECT verse_id FROM verses WHERE CONCAT(misra1, ' ', misra2) = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
			preparedStatement.setString(1, selectedVerse);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				verseId = resultSet.getInt("verse_id");
			}
		} catch (SQLException e) {
			logger.debug("getVerseId func triggerd an exception");
			e.printStackTrace();
		}

		return verseId;
	}

}