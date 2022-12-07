package Serveur;

public class User {

	String nom;
	String repertoire;
	public boolean userOk = false ;
	public boolean pwOk = false ;
	
	public User(String nom) {
		this.nom = nom;
	}
	
	public void setRepertoire(String nom, String rep) {
		repertoire = rep;
	}
	
	public String getRepertoire(String nom) {
		return this.repertoire;
	}
}
