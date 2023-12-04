package transferObjects;

public class VerseTO {
	
	private int verse_id;
//	private int verseNumber;
	private String misra1;
	private String misra2;
	private int poem_id; 
	
	
	public int getPoem_id() {
		return poem_id;
	}
	public void setPoem_id(int poem_id) {
		this.poem_id = poem_id;
	}
	public int getId() {
		return verse_id;
	}
	public void setId(int id) {
		this.verse_id = id;
	}
//	public int getVerseNumber() {
//		return verseNumber;
//	}
//	public void setVerseNumber(int verseNumber) {
//		this.verseNumber = verseNumber;
//	}
	public String getMisra1() {
		return misra1;
	}
	public void setMisra1(String misra1) {
		this.misra1 = misra1;
	}
	public String getMisra2() {
		return misra2;
	}
	public void setMisra2(String misra2) {
		this.misra2 = misra2;
	}
	
	
}
