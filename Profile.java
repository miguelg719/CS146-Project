import java.util.LinkedList;

public class Profile {
	
	String name;
	String status;
	String img;
	LinkedList<String> friends;
	
	public Profile(String name, String status) {
		this.name = name;
		this.status = status;
		this.img = null;	
	}
	
	public Profile(String name, String status, String img) {
		this.name = name;
		this.status = status;
		this.img = img;	
	}
	
	
	
	
}
