import java.util.*;

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
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to the Spartanbook!");
		System.out.println("To complete your profile, please fill in the following information:");
		System.out.println();
		System.out.println("What is your name?");
		String name = in.next();
		System.out.println("What is your current status? (Married, Single, In a relationship...)");
		String status = in.next();
		System.out.println("If you'd like to upload a picture, please input link here:");
		String img = in.next();
		System.out.println();

		System.out.println("Name: "+name+" \nStatus: "+status+" \nImage link: "+img);


		

	}
	
	
}
