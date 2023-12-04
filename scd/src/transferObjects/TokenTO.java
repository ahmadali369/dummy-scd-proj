package transferObjects;

public class TokenTO {

	String token; 
	int verse_id; 
//	int root_id;
	String pos;
	
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getVerse_id() {
		return verse_id;
	}
	public void setVerse_id(int verse_id) {
		this.verse_id = verse_id;
	}
//	public int getRoot_id() {
//		return root_id;
//	}
//	public void setRoot_id(int root_id) {
//		this.root_id = root_id;
//	}
	public TokenTO(String token, int verse_id, String pos) {
		super();
		this.token = token;
		this.verse_id = verse_id;

		this.pos = pos; 
	}
	
	
	
	
}
