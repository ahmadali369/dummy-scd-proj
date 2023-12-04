package bll.classes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bll.interfaces.ITokenBLO;
import dal.interfaces.IDalFacade;
import transferObjects.RootTO;
import transferObjects.TokenTO;

public class TokenBLO implements ITokenBLO {

	public IDalFacade facadeDal;

	private static final Logger logger = LogManager.getLogger(TokenBLO.class);
	
	public TokenBLO(IDalFacade facadeDal) {
		this.facadeDal = facadeDal;
	}

	@Override
	public List<Map<String, Object>> getAllVersesAndGenerateTokensAndRootsList(int poem_id) {
		// TODO Auto-generated method stub
		try {

			List<Map<String, Object>> verses = facadeDal.getAllVerses(poem_id);
			for (Map<String, Object> verse : verses) {

				String misra1 = (String) verse.get("misra1");
				String misra2 = (String) verse.get("misra2");
				int verse_id = (int) verse.get("verseId");

				List<Map<String, Object>> tokens = generateTokens(misra1, misra2, verse_id);

				for (Map<String, Object> token : tokens) {

					String tokenValue = (String) token.get("token");

					String pos = (String) token.get("pos");
//					    System.out.println("pos----------------" + pos);

					int verseId = (int) token.get("verseId");
					int rootId = (int) token.get("rootId");

					TokenTO tokenn = new TokenTO(tokenValue, verseId, rootId, pos);
					insertToken(tokenn);
					RootTO root = new RootTO(verse_id, createRoot(tokenValue), "Auto");

					facadeDal.insertRoot(root);

					

				}

			}

			// ==============================

			return facadeDal.getAllVerses(poem_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.debug("getAllVerses func triggerd an exception");
			e.printStackTrace();
			
		}

		return null;
	}

	@Override
	public List<Map<String, Object>> getAllTokens(int verseId) {
		// TODO Auto-generated method stub
		try {
			return facadeDal.getAllTokens(verseId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.debug("getAllTokens func triggerd an exception");
			e.printStackTrace();
		}
		return null;
	}

	// ===============================

	@Override
	public void insertToken(TokenTO token) {
		// TODO Auto-generated method stub
		try {
			facadeDal.insertToken(token);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.debug("insertToken func triggerd an exception");
			e.printStackTrace();
		}

	}

	@Override
	public String createPos(String word) {

		List<net.oujda_nlp_team.entity.Result> pos = net.oujda_nlp_team.AlKhalil2Analyzer.getInstance()
				.processToken(word).getAllResults();

		if (!pos.isEmpty()) {
			return pos.get(0).getPartOfSpeech().toString();
		} else {

			return " ";
		}

	}

	public String createRoot(String word) {

		String root = net.oujda_nlp_team.AlKhalil2Analyzer.getInstance().processToken(word).getAllRootString();
		if (!root.isEmpty()) {
			return root;
		} else {
			return " ";
		}

	}

	@Override
	public List<Map<String, Object>> generateTokens(String misra1, String misra2, int verse_id) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> tokens = new ArrayList<>();

		String[] misra1Tok = misra1.split(" ");
		String[] misra2Tok = misra2.split(" ");

		for (String s : misra1Tok) {

			Map<String, Object> token = new HashMap<>();

			token.put("token", s);
			token.put("verseId", verse_id);
			token.put("rootId", 1);
			token.put("pos", createPos(s));

			tokens.add(token);

		}

		for (String s : misra2Tok) {

			Map<String, Object> token = new HashMap<>();

			token.put("token", s);
			token.put("verseId", verse_id);
			token.put("rootId", 1);
			token.put("pos", createPos(s));

			tokens.add(token);

		}

		return tokens;
	}

}
