package projet;

public class fichier extends mediatheque{
	 int id;
	 String nom;
	 String type;
	 String taille;
	 double tailleKb;
	

	public fichier(int id,String nom, String type, String taille) {
		
		this.id=id;
		this.nom = nom;
		this.type = type;
		this.taille = taille;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getTaille() {
		return taille;
	}


	public void setTaille(String taille) {
		this.taille = taille;
	}


	public double getTailleKb() {
		return tailleKb;
	}


	public void setTailleKb(double tailleKb) {
		this.tailleKb = tailleKb;
	}
	

}
