import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import dataStructures.*;
import socialNet.*;
import exceptions.*;

/**
 * @author Pedro Trindade Nº 41661 e Paulo Martins Nº 41982
 */

public class Main {
	
	//Comandos utilizados pelo utilizador.
	private static final String IC = "IC";
	private static final String CC = "CC";
	private static final String IA = "IA";
	private static final String RA = "RA";
	private static final String IG = "IG";
	private static final String CG = "CG";
	private static final String RG = "RG";
	private static final String ID = "ID";
	private static final String RD = "RD";
	private static final String IP = "IP";
	private static final String LA = "LA";
	private static final String LD = "LD";
	private static final String LC = "LC";
	private static final String LG = "LG";

	//Mensagens de erro apresentadas ao utilizador.
	private static final String EXISTENT_CONTACT = "Existencia de contacto referido.";
	private static final String INEXISTENT_CONTACT = "Inexistencia de contacto referido.";
	private static final String EXISTENT_FRIENDSHIP = "Existencia de amizade referida.";
	private static final String INEXISTENT_FRIENDSHIP = "Inexistencia de amizade referida.";
	private static final String CANT_REMOVE_FRIENDSHIP = "Amizade nao pode ser removida.";
	private static final String EXISTENT_GROUP = "Existencia de grupo referido.";
	private static final String INEXISTENT_GROUP = "Inexistencia de grupo referido.";
	private static final String EXISTENT_MEMBERSHIP = "Existencia de aderencia referida.";
	private static final String INEXISTENT_MEMBERSHIP = "Inexistencia de aderencia referida.";
	private static final String NO_FRIENDS = "Contacto nao tem amigos.";
	private static final String EMPTY_GROUP = "Grupo nao tem aderentes.";
	private static final String NO_ACCESS = "Contacto nao tem permissao de leitura de posts.";
	private static final String NO_CONTACT_POSTS = "Contacto nao tem posts.";
	private static final String NO_GROUP_POSTS = "Grupo nao tem posts.";

	//Mensagens de sucesso apresentadas ao utilizador.
	private static final String INSERT_CONTACT = "Insercao de contacto com sucesso.";
	private static final String INSERT_FRIENDSHIP = "Insercao de amizade com sucesso.";
	private static final String REMOVE_FRIENDSHIP = "Remocao de amizade com sucesso.";
	private static final String INSERT_GROUP = "Insercao de grupo com sucesso.";
	private static final String REMOVE_GROUP = "Remocao de grupo com sucesso.";
	private static final String INSERT_ADHERENT = "Insercao de aderencia a grupo com sucesso.";
	private static final String REMOVE_ADHERENT = "Remocao de aderencia com sucesso.";
	private static final String INSERT_POST = "Insercao de post com sucesso.";

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		File file = new File("data.txt");
		
		SocialNet sn = load(file);
		
