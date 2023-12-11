//package dal;
//
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Map;
//
//import dal.interfaces.IRootDAO;
//import transferObjects.RootTO;
//
//public class RootDAOStub implements IRootDAO{
//
//	@Override
//	public List getAllRoots() throws SQLException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
////	@Override
////	public void insertrootword(String root) throws SQLException {
////		// TODO Auto-generated method stub
////		
////	}
//
//	@Override
//	public void updateroot(String rootw, String id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteroot(String id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void insertRoot(RootTO root) throws SQLException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public List<Map<String, Object>> getAllRootsByVerseId(int verseId) throws SQLException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void updateStatus(int verse_id) throws SQLException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void updateRootStatus(String selectedRoot, String selectedVerse) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public int getVerseId(String selectedVerse) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//}

package dal;

import dal.interfaces.IRootDAO;
import transferObjects.RootTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RootDAOStub implements IRootDAO {

    // Simulate an in-memory database
    private static final List<RootTO> rootsDatabase = new ArrayList<>();

    @Override
    public void insertRoot(RootTO root) throws SQLException {
        // Simulate inserting a root into the database
        rootsDatabase.add(root);
    }

    @Override
    public List<Map<String, Object>> getAllRoots() throws SQLException {
        // Simulate retrieving all roots from the database
        List<Map<String, Object>> result = new ArrayList<>();
        for (RootTO root : rootsDatabase) {
            result.add(mapRootToMap(root));
        }
        return result;
    }

    @Override
    public void updateroot(String rootw, String id) {
        // Simulate updating a root in the database
        rootsDatabase.stream()
                .filter(root -> String.valueOf(root.getRoot_id()).equals(id))
                .findFirst()
                .ifPresent(root -> root.setRoot(rootw));
    }

    @Override
    public void deleteroot(String id) {
        // Simulate deleting a root from the database
        rootsDatabase.removeIf(root -> String.valueOf(root.getRoot_id()).equals(id));
    }

    @Override
    public List<Map<String, Object>> getAllRootsByVerseId(int verseId) throws SQLException {
        // Simulate retrieving all roots by verseId from the database
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
        // Simulate updating the status of roots based on verse_id
        rootsDatabase.stream()
                .filter(root -> root.getVerse_id() == verse_id)
                .forEach(root -> root.setStatus("Updated Status"));
    }

    @Override
    public void updateRootStatus(String selectedRoot, String selectedVerse) {
        // Simulate updating the status of a specific root based on selectedRoot and selectedVerse
        rootsDatabase.stream()
                .filter(root -> root.getRoot().equals(selectedRoot) && String.valueOf(root.getVerse_id()).equals(selectedVerse))
                .findFirst()
                .ifPresent(root -> root.setStatus("Updated Status"));
    }

    @Override
    public int getVerseId(String selectedVerse) {
        // Simulate retrieving the verseId based on the selectedVerse
        return rootsDatabase.stream()
                .filter(root -> String.valueOf(root.getVerse_id()).equals(selectedVerse))
                .findFirst()
                .map(RootTO::getVerse_id)
                .orElse(0);
    }

    // Helper method to map RootTO to a Map<String, Object>
    private Map<String, Object> mapRootToMap(RootTO root) {
        Map<String, Object> rootMap = new HashMap<>();
        rootMap.put("id", root.getRoot_id());
        rootMap.put("verse_id", root.getVerse_id());
        rootMap.put("root", root.getRoot());
        rootMap.put("status", root.getStatus());
        return rootMap;
    }
}


