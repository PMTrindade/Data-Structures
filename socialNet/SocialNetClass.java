package socialNet;

import dataStructures.*;
import exceptions.*;

public class SocialNetClass implements SocialNet, java.io.Serializable {

	private static final long serialVersionUID = 0L;
	
	private static final int maxContactSize = 12000; //Tamanho máximo do dicionário de contactos.
	private static final int maxGroupSize = 3000; //Tamanho máximo do dicionário de grupos.
	
	Contact c; //Objecto contacto.
	Group g; //Objecto grupo.
	
	Dictionary<String, Contact> contacts; //Dicionário de contactos.
	Dictionary<String, Group> groups; //Dicionário de grupos.
	
	public SocialNetClass() {
		c = null;
		g = null;
		contacts = new SepChainHashTable<String, Contact>(maxContactSize);
		groups = new SepChainHashTable<String, Group>(maxGroupSize);
	}
	
	public void insertContact(String login, String contactName, int age, String address, String profession) throws ExistentContactException {
		if(contacts.find(login) != null)
			throw new ExistentContactException();
		
		c = new ContactClass(login, contactName, age, address, profession);
		contacts.insert(login, c);
	}
	
	public ContactValues getContact(String login) throws InexistentContactException {
		if(contacts.find(login) == null)
			throw new InexistentContactException();
		
		ContactValues cv = contacts.find(login);
		
		return cv;
	}
	
	public void insertFriendship(String login1, String login2) throws InexistentContactException, ExistentFriendshipException {
		if(contacts.find(login1) == null || contacts.find(login2) == null)
			throw new InexistentContactException();
		
		c = contacts.find(login1);
		
		if(login1.equalsIgnoreCase(login2))
			throw new ExistentFriendshipException();
		
		if(c.hasFriend(login2))
			throw new ExistentFriendshipException();
		
		c.addFriend(login2, contacts.find(login2));
		contacts.find(login2).addFriend(login1, c);
	}
	
	public void removeFriendship(String login1, String login2) throws InexistentContactException, SelfFriendException, InexistentFriendshipException {
		if(contacts.find(login1) == null || contacts.find(login2) == null)
			throw new InexistentContactException();
		
		c = contacts.find(login1);
		
		if(login1.equalsIgnoreCase(login2))
			throw new SelfFriendException();
		
		if(!c.hasFriend(login2))
			throw new InexistentFriendshipException();
		
		c.removeFriend(login2);
		contacts.find(login2).removeFriend(login1);
	}
	
	public void insertGroup(String groupName, String description) throws ExistentGroupException {
		if(groups.find(groupName) != null)
			throw new ExistentGroupException();
		
		g = new GroupClass(groupName, description);
		groups.insert(groupName, g);
	}
	
	public GroupValues getGroup(String groupName) throws InexistentGroupException {
		if(groups.find(groupName) == null)
			throw new InexistentGroupException();
		
		GroupValues gv = groups.find(groupName);
		
		return gv;
	}
	
	public void removeGroup(String groupName) throws InexistentGroupException {
		if(groups.find(groupName) == null)
			throw new InexistentGroupException();
		
		g = groups.find(groupName);
		
		if(g.hasAnyMember()) {
			Iterator<Entry<String, Contact>> it = g.listGroupAdherents();
			Contact c = null;
			while(it.hasNext()) {
				c = it.next().getValue();
				c.leaveGroup(g);
			}
		}
		
		groups.remove(groupName);
	}
	
	public void insertAdherent(String login, String groupName) throws InexistentContactException, InexistentGroupException, ExistentMembershipException {
		if(contacts.find(login) == null)
			throw new InexistentContactException();
		
		if(groups.find(groupName) == null)
			throw new InexistentGroupException();
		
		c = contacts.find(login);
		g = groups.find(groupName);
		
		if(c.hasGroup(g) && g.hasMember(login))
			throw new ExistentMembershipException();
		
		c.joinGroup(g);
		g.addMember(login, c);
	}
	
	public void removeAdherent(String login, String groupName) throws InexistentContactException, InexistentGroupException, InexistentMembershipException {
		if(contacts.find(login) == null)
			throw new InexistentContactException();
		
		if(groups.find(groupName) == null)
			throw new InexistentGroupException();
		
		c = contacts.find(login);
		g = groups.find(groupName);
		
		if(!c.hasGroup(g) || !g.hasMember(login))
			throw new InexistentMembershipException();
		
		c.leaveGroup(g);
		g.removeMember(login);
	}
	
	public void insertPost(String login, String title, String description, String photoURL) throws InexistentContactException {
		if(contacts.find(login) == null)
			throw new InexistentContactException();
		
		Post p = null;
		c = contacts.find(login);
		p = new PostClass(title, description, photoURL);
		
		if(c.hasAnyFriend()) {
			Iterator<Entry<String, Contact>> it = c.listContactFriends();
			Contact f = null;
			while(it.hasNext()) {
				f = it.next().getValue();
				f.newPost(p);
			}
		}
		
		if(c.hasAnyGroup()) {
			Iterator<Group> it = c.listContactGroups();
			Group g = null;
			while(it.hasNext()) {
				g = it.next();
				g.newPost(p);
			}
		}
		
		c.newPost(p);
	}
	
	public Iterator<Entry<String, Contact>> getFriends(String login) throws InexistentContactException, NoFriendsException {
		if(contacts.find(login) == null)
			throw new InexistentContactException();
		
		c = contacts.find(login);
		
		if(!c.hasAnyFriend())
			throw new NoFriendsException();
		
		return c.listContactFriends();
	}
	
	public Iterator<Entry<String, Contact>> getAdherents(String groupName) throws InexistentGroupException, EmptyGroupException {
		if(groups.find(groupName) == null)
			throw new InexistentGroupException();
		
		g = groups.find(groupName);
		
		if(!g.hasAnyMember())
			throw new EmptyGroupException();
		
		return g.listGroupAdherents();
	}
	
	public Iterator<Post> listContactPosts(String login1, String login2) throws InexistentContactException, NoPostsException, InexistentFriendshipException {
		if(contacts.find(login1) == null || contacts.find(login2) == null)
			throw new InexistentContactException();
		
		c = contacts.find(login1);
		
		if(!c.hasPosts())
			throw new NoPostsException();
		
		if(!login1.equalsIgnoreCase(login2)) {
			if(!c.hasFriend(login2))
				throw new InexistentFriendshipException();
		}
		
		return c.listContactPosts();
	}
	
	public Iterator<Post> listGroupPosts(String groupName, String login) throws InexistentGroupException, InexistentContactException, InexistentMembershipException, NoPostsException {
		if(groups.find(groupName) == null)
			throw new InexistentGroupException();
		
		if(contacts.find(login) == null)
			throw new InexistentContactException();
		
		g = groups.find(groupName);
		c = contacts.find(login);
		
		if(!g.hasMember(login) || !c.hasGroup(g))
			throw new InexistentMembershipException();
		
		if(!g.hasPosts())
			throw new NoPostsException();
		
		return g.listGroupPosts();
	}

}
