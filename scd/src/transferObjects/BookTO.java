package transferObjects;

public class BookTO {
	String title;
	String authorName;
	String authorDateOfBirth;
	String authorDateOfDeath;
	int totalPoems;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorDateOfBirth() {
		return authorDateOfBirth;
	}

	public void setAuthorDateOfBirth(String authorDateOfBirth) {
		this.authorDateOfBirth = authorDateOfBirth;
	}

	public String getAuthorDateOfDeath() {
		return authorDateOfDeath;
	}

	public void setAuthorDateOfDeath(String authorDateOfDeath) {
		this.authorDateOfDeath = authorDateOfDeath;
	}

	public int getTotalPoems() {
		return totalPoems;
	}

	public void setTotalPoems(int totalPoems) {
		this.totalPoems = totalPoems;
	}

}
