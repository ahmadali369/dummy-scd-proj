package dal.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bll.classes.BooksBLO;
import dal.interfaces.IPoemDAO;
import dal.interfaces.IVerseDAO;
import transferObjects.PoemTO;
import transferObjects.VerseTO;

public class PoemDAO implements IPoemDAO {

	DBconfig dbconnection = DBconfig.getInstance();

	private static final Logger logger = LogManager.getLogger(PoemDAO.class);
	
	HashMap<String, List<String[][]>> poems;

	public PoemDAO() {
		poems = new HashMap<>();

	}

	@Override
	public void savePoem(PoemTO poem) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dbconnection.getConnection();
			String sql = "INSERT INTO poems (title, book_id, total_verses) VALUES (?, ? ,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,
					poem.getTitle().length() < 200 ? poem.getTitle() : poem.getTitle().substring(0, 200));

			preparedStatement.setInt(2, poem.getBookId());
			preparedStatement.setInt(3, 0);
			preparedStatement.executeUpdate();

			updatePoemCount(poem.getBookId());
			
		}catch (Exception e) {
		
		} finally {
			DBconfig.close(connection, preparedStatement);
		}
	}
	
	@Override
	public List<Map<String, Object>> getAllPoems(int bookid) throws SQLException {
		List<Map<String, Object>> poems = new ArrayList<>();

		try (Connection connection = dbconnection.getConnection()) {
			String selectAllPoemsSQL = "SELECT * FROM poems WHERE book_id = ?";

			try (PreparedStatement preparedStatement = connection.prepareStatement(selectAllPoemsSQL)) {
				preparedStatement.setInt(1, bookid);

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						Map<String, Object> poem = new HashMap<>();

						int poemId = resultSet.getInt("id");
						poem.put("poemid", poemId);
						poem.put("title", resultSet.getString("title"));
						poem.put("total_verses", resultSet.getString("total_verses"));
						poem.put("bookId", resultSet.getInt("book_id"));                      
						poems.add(poem);
					}
				}
			}
		}



		if (poems.isEmpty()) {
			logger.debug("No poems found in the database.");
		}

		return poems;
	}
	
	
	@Override
	public List<Map<String, Object>> getPoemsByRoot(String root) throws SQLException {
	    List<Map<String, Object>> poems = new ArrayList<>();

	    try (Connection connection = dbconnection.getConnection()) {
	        // Select poems related to the given root
	        String selectPoemsByRootSQL = 
	                "SELECT DISTINCT p.id AS poemid, p.title, p.total_verses, p.book_id " +
	                "FROM poems p " +
	                "JOIN verses v ON p.id = v.poem_id " +
	                "JOIN verse_token_junction vtj ON v.verse_id = vtj.verse_id " +
	                "JOIN tokens t ON vtj.token_id = t.token_id " +
	                "LEFT JOIN verse_root_junction vrj ON v.verse_id = vrj.verse_id " +
	                "LEFT JOIN root r ON vrj.root_id = r.id AND t.token_id = r.token_id " +
	                "WHERE r.root = ? OR r.root IS NULL";

	        try (PreparedStatement preparedStatement = connection.prepareStatement(selectPoemsByRootSQL)) {
	            preparedStatement.setString(1, root);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    Map<String, Object> poem = new HashMap<>();

	                    // Retrieve data from the result set
	                    int poemId = resultSet.getInt("poemid");
	                    poem.put("poemid", poemId);
	                    poem.put("title", resultSet.getString("title"));
	                    poem.put("total_verses", resultSet.getString("total_verses"));
	                    poem.put("bookId", resultSet.getInt("book_id"));

	                    // Add the poem data to the list
	                    poems.add(poem);
	                }
	            }
	        }
	    }

	    // Return the list of poems associated with the given root
	    return poems;
	}


	
	
	

