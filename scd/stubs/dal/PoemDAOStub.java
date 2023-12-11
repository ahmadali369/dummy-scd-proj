//package dal;
//
//import java.io.File;
//
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Map;
//
//import javax.swing.JFrame;
//
//import dal.interfaces.IPoemDAO;
//import transferObjects.PoemTO;
//import transferObjects.VerseTO;
//
//public class PoemDAOStub implements IPoemDAO{
//
//	@Override
//	public void savePoem(PoemTO poem) throws SQLException {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//
//	@Override
//	public void importPoem(int bookid, File file) throws SQLException {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//
//	@Override
//	public void updatePoem(String existingTitle, PoemTO poem) throws SQLException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deletePoem(String title) throws SQLException {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	@Override
//	public List<Map<String, Object>> getAllPoems(int bookid) throws SQLException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public List<Map<String, Object>> getPoemsByRoot(String root) throws SQLException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}



package dal;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dal.interfaces.IPoemDAO;
import transferObjects.PoemTO;

public class PoemDAOStub implements IPoemDAO {

    // Simulate an in-memory database
    private static final List<PoemTO> poemsDatabase = new ArrayList<>();

    @Override
    public void savePoem(PoemTO poem) throws SQLException {
        // Simulate saving a poem into the database
        poemsDatabase.add(poem);
    }

    @Override
    public void importPoem(int bookid, File file) throws SQLException {
        // Simulate importing a poem into the database
        // You can implement logic here based on your requirements
    }

    @Override
    public void updatePoem(String existingTitle, PoemTO poem) throws SQLException {
        // Simulate updating a poem in the database
        for (int i = 0; i < poemsDatabase.size(); i++) {
            PoemTO currentPoem = poemsDatabase.get(i);
            if (currentPoem.getTitle().equals(existingTitle)) {
                // Found the poem to update
                poemsDatabase.set(i, poem);
                break;
            }
        }
    }

    @Override
    public void deletePoem(String title) throws SQLException {
        // Simulate deleting a poem from the database
        poemsDatabase.removeIf(poem -> poem.getTitle().equals(title));
    }



    @Override
    public List<Map<String, Object>> getAllPoems(int bookid) throws SQLException {
        // Simulate retrieving all poems from the database
        List<Map<String, Object>> result = new ArrayList<>();
        for (PoemTO poem : poemsDatabase) {
            Map<String, Object> poemMap = new HashMap<>();
            poemMap.put("title", poem.getTitle());
            poemMap.put("bookId", poem.getBookId());
            poemMap.put("total_verses", poem.getTotal_verses());
            result.add(poemMap);
        }
        return result;
    }
    
    @Override
    public List<Map<String, Object>> getPoemsByRoot(String root) throws SQLException {
        // Simulate retrieving poems by root from the database
        // You can implement logic here based on your requirements
        return new ArrayList<>();
    }
}