		//Interpretador de comandos.
		do {
			String commands = in.next().toUpperCase();
			if(commands.equals(IC))
				insertContact(in, sn);
			else if(commands.equals(CC))
				consultContact(in, sn);
			else if(commands.equals(IA))
				insertFriendship(in, sn);
			else if(commands.equals(RA))
				removeFriendship(in, sn);
			else if(commands.equals(IG))
				insertGroup(in, sn);
			else if(commands.equals(CG))
				consultGroup(in, sn);
			else if(commands.equals(RG))
				removeGroup(in, sn);
			else if(commands.equals(ID))
				insertAdherent(in, sn);
			else if(commands.equals(RD))
				removeAdherent(in, sn);
			else if(commands.equals(IP))
				insertPost(in, sn);
			else if(commands.equals(LA))
				listFriends(in, sn);
			else if(commands.equals(LD))
				listAdherents(in, sn);
			else if(commands.equals(LC))
				listContactPosts(in, sn);
			else if(commands.equals(LG))
				listGroupPosts(in, sn);
			System.out.println();
			} while(in.hasNext());
		save(sn, file);
		}
		
		private static SocialNet load(File file) {
			SocialNet sn = null;
			
			try {
				FileInputStream fileIn = new FileInputStream(file);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				sn = (SocialNet) in.readObject();
				in.close();
				fileIn.close();
			} catch (IOException i) {
				sn = new SocialNetClass();
			} catch (ClassNotFoundException c) {
				c.printStackTrace();
			}
			
			return sn;
		}
		
		private static void save(SocialNet sn, File file) {
			try {
				FileOutputStream fileOut = new FileOutputStream(file);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(sn);
				out.close();
				fileOut.close();
			} catch (IOException i) {
				i.printStackTrace();
			}
		}
		
		//Command methods
		private static void insertContact(Scanner in, SocialNet sn) {
			String login = in.next().trim().toUpperCase();
			String contactName = in.nextLine().trim();
			int age = in.nextInt();
			String address = in.nextLine().trim();
			String profession = in.nextLine();
			in.nextLine();
			
			try {
				sn.insertContact(login, contactName, age, address, profession);
				System.out.println(INSERT_CONTACT);
			}
			catch (ExistentContactException ex) {
				System.out.println(EXISTENT_CONTACT);
			}
		}
		
		private static void consultContact(Scanner in, SocialNet sn) {
			String login = in.nextLine().trim().toUpperCase();
			in.nextLine();
			
			try {
				ContactValues c = sn.getContact(login);
				System.out.println(c.getLogin().toUpperCase() + " " + c.getContactName().toUpperCase() + " " + c.getAge());
				System.out.println(c.getAddress().toUpperCase() + " " + c.getProfession().toUpperCase());
			}
			catch (InexistentContactException ex) {
				System.out.println(INEXISTENT_CONTACT);
			}
		}
		
		private static void insertFriendship(Scanner in, SocialNet sn) {
			String login1 = in.next().trim().toUpperCase();
			String login2 = in.nextLine().trim().toUpperCase();
			in.nextLine();
			
			try {
				sn.insertFriendship(login1, login2);
				System.out.println(INSERT_FRIENDSHIP);
			}
			catch (InexistentContactException ex) {
				System.out.println(INEXISTENT_CONTACT);
			}
			catch (ExistentFriendshipException ex) {
				System.out.println(EXISTENT_FRIENDSHIP);
			}
		}
		
		private static void removeFriendship(Scanner in, SocialNet sn) {
			String login1 = in.next().trim().toUpperCase();
			String login2 = in.nextLine().trim().toUpperCase();
			in.nextLine();
			
			try {
				sn.removeFriendship(login1, login2);
				System.out.println(REMOVE_FRIENDSHIP);
			}
			catch (InexistentContactException ex) {
				System.out.println(INEXISTENT_CONTACT);
			}
			catch (SelfFriendException ex) {
				System.out.println(CANT_REMOVE_FRIENDSHIP);
			}
			catch (InexistentFriendshipException ex) {
				System.out.println(INEXISTENT_FRIENDSHIP);
			}
		}
		
		private static void insertGroup(Scanner in, SocialNet sn) {
			String groupName = in.nextLine().trim().toUpperCase();
			String description = in.nextLine();
			in.nextLine();
			
			try {
				sn.insertGroup(groupName, description);
				System.out.println(INSERT_GROUP);
			}
			catch (ExistentGroupException ex) {
				System.out.println(EXISTENT_GROUP);
			}
		}
		
		private static void consultGroup(Scanner in, SocialNet sn) {
			String groupName = in.nextLine().trim().toUpperCase();
			in.nextLine();
			
			try {
				GroupValues g = sn.getGroup(groupName);
				System.out.println(g.getGroupName().toUpperCase());
				System.out.println(g.getDescription().toUpperCase());
			}
			catch (InexistentGroupException ex) {
				System.out.println(INEXISTENT_GROUP);
			}
		}
		
		private static void removeGroup(Scanner in, SocialNet sn) {
			String groupName = in.nextLine().trim().toUpperCase();
			in.nextLine();
			
			try {
				sn.removeGroup(groupName);
				System.out.println(REMOVE_GROUP);
			}
			catch (InexistentGroupException ex) {
				System.out.println(INEXISTENT_GROUP);
			}
		}
		
		private static void insertAdherent(Scanner in, SocialNet sn) {
			String login = in.next().trim().toUpperCase();
			String groupName = in.nextLine().trim().toUpperCase();
			in.nextLine();
			
			try {
				sn.insertAdherent(login, groupName);
				System.out.println(INSERT_ADHERENT);
			}
			catch (InexistentContactException ex) {
				System.out.println(INEXISTENT_CONTACT);
			}
			catch (InexistentGroupException ex) {
				System.out.println(INEXISTENT_GROUP);
			}
			catch (ExistentMembershipException ex) {
				System.out.println(EXISTENT_MEMBERSHIP);
			}
		}
		
		private static void removeAdherent(Scanner in, SocialNet sn) {
			String login = in.next().trim().toUpperCase();
			String groupName = in.nextLine().trim().toUpperCase();
			in.nextLine();
			
			try {
				sn.removeAdherent(login, groupName);
				System.out.println(REMOVE_ADHERENT);
			}
			catch (InexistentContactException ex) {
				System.out.println(INEXISTENT_CONTACT);
			}
			catch (InexistentGroupException ex) {
				System.out.println(INEXISTENT_GROUP);
			}
			catch (InexistentMembershipException ex) {
				System.out.println(INEXISTENT_MEMBERSHIP);
			}
		}
		
		private static void insertPost(Scanner in, SocialNet sn) {
			String login = in.nextLine().trim().toUpperCase();
			String title = in.nextLine();
			String description = in.nextLine();
			String photoURL = in.nextLine();
			in.nextLine();
			
			try {
				sn.insertPost(login, title, description, photoURL);
				System.out.println(INSERT_POST);
			}
			catch (InexistentContactException ex) {
				System.out.println(INEXISTENT_CONTACT);
			}
		}
		
		private static void listFriends(Scanner in, SocialNet sn) {
			String login = in.nextLine().trim().toUpperCase();
			in.nextLine();
			
			try {
				Iterator<Entry<String, Contact>> it = sn.getFriends(login);
				ContactValues f = null;
				while(it.hasNext()) {
					f = it.next().getValue();
					System.out.println(f.getLogin().toUpperCase() + " " + f.getContactName().toUpperCase());
				}
			}
			catch (InexistentContactException ex) {
				System.out.println(INEXISTENT_CONTACT);
			}
			catch (NoFriendsException ex) {
				System.out.println(NO_FRIENDS);
			}
		}
		
		private static void listAdherents(Scanner in, SocialNet sn) {
			String groupName = in.nextLine().trim().toUpperCase();
			in.nextLine();
			
			try {
				Iterator<Entry<String, Contact>> it = sn.getAdherents(groupName);
				ContactValues a = null;
				while(it.hasNext()) {
					a = it.next().getValue();
					System.out.println(a.getLogin().toUpperCase() + " " + a.getContactName().toUpperCase());
				}
			}
			catch (InexistentGroupException ex) {
				System.out.println(INEXISTENT_GROUP);
			}
			catch (EmptyGroupException ex) {
				System.out.println(EMPTY_GROUP);
			}
		}
		
		private static void listContactPosts(Scanner in, SocialNet sn) {
			String login1 = in.next().trim().toUpperCase();
			String login2 = in.nextLine().trim().toUpperCase();
			in.nextLine();
			
			try {
				Iterator<Post> it = sn.listContactPosts(login1, login2);
				Post p = null;
				while(it.hasNext()) {
					p = it.next();
					System.out.println(p.getTitle().toUpperCase());
					System.out.println(p.getText().toUpperCase());
					System.out.println(p.getPhotoURL().toUpperCase());
					if(it.hasNext()) //O último post não necessita de imprimir esta linha extra.
					System.out.println();
				}
			}
			catch (InexistentContactException ex) {
				System.out.println(INEXISTENT_CONTACT);
			}
			catch (NoPostsException ex) {
				System.out.println(NO_CONTACT_POSTS);
			}
			catch (InexistentFriendshipException ex) {
				System.out.println(NO_ACCESS);
			}
		}
		
		private static void listGroupPosts(Scanner in, SocialNet sn) {
			String groupName = in.next().trim().toUpperCase();
			String login = in.nextLine().trim().toUpperCase();
			in.nextLine();
			
			try {
				Iterator<Post> it = sn.listGroupPosts(groupName, login);
				Post p = null;
				while(it.hasNext()) {
					p = it.next();
					System.out.println(p.getTitle().toUpperCase());
					System.out.println(p.getText().toUpperCase());
					System.out.println(p.getPhotoURL().toUpperCase());
					if(it.hasNext()) //O último post não necessita de imprimir esta linha extra.
					System.out.println();
				}
			}
			catch (InexistentGroupException ex) {
				System.out.println(INEXISTENT_GROUP);
			}
			catch (InexistentContactException ex) {
				System.out.println(INEXISTENT_CONTACT);
			}
			catch (InexistentMembershipException ex) {
				System.out.println(NO_ACCESS);
			}
			catch(NoPostsException ex) {
				System.out.println(NO_GROUP_POSTS);
			}
		}

}