//	@Override
//	public void insertDataFromJTable(PoemTO poem, VerseTO verse) {
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		try {
//			connection = dbconnection.getConnection();
//
//			String sql = "INSERT INTO poems (title, book_id) VALUES (?, ?)";
//			preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setString(1,
//					poem.getTitle().length() < 200 ? poem.getTitle() : poem.getTitle().substring(0, 200));
//
//			preparedStatement.setInt(2, poem.getBookId());
//
//			preparedStatement.executeUpdate();
//
//			updatePoemCount(poem.getBookId());
//
//		} catch (SQLException e) {
//			logger.debug("insertdatafromjtable func triggerd and exception");
//
//			e.printStackTrace();
//
//		} finally {
//			DBconfig.close(connection, preparedStatement);
//		}
//
//	}

	
	void updatePoemCount(int id) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dbconnection.getConnection();
			String sql2 = "UPDATE books SET total_poems = (SELECT COUNT(id) FROM poems WHERE book_id = ?) WHERE book_id = ?";
			preparedStatement = connection.prepareStatement(sql2);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			logger.debug("updatePoemCount func triggerd and exception");
			e.printStackTrace();
		} finally {
			DBconfig.close(connection, preparedStatement);
		}

	}
	
	

	@Override
	public void importPoem(int bookId, File file) throws SQLException {
		// String file = "Poem.txt";
		List<String> poemTitles = new ArrayList<>();
		List<String[]> poemVerses = new ArrayList();
		IVerseDAO verseDAO = new VerseDAO();

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			boolean inPoemSection = false;
			String currentTitle = "";
			String currentVerse1 = "";
			String currentVerse2 = "";

			int verseNumber = 1; // Initialize verse number

			while ((line = reader.readLine()) != null) {
				if (line.contains("==========")) {
					inPoemSection = !inPoemSection;
					if (!inPoemSection) {
						if (!currentTitle.isEmpty()) {
							poemTitles.add(currentTitle);
							poemVerses.add(new String[] { currentVerse1, currentVerse2 });

							PoemTO poem = new PoemTO();
							VerseTO verse = new VerseTO();
							poem.setTitle(currentTitle);

							
							
							String modifiedString = currentVerse1.replace(String.valueOf(")"), "");
							 String modifiedString2 = currentVerse2.replace(String.valueOf(")"), "");
							
							
							verse.setMisra1(modifiedString);
							verse.setMisra2(modifiedString2);

							poem.setBookId(bookId);

							try {
								savePoem(poem);
								verse.setPoem_id(getPoemID(currentTitle));
								if (getPoemID(currentTitle) != -1) {
									verseDAO.saveVerse(verse);
								}
							} catch (Exception e) {

								verse.setPoem_id(getPoemID(currentTitle));
								if (getPoemID(currentTitle) != -1) {
									verseDAO.saveVerse(verse);
								}
							}
//							

							currentTitle = "";
							currentVerse1 = "";
							currentVerse2 = "";

							verseNumber++;
						}
					}
				} else if (inPoemSection) {
					if (line.startsWith("[")) {
						currentTitle = line.substring(1, line.indexOf("]"));
					} else if (line.startsWith("(")) {
						String[] parts = line.substring(1, line.length() - 1).split("\\.{3}");
						if (parts.length >= 1) {
							currentVerse1 = parts[0];
						}
						if (parts.length >= 2) {
							currentVerse2 = parts[1];
						}
					}
				}
			}
		} catch (IOException e) {
			logger.debug("importPoem func triggerd and exception");
			e.printStackTrace();
		}

	}

	private int getPoemID(String title) {
		int id = -1;
		String selectBookSQL = "SELECT id FROM poems WHERE title = ?";

		try (Connection connection = dbconnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(selectBookSQL)) {

			preparedStatement.setString(1, title);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					id = resultSet.getInt("id");
				}
			}

		} catch (SQLException e) {
			logger.debug("getPoemID func triggerd and exception");
			e.printStackTrace();
		}

		return id;
	}


	@Override
	public void updatePoem(String existingTitle, PoemTO poem) throws SQLException {
		try (Connection connection = dbconnection.getConnection()) {
			String updateBookSQL = "UPDATE poems SET title = ? WHERE title = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(updateBookSQL)) {
				preparedStatement.setString(1, poem.getTitle());

				preparedStatement.setString(2, existingTitle);
				preparedStatement.executeUpdate();

			}
		}
	}

	@Override
	public void deletePoem(String title) throws SQLException {
		try (Connection connection = dbconnection.getConnection()) {
			String deleteBookSQL = "DELETE FROM poems WHERE title = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(deleteBookSQL)) {
				preparedStatement.setString(1, title);
				preparedStatement.executeUpdate();
			}
		}
	}
		
	

	@Override
	public String goToPoem(String root) {
//		String root = rootTextField.getText().trim();

		try (Connection connection = dbconnection.getConnection()) {
			// Check if the root exists in the verse table
			if (findVerseHavingRoot(root)) {
				String query = "SELECT poems.title, verses.verse_number, verses.misra1, verses.misra2 " + "FROM poems "
						+ "JOIN verses ON poems.id = verses.poem_id " + "WHERE poems.id IN (SELECT DISTINCT poems.id "
						+ "FROM poems " + "JOIN verses ON poems.id = verses.poem_id "
						+ "JOIN tokens ON verses.verse_id = tokens.verse_id " + "WHERE tokens.token = ?)";

				try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
					preparedStatement.setString(1, root.toLowerCase());

					try (ResultSet resultSet = preparedStatement.executeQuery()) {
						StringBuilder result = new StringBuilder();
						String currentPoemTitle = "";
						while (resultSet.next()) {
							String poemTitle = resultSet.getString("title");
							int verseNumber = resultSet.getInt("verse_number");
							String misra1 = resultSet.getString("misra1");
							String misra2 = resultSet.getString("misra2");

							// Check if the poem title has changed
							if (!currentPoemTitle.equals(poemTitle)) {
								// If it has, append the new poem title
								currentPoemTitle = poemTitle;
								result.append("Poem: ").append(poemTitle).append("\n");
							}

							// Append the current verse information
							result.append("Verse ").append(verseNumber).append(": ").append(misra1).append(" ")
									.append(misra2).append("\n");
						}

						if (result.length() == 0) {

							System.out.println("No verses found for the given root.");
						} else {
//							verseTextArea.setText(result.toString());
							return result.toString();
						}
					}
				}
			} else {

				System.out.println("Root does not exist in the verse table.");
			}
		} catch (Exception e) {
			logger.debug("goToPoem func triggerd an exception");
			e.printStackTrace();

			System.out.println("Error occurred. Check console for details.");
		}
		System.out.println("Go to poem completed for root: " + root);
		return "---";
	}
	
	
	private boolean findVerseHavingRoot(String root) {

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
