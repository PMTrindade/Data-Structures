package socialNet;

import dataStructures.*;

public interface Contact extends java.io.Serializable, ContactValues {

	/**
	* Verifica se o utilizador tem o contacto identificado pelo login recebido por argumento no seu conjunto de amigos.
	* @param login - o login do contacto.
	* @return - true se os utilizadores forem amigos, false caso contr�rio.
	*/
	boolean hasFriend(String login);
	
	/**
	* Adiciona um amigo, representado pelo login e pelo objecto Contact, ambos recebidos por argumento.
	* @param login - o login do contacto.
	* @param c - o objecto Contact.
	*/
	void addFriend(String login, Contact c);
	
	/**
	* Remove um amigo do conjunto, identificado pelo login recebido por argumento.
	* @param login - o login do contacto.
	*/
	void removeFriend(String login);
	
	/**
	 * Verifica se o utilizador tem amigos, ou seja, se a �rvore bin�ria que representa o conjunto de amigos est� ou n�o vazia.
	 * @return - true se o utilizador tiver amigos, false caso contr�rio.
	 */
	boolean hasAnyFriend();
	
	/**
	* Retorna um iterador da �rvore bin�ria de amigos do utilizador.
	* @return - o iterador da �rvore bin�ria de amigos.
	*/
	Iterator<Entry<String, Contact>> listContactFriends();
	
	/**
	* Verifica se o utilizador pertence ao grupo recebido por argumento.
	* @param g - o objecto grupo.
	* @return - true se o utilizador pertencer ao grupo, false caso contr�rio.
	*/
	boolean hasGroup(Group g);
	
	/**
	* Remove a ader�ncia do utilizador ao grupo recebido por argumento.
	* @param g - o objecto grupo.
	*/
	void leaveGroup(Group g);
	
	/**
	* O utilizador adere ao grupo recebido por argumento.
	* @param g - o objecto Group.
	*/
	void joinGroup(Group g);
	
	/**
	 * Verifica se o utilizador pertence a um grupo.
	 * @return - true se o utilizador pertencer a um grupo, false caso contr�rio.
	 */
	boolean hasAnyGroup();
	
	/**
	* Retorna um iterador da lista de grupos do utilizador.
	* @return - o iterador da lista de grupos.
	*/
	Iterator<Group> listContactGroups();
	
	/**
	* Insere um novo post na lista de posts, recebido por argumento.
	* @param post - o objecto Post.
	*/
	void newPost(Post p);
	
	/**
	* Verifica se o utilizador cont�m posts, ou seja, se a sua lista de posts n�o est� vazia.
	* @return - true se o utilizador tiver posts, false caso contr�rio.
	*/
	boolean hasPosts();
	
	/**
	* Retorna um iterador da lista de posts do utilizador.
	* @return - o iterador da lista de posts.
	*/
	Iterator<Post> listContactPosts();

}
