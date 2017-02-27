package socialNet;

import dataStructures.*;

public class ContactClass implements Contact, java.io.Serializable {

	private static final long serialVersionUID = 0L;
	
	private String login, contactName, address, profession; //Variáveis primitivas da classe, o login, o nome, a localidade, a profissão 
	private int age; //e a idade do utilizador.
	
	List<Group> myGroups; //Lista dos grupos a que o utilizador aderiu.
	List<Post> posts; //Lista dos posts submetidos pelo utilizador.
	
	OrderedDictionary<String, Contact> friends; //Árvore binária dos amigos do utilizador.
	
	public ContactClass(String login, String contactName, int age, String address, String profession) {
		this.login = login;
		this.contactName = contactName;
		this.age = age;
		this.address = address;
		this.profession = profession;
		myGroups = new DoublyLinkedList<Group>(); //Max size = 10
		posts = new DoublyLinkedList<Post>(); //Max size = 200
		friends = new BinarySearchTree<String, Contact>();
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getContactName() {
		return contactName;
	}
	
	public int getAge() {
		return age;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getProfession() {
		return profession;
	}
	
	public boolean hasFriend(String login) {
		return (friends.find(login) != null);
	}
	
	public void addFriend(String login, Contact c) {
		friends.insert(login, c);
	}
	
	public void removeFriend(String login) {
		friends.remove(login);
	}
	
	public boolean hasAnyFriend() {
		return (!friends.isEmpty());
	}
	
	public Iterator<Entry<String, Contact>> listContactFriends() {
		return friends.iterator();
	}
	
	public boolean hasGroup(Group g) {
		return (myGroups.find(g) != -1);
	}
	
	public void leaveGroup(Group g) {
		myGroups.remove(g);
	}
	
	public void joinGroup(Group g) {
		myGroups.addFirst(g);
	}
	
	public boolean hasAnyGroup() {
		return (!myGroups.isEmpty());
	}
	
	public Iterator<Group> listContactGroups() {
		return myGroups.iterator();
	}
	
	public void newPost(Post p) {
		posts.addFirst(p);
	}
	
	public boolean hasPosts() {
		return (!posts.isEmpty());
	}
	
	public Iterator<Post> listContactPosts() {
		return posts.iterator();
	}

}
