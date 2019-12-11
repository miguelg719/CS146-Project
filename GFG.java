import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class GFG {

	private int nVerts;
	private ArrayList<LinkedList<Integer>> adjacencyList;
	private ArrayList<Profile> profiles;
	private Profile currentUser;

	/*
	 * Each index in the adjacencyList represents a person and corresponds to
	 * the indices of the "persons" ArrayList. The linked list held in each
	 * index represents the friends of that person, once again with each number
	 * in the linked lists representing friends of an index in the adjacency
	 * list.
	 */

	public GFG() {
		adjacencyList = new ArrayList<LinkedList<Integer>>(); // Create an
																// adjacency
																// list
		profiles = new ArrayList<Profile>(); // persons ArrayList (each person
											// corresponds to vertex)
	}

	public static void main(String[] args) {
		GFG graph = new GFG();

		// Add these pre-made users to the network
		graph.addUser("Jack", "My status is unrelated to my name being Jack", "");
		graph.addUser("John", "My status is unrelated to my name being John", "");
		graph.addUser("Bill", "My status is unrelated to my name being Bill", "");
		graph.addUser("Doe", "My status is unrelated to my name being Doe", "");
		graph.addUser("Dave", "My status is unrelated to my name being Dave", "");
		graph.addUser("Duck", "My status is unrelated to my name being Duck", "");
		graph.nVerts = 6;

		// Now, connect some of these users as friends
		graph.addEdge(0, 2);
		graph.addEdge(0, 3);
		graph.addEdge(0, 4);
		graph.addEdge(5, 2);
		graph.addEdge(5, 3);
		graph.addEdge(1, 2);
		graph.addEdge(4, 3);
		graph.addEdge(2, 3);

		System.out.println("Adj list = "
				+ Arrays.toString(graph.adjacencyList.toArray()));

		Scanner scan = new Scanner(System.in);
		int option = 0;

		// Deal with adding, removing, etc
		while (true) {

			System.out.println("Welcome to the social network!");
			System.out.println("These are your options: \n");
			System.out.println("1: Create a new profile");
			System.out.println("2: Delete your existing profile");
			System.out.println("3: Modify your existing profile: ");
			System.out.println("4: Search for other profiles");
			
			// These two parts haven't been implemented yet
			System.out.println("5: Add friends from the other profiles");
			System.out.println("6: View the friends of your friends");
			
			System.out.println("7: Print the social media graph");

			option = scan.nextInt();
			String bla = scan.nextLine();

			switch (option) {

			case 1:
				System.out.println("Please enter your name: ");
				String name = scan.nextLine();

				System.out.println("Please enter your current status (married, single, in a relationship, etc): ");
				String currentStatus = scan.nextLine();
				
				System.out.println("If you'd like to upload a picture, please input a link: ");
				String imgLink = scan.nextLine();
				graph.addUser(name, currentStatus, imgLink);
				break;

			case 2:
				graph.deleteCurrentUser();
				break;

			case 3:
				System.out.println("Modify your name (if you don't want to modify enter the same name): ");
				String modifiedName = scan.nextLine();

				System.out.println("Enter your new current status (married, single, in a relationship, etc): ");
				String modifiedStatus = scan.nextLine();
				
				System.out.println("If you'd like, enter a new image link: ");
				String modifiedImgLink = scan.nextLine();
				graph.modifyCurrentUser(modifiedName, modifiedStatus, modifiedImgLink);
				break;

			case 4:
				graph.searchForOtherUsers(graph);
				break;

			case 5:
				graph.addFriends(graph, scan);
				break;

			case 6:
				graph.viewFriendsOfFriends();
				break;
				
			case 7:
				graph.printGraph(graph);
				break;

			default:
				break;
			}
		}
	}
	
	public void printGraph(GFG graph) {
		
		for(int v = 0; v < profiles.size(); v++) {
			
			System.out.println("Adjacency list of vertex " + v);
			System.out.print("head");
			
			for(Integer pCrawl: graph.adjacencyList.get(v)) {
				
				System.out.print("-> " + pCrawl);
			}
			
			System.out.println("\n");
		}
	}

	public void addEdge(int src, int dest) {

		/*
		 * for(int i = adjacencyList.size(); i <= src; i++) {
		 * adjacencyList.add(new LinkedList<Integer>()); }
		 */

		adjacencyList.get(src).add(dest);
	}

	// Upon adding a user, you are automatically logged in as this new user
	public void addUser(String name, String currentStatus, String img) {

		// Add the person to social media network
		currentUser = new Profile(name, currentStatus, img);
		profiles.add(currentUser);

		adjacencyList.add(new LinkedList<Integer>());
		nVerts++;

		System.out.println("Welcome to the social network, "
				+ currentUser.getName() + "! You are the currently logged in user. \n");
	}

	public void deleteCurrentUser() {

		// If there is no user currently logged in (e.g. if a created account was deleted),
		// currentUser = null and you can't delete current user.
		if (currentUser != null) {
			
			String tempCurrentUserName = currentUser.getName();
			int currentUserIndex = profiles.indexOf(currentUser);

			for (int i = 0; i < adjacencyList.size(); i++) {

				for (int j = 0; j < adjacencyList.get(i).size(); j++) {

					// If the linked lists in adjacency list contain
					// currentUserIndex, remove it.
					// This entirely purges the current user from existence
					if (adjacencyList.get(i).get(j).equals(currentUserIndex)) {

						adjacencyList.get(i).remove(j);
						break;
					}
				}
			}

			adjacencyList.remove(currentUserIndex);
			profiles.remove(currentUser);
			currentUser = null;
			nVerts--;

			System.out.println("Your account ( " + tempCurrentUserName
					+ " ) has been successfully deleted!\n");
		}
		
		else {
			System.out.println("There is no currently logged in user, press 1 to create a profile\n");
		}
	}

	// Modify the profile of the current user if it exists
	public void modifyCurrentUser(String name, String currentStatus, String currentImg) {
		
		if(currentUser != null) {
			currentUser.setName(name);
			currentUser.setCurrentStatus(currentStatus);
			currentUser.setImg(currentImg);
		}
		
		else {
			System.out.println("There is no currently logged in user, so you can't modify a profile. Press 1 to create a profile\n");
		}
	}

	// Search for other users in the network
	public void searchForOtherUsers(GFG graph) {

		System.out.println("Others on the network are: \n");

		for (int i = 0; i < profiles.size(); i++) {

			// Print other users on network as long as they aren't current user
			if (i != profiles.indexOf(currentUser)) {
				System.out.println(profiles.get(i).getName()
						+ " with a status of \""
						+ profiles.get(i).getCurrentStatus() + "\"");
			}
		}

		System.out.println();
	}

	// Add friends to the current user
	public void addFriends(GFG graph, Scanner scan) {

	}

	// View friends of friends
	private void viewFriendsOfFriends() {

	}

	/*
	 * public ArrayList<Edge> kruskal(ArrayList<LinkedList<Integer>>
	 * adjacencyList, int nVerts) {
	 * 
	 * DisjSets ds = new DisjSets(nVerts);
	 * 
	 * for(int i = 0; i < nVerts; i++) { Collections.sort(adjacencyList.get(i));
	 * }
	 * 
	 * ArrayList<Edge> mst = new ArrayList<Edge>();
	 * 
	 * while (mst.size() != nVerts - 1) {
	 * 
	 * Edge e = adjacencyList.get.remove(); int uset = ds.find(e.getu()); int
	 * vset = ds.find(e.getv());
	 * 
	 * if (uset != vset) {
	 * 
	 * mst.add(e); ds.union(uset, vset); } }
	 * 
	 * int mstTotalWeight = 0; for (Edge e : mst) {
	 * 
	 * mstTotalWeight += e.getWeight(); }
	 * 
	 * return mst; }
	 */

}
