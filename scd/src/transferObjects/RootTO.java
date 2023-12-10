package transferObjects;

public class RootTO {

	int id;
//	int token_id;
	int verse_id;

	String root;
	String status;
	

	public RootTO(int verse_id, String root, String status) {
		super();
//		this.token_id = token_id;
		this.verse_id = verse_id;
		this.root = root;
		this.status = status;
	}

	public int getRoot_id() {
		return id;
	}

	public void setRoot_id(int root_id) {
		this.id = root_id;
	}

//	public int getToken_id() {
//		return token_id;
//	}
//
//	public void setToken_id(int token_id) {
//		this.token_id = token_id;
//	}

	public int getVerse_id() {
		return verse_id;
	}

	public void setVerse_id(int verse_id) {
		this.verse_id = verse_id;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
