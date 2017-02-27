package socialNet;

public interface ContactValues {

	/**
	* Consulta o login do utilizador.
	* @return - o login do utilizador.
	*/
	String getLogin();
	
	/**
	* Consulta o nome do utilizador.
	* @return - o nome do utilizador.
	*/
	String getContactName();
	
	/**
	* Consulta a idade do utilizador.
	* @return - a idade do utilizador.
	*/
	int getAge();
	
	/**
	* Consulta a localidade do utilizador.
	* @return - a localidade do utilizador.
	*/
	String getAddress();
	
	/**
	* Consulta a profissão do utilizador.
	* @return - a profissão do utilizador.
	*/
	String getProfession();

}
