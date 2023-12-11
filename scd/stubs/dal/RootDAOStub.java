
package dal;

import dal.interfaces.IRootDAO;
import transferObjects.RootTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RootDAOStub implements IRootDAO {

	private static final List<RootTO> rootsDatabase = new ArrayList<>();

	@Override
	public void insertRoot(RootTO root) throws SQLException {

		rootsDatabase.add(root);
	}

	@Override
	public List<Map<String, Object>> getAllRoots() throws SQLException {

		List<Map<String, Object>> result = new ArrayList<>();
		for (RootTO root : rootsDatabase) {
			result.add(mapRootToMap(root));
		}
		return result;
	}

	@Override
	public void updateroot(String rootw, String id) {

		rootsDatabase.stream().filter(root -> String.valueOf(root.getRoot_id()).equals(id)).findFirst()
				.ifPresent(root -> root.setRoot(rootw));
	}

	@Override
	public void deleteroot(String id) {

		rootsDatabase.removeIf(root -> String.valueOf(root.getRoot_id()).equals(id));
	}

	@Override
	public List<Map<String, Object>> getAllRootsByVerseId(int verseId) throws SQLException {

		List<Map<String, Object>> result = new ArrayList<>();
		for (RootTO root : rootsDatabase) {
			if (root.getVerse_id() == verseId) {
				result.add(mapRootToMap(root));
			}
		}
		return result;
	}

	@Override
	public void updateStatus(int verse_id) throws SQLException {

		rootsDatabase.stream().filter(root -> root.getVerse_id() == verse_id)
				.forEach(root -> root.setStatus("Updated Status"));
	}

	@Override
	public void updateRootStatus(String selectedRoot, String selectedVerse) {

		rootsDatabase.stream().filter(
				root -> root.getRoot().equals(selectedRoot) && String.valueOf(root.getVerse_id()).equals(selectedVerse))
				.findFirst().ifPresent(root -> root.setStatus("Updated Status"));
	}

	@Override
	public int getVerseId(String selectedVerse) {

		return rootsDatabase.stream().filter(root -> String.valueOf(root.getVerse_id()).equals(selectedVerse))
				.findFirst().map(RootTO::getVerse_id).orElse(0);
	}

	private Map<String, Object> mapRootToMap(RootTO root) {
		Map<String, Object> rootMap = new HashMap<>();
		rootMap.put("id", root.getRoot_id());
		rootMap.put("verse_id", root.getVerse_id());
		rootMap.put("root", root.getRoot());
		rootMap.put("status", root.getStatus());
		return rootMap;
	}
}
