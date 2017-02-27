package socialNet;

import dataStructures.*;

public interface Group extends java.io.Serializable, GroupValues {

	/**
	* Verifica se o utilizador representado pelo login recebido por argumento pertence ao grupo.
	* @param login - o login do utilizador.
	* @return - true se o utilizador pertencer ao grupo, false caso contr�rio.
	*/
	boolean hasMember(String login);
	
	/**
	* Remove o membro do grupo identificado por login.
	* @param login - o login do utilizador.
	*/
	void removeMember(String login);
	
	/**
	* Adiciona o utilizador ao grupo, sendo este representado pelo login e pelo objecto Contact, ambos recebidos por argumento.
	* @param login - o login do utilizador.
	* @param c - o objecto Contact.
	*/
	void addMember(String login, Contact c);
	
	/**
	* Verifica se o grupo tem membros.
	* @return - true se o grupo tiver membros, false caso contr�rio.
	*/
	boolean hasAnyMember();
	
	/**
	* Retorna um iterador da �rvore bin�ria de aderentes do grupo.
	* @return - o iterador da �rvore bin�ria de aderentes.
	*/
	Iterator<Entry<String, Contact>> listGroupAdherents();
	
	/**
	* Insere um novo post na lista de posts, recebido por argumento.
	* @param post - o objecto Post.
	*/
	void newPost(Post p);
	
	/**
	* Verifica se o grupo cont�m posts, ou seja, se a sua lista de posts n�o est� vazia.
	* @return - true se o grupo tiver posts, false caso contr�rio.
	*/
	boolean hasPosts();
	
	/**
	* Retorna um iterador da lista de posts do grupo.
	* @return - o iterador da lista de posts.
	*/
	Iterator<Post> listGroupPosts();

}
