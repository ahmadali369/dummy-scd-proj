
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dal.interfaces.IVerseDAO;
import transferObjects.VerseTO;

public class VersesDAOStub implements IVerseDAO {

	private static final List<VerseTO> versesDatabase = new ArrayList<>();

	@Override
	public void saveVerse(VerseTO verse) throws SQLException {

		versesDatabase.add(verse);
	}

	@Override
	public void updateVerse(String existingMisra1, String existingMisra2, VerseTO verse) throws SQLException {

		for (int i = 0; i < versesDatabase.size(); i++) {
			VerseTO currentVerse = versesDatabase.get(i);
			if (currentVerse.getMisra1().equals(existingMisra1) && currentVerse.getMisra2().equals(existingMisra2)) {

				versesDatabase.set(i, verse);
				break;
			}
		}
	}

	@Override
	public void deleteVerse(String misra1, String misra2) throws SQLException {

		versesDatabase.removeIf(verse -> verse.getMisra1().equals(misra1) && verse.getMisra2().equals(misra2));
	}

	@Override
	public List<Map<String, Object>> getAllVerses(int poem_id) throws SQLException {

		List<Map<String, Object>> result = new ArrayList<>();
		for (VerseTO verse : versesDatabase) {
			Map<String, Object> verseMap = new HashMap<>();
			verseMap.put("misra1", verse.getMisra1());
			verseMap.put("misra2", verse.getMisra2());
			verseMap.put("poem_id", verse.getPoem_id());
			result.add(verseMap);
		}
		return result;
	}
}
