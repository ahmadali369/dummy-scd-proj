
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

	private static final List<PoemTO> poemsDatabase = new ArrayList<>();

	@Override
	public void savePoem(PoemTO poem) throws SQLException {

		poemsDatabase.add(poem);
	}

	@Override
	public void importPoem(int bookid, File file) throws SQLException {

	}

	@Override
	public void updatePoem(String existingTitle, PoemTO poem) throws SQLException {

		for (int i = 0; i < poemsDatabase.size(); i++) {
			PoemTO currentPoem = poemsDatabase.get(i);
			if (currentPoem.getTitle().equals(existingTitle)) {

				poemsDatabase.set(i, poem);
				break;
			}
		}
	}

	@Override
	public void deletePoem(String title) throws SQLException {

		poemsDatabase.removeIf(poem -> poem.getTitle().equals(title));
	}

	@Override
	public List<Map<String, Object>> getAllPoems(int bookid) throws SQLException {

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

		return new ArrayList<>();
	}
}
