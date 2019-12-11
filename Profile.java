public class Profile implements Comparable<Profile>{

	private String name;
	private String currentStatus;
	private String img;

	public Profile(String name, String currentStatus, String img) {
		this.name = name;
		this.currentStatus = currentStatus;
		this.img = img;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getCurrentStatus() {
		return this.currentStatus;
	}
	
	public String getImg() {
		return this.img;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	
	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public int compareTo(Profile p) {
		return name.compareTo(p.getName());
	}
}
