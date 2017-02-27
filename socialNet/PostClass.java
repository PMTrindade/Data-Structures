package socialNet;

//Do another Constructor!
public class PostClass implements Post, java.io.Serializable {

	private static final long serialVersionUID = 0L;
	
	private String title, text, photoURL; //Variáveis primitivas da classe, o título, o texto e o URL de uma foto relacionada com o post.
	
	public PostClass(String title, String text, String photoURL) {
		this.title = title;
		this.text = text;
		this.photoURL = photoURL;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getText() {
		return text;
	}
	
	public String getPhotoURL() {
		return photoURL;
	}

}
