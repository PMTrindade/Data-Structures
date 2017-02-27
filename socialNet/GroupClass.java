package socialNet;

import dataStructures.*;

public class GroupClass implements Group, java.io.Serializable {

	private static final long serialVersionUID = 0L;
	
	private String groupName, description; //Variáveis primitivas da classe, o nome e a descrição do grupo.
	
	List<Post> gPosts; //Lista de posts presentes na zona de comunicação do grupo.
	
	OrderedDictionary<String, Contact> myMembers; //Árvore binária dos membros do grupo.
	
	public GroupClass(String groupName, String description) {
		this.groupName = groupName;
		this.description = description;
		gPosts = new DoublyLinkedList<Post>(); //Max size = 200
		myMembers = new BinarySearchTree<String, Contact>();
	}
	
	public String getGroupName() {
		return groupName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public boolean hasMember(String login) {
		return (myMembers.find(login) != null);
	}
	
	public void removeMember(String login) {
		myMembers.remove(login);
	}
	
	public void addMember(String login, Contact c) {
		myMembers.insert(login, c);
	}
	
	public boolean hasAnyMember() {
		return (!myMembers.isEmpty());
	}
	
	public Iterator<Entry<String, Contact>> listGroupAdherents() {
		return myMembers.iterator();
	}
	
	public void newPost(Post p) {
		gPosts.addFirst(p);
	}
	
	public boolean hasPosts() {
		return !gPosts.isEmpty();
	}
	
	public Iterator<Post> listGroupPosts() {
		return gPosts.iterator();
	}

}
