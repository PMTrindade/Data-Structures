package socialNet;

public interface Post extends java.io.Serializable {

	/**
	* Consulta o título do post.
	* @return - o título do post.
	*/
	String getTitle();
	
	/**
	* Consulta o texto do post.
	* @return - o texto do post.
	*/
	String getText();
	
	/**
	* Consulta o url de uma foto relacionada com o post.
	* @return - o url de uma foto relacionada com o post.
	*/
	String getPhotoURL();

}
