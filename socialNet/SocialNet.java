package socialNet;

import dataStructures.*;
import exceptions.*;

public interface SocialNet extends java.io.Serializable {

	/**
	 * Insere um utilizador no sistema, identificado por login, nome, idade, localidade a profissão.
	 * @param login - o login do utilizador.
	 * @param contactName - o nome do utilizador.
	 * @param age - a idade do utilizador.
	 * @param address - a localidade do utilizador.
	 * @param profession - a profissão do utilizador.
	 * @throws ExistentContactException - quando existe um utilizador com o mesmo login.
	 */
	void insertContact(String login, String contactName, int age, String address, String profession) throws ExistentContactException;
	
	/**
	 * Consulta os dados de um utilizador, identificado por login.
	 * @param login - o login do utilizador.
	 * @return - o objecto Contact.
	 * @throws InexistentContactException - quando o utilizador não existe.
	 */
	ContactValues getContact(String login) throws InexistentContactException;
	
	/**
	 * Estabelece uma relação de amizade entre os utilizadores idenficados pelos logins 1&2.
	 * @param login1 - o login do utilizador 1.
	 * @param login2 - o login do utilizador 2.
	 * @throws InexistentContactException - quando um dos utilizadores não existe.
	 * @throws ExistentFriendshipException - quando a relação de amizade já existe.
	 */
	void insertFriendship(String login1, String login2) throws InexistentContactException, ExistentFriendshipException;
	
	/**
	 * Remove uma relação de amizade entre os utilizadores identificados pelos logins 1&2.
	 * @param login1 - o login do utilizador 1.
	 * @param login2 - o login do utilizador 2.
	 * @throws InexistentContactException - quando um dos utilizadores não existe.
	 * @throws SelfFriendException - quando o amigo do utilizador é ele mesmo.
	 * @throws InexistentFriendshipException - quando a relação de amizade não existe.
	 */
	void removeFriendship(String login1, String login2) throws InexistentContactException, SelfFriendException, InexistentFriendshipException;
	
	/**
	 * Insere um grupo no sistema, identificado por nome e descrição.
	 * @param groupName - o nome do grupo.
	 * @param description - a descrição do grupo.
	 * @throws ExistentGroupException - quando existe um grupo com o mesmo nome.
	 */
	void insertGroup(String groupName, String description) throws ExistentGroupException;
	
	/**
	 * Consulta os dados de um grupo, identificado por nome.
	 * @param groupName - o nome do grupo.
	 * @return - o objecto Group.
	 * @throws InexistentGroupException - quando o grupo não existe.
	 */
	GroupValues getGroup(String groupName) throws InexistentGroupException;
	
	/**
	 * Remove o grupo identificado pelo nome.
	 * @param groupName - o nome do grupo.
	 * @throws InexistentGroupException - quando o grupo não existe.
	 */
	void removeGroup(String groupName) throws InexistentGroupException;
	
	/**
	 * Estabelece uma aderência do utilizador identificado por login ao grupo identificado por nome.
	 * @param login - o login do utilizador.
	 * @param groupName - o nome do grupo.
	 * @throws InexistentContactException - quando o utilizador não existe.
	 * @throws InexistentGroupException - quando o grupo não existe.
	 * @throws ExistentMembershipException - quando o utilizador já pertence ao grupo.
	 */
	void insertAdherent(String login, String groupName) throws InexistentContactException, InexistentGroupException, ExistentMembershipException;
	
	/**
	 * Remove a aderência do utilizador identificado por login ao grupo identificado por nome.
	 * @param login - o login do utilizador.
	 * @param groupName - o nome do grupo.
	 * @throws InexistentContactException - quando o utilizador não existe.
	 * @throws InexistentGroupException - quando o grupo não existe.
	 * @throws InexistentMembershipException - quando o utilizador não pertence ao grupo.
	 */
	void removeAdherent(String login, String groupName) throws InexistentContactException, InexistentGroupException, InexistentMembershipException;
	
	/**
	 * Insere um novo post, associado ao utilizador (identificado por login), identificado por título,
	 * descrição e pelo URL de uma foto que lhe está associada.
	 * @param login - o login do utilizador.
	 * @param title - o título do post.
	 * @param description - a descrição do post.
	 * @param photoURL - o URL da foto.
	 * @throws InexistentContactException - quando o utilizador não existe.
	 */
	void insertPost(String login, String title, String description, String photoURL) throws InexistentContactException;
	
	/**
	 * Retorna um iterador de amigos do utilizador identificado por login.
	 * @param login - o login do utilizador.
	 * @return - o iterador de amigos.
	 * @throws InexistentContactException - quando o utilizador não existe.
	 * @throws NoFriendsException - quando o utilizador não tem amigos.
	 */
	Iterator<Entry<String, Contact>> getFriends(String login) throws InexistentContactException, NoFriendsException;
	
	/**
	 * Retorna um iterador de aderentes do grupo identificado por nome.
	 * @param groupName - o nome do grupo.
	 * @return - o iterador de aderentes.
	 * @throws InexistentGroupException - quando o grupo não existe.
	 * @throws EmptyGroupException - quando o grupo está vazio (não tem membros).
	 */
	Iterator<Entry<String, Contact>> getAdherents(String groupName) throws InexistentGroupException, EmptyGroupException;
	
	/**
	 * Retorna um iterador de posts submetidos pelo utilizador identificado pelo login 2, na zona de comunicação do utilizador identificado pelo login 1.
	 * @param login1 - o login do utilizador.
	 * @param login2 - o login do utilizador.
	 * @return - o iterador de posts.
	 * @throws InexistentContactException - quando o utilizador não existe.
	 * @throws NoPostsException - quando o utilizador não tem posts.
	 * @throws InexistentFriendshipException - quando a relação de amizade não existe.
	 */
	Iterator<Post> listContactPosts(String login1, String login2) throws InexistentContactException, NoPostsException, InexistentFriendshipException;
	
	/**
	 * Retorna um iterador de posts do grupo identificado por nome, submetidos pelo utilizador identificado por login.
	 * @param groupName - o nome do grupo.
	 * @param login - o login do utilizador.
	 * @return - o iterador de posts.
	 * @throws InexistentGroupException - quando o grupo não existe.
	 * @throws InexistentContactException - quando o utilizador não existe.
	 * @throws InexistentMembershipException - quando o utilizador não pertence ao grupo.
	 * @throws NoPostsException - quando o grupo não tem posts.
	 */
	Iterator<Post> listGroupPosts(String groupName, String login) throws InexistentGroupException, InexistentContactException, InexistentMembershipException, NoPostsException;

}
