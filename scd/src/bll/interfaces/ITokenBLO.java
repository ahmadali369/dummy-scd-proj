package bll.interfaces;

import java.util.List;
import java.util.Map;

import transferObjects.TokenTO;

public interface ITokenBLO {

	public List<Map<String, Object>> getAllVersesAndGenerateTokensAndRootsList(int poem_id);   //

	public void insertToken(TokenTO token);   

	public List<Map<String, Object>> getAllTokens(int verseId);

	public String createPos(String word);
	
	public void tokenRootProcessing(int poem_id); 

	public List<Map<String, Object>> generateTokens(String misra1, String misra2, int verse_id);

}
