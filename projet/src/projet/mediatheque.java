package projet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;




public class mediatheque {
	
	static int id = 0; //id compteur
	static String fichierObject;
	static String line1; //pour recuperer 1ere line de terminal
	static String line2; //pour recuperer 2eme line de terminal
	static String type;  //fichier type
	static String taille; //fichier taille
    static fichier x[];    // pour accerder chaque objet de class, on utilise comme list
    static double[] listTaillekb;
    
    
    
   
    
	public static void terminalProcess() {
		
		try {
			
			// Java Runtime methods pour lire de terminal
			
			//Process p pour demande le commande ls à terminal
			
			Process p = Runtime.getRuntime().exec("ls /Users/cemdogan/Desktop/mediatheque_repertoire");//localisation de repertoire mediatheque
			// ces commandes est valid pour macox et linux, il faut les change pour windows
			BufferedReader in = new BufferedReader(
			                                new InputStreamReader(p.getInputStream()));
			
			
			x = new fichier[100];
			
			while(true) {
				
				id++; // Compteur pour fichier id 
				
				
				line1 = in.readLine();  //pour lire chaque line de terminal de process p.
				
				//Process p2 pour demande le commande du -h à terminal 
				Process p2 = Runtime.getRuntime().exec("du -h /Users/cemdogan/Desktop/mediatheque_repertoire/"+line1);
				BufferedReader in2 = new BufferedReader(
				                                new InputStreamReader(p2.getInputStream()));
				
				
				
				line2 = in2.readLine(); //pour lire chaque line de terminal de process p2.
				
				if(line2==null) {break;} //pour arreter,s'il n'y a pas de quelque choose dans terminal
				
				 
				taille = line2.substring(0, 4);// trouver l'endroit du esapce pour recuperer la taille du fichier
				
				int point = line1.indexOf("."); // trouver l'endroit du point pour recuperer le type du fichier
				
				type = line1.substring(point, line1.length());
						
				
				x[id] = new fichier(id,line1,type,taille);
			}
			
			
			} catch (IOException e){
			e.printStackTrace();
			}
		
			System.out.println("");
			System.out.println("						BİENVENUE VOTRE MEDİATHEQUE");	
			System.out.println("");
	}
	
	public static void afficherAvecTousInformation() {
		
		System.out.println("		--Afficher les fichiers avec tous information--");
		System.out.println("		-----------------------------------------------");
		
		// pour afficher tous les fichiers 
		//  x[]
		for(int i=1; i<id; i++) {
			System.out.println("		"+x[i].getId()+") "+x[i].getNom()+" # "+x[i].getType()
			+" # "+x[i].getTaille());
			}
		System.out.println("		-----------------------------------------------");
			
}
	
	public static void trier() {
		
		// Conversion de la taille de chaque fichier à partir de String à double comme kilobyte.
		//pour ça, on controle de troiseme index de string pour chaque fichier.
		
		for(int i=1; i<id; i++) {
		String t = x[i].getTaille() ;
		
		
		//Conversion de Gb
		if(t.indexOf("G") == 3) {
			
			double gbTokb = Double.parseDouble(t.substring(0,3));
			
			gbTokb=gbTokb*1024*1024; // converter gb a kb pour trier
			
			x[i].setTailleKb(gbTokb);
			
		}
		
		//Conversion de Mb
		if(t.indexOf("M") == 3) {
			
			double mgTokb = Double.parseDouble(t.substring(0,3));
			
			mgTokb=mgTokb*1024; // converter mb a kb pour trier
			
			x[i].setTailleKb(mgTokb);
			
		}
		
		//Conversion de Kb
		if(t.indexOf("K") == 3) {
			
			double kb = Double.parseDouble(t.substring(0,3));
			
			
			x[i].setTailleKb(kb);
			
		}		
		}
		
		// j'ai creé noveaux list pour ecrir et trier la tailleKb pour chaque fichier
		listTaillekb = new double[100];
		
			for(int i=1; i<id; i++) {
			double taillekb = x[i].getTailleKb() ;
			listTaillekb[i]=taillekb;		
		}
		
		
			
		//ici pour trier de chaque element de listTailleKb qui est présicé ci-dessus
		for(int x=1; x<id; x++) {
		
			
			for(int y=x+1; y<id; y++) {
				
				if(listTaillekb[y] < listTaillekb[x]) {
					
					double exchange = listTaillekb[x] ;
					listTaillekb[x]=listTaillekb[y];
					listTaillekb[y]=exchange;		
				}
			}
		}
		
	
	
		
	}
	
		
	public static void afficherTrierAvecTaille(){
		
		System.out.println("		--Afficher les fichiers avec les tailles--");
		System.out.println("		-----------------------------------------------");
		
		trier();
		
		
		for(int t=1; t<id; t++) {
			
			for(int z=1; z<id; z++) {
			
			if(listTaillekb[t] == x[z].getTailleKb()) {
				
				System.out.println("		"+x[z].getTaille()+"  "+x[z].getNom());
				}
			}
				
		}
		System.out.println("		-----------------------------------------------");
	}
	
	public static void afficherTrierAvecType() {
		System.out.println("		--Afficher les fichiers avec les Types--");
		System.out.println("		-----------------------------------------------");
		
		ArrayList<String> listType = new ArrayList<String>();
		
		for(int t=1; t<id; t++) {
			
			//ajout de type de fichier seullment un fois pour chaque type à nouveaux list
				if(!(listType.contains(x[t].getType()))) {
					
				listType.add(x[t].getType());
					}
		}
		
		
		//ici ci-desous, 
		for(int i=0; i<listType.size(); i++) {
			for(int y=1; y<id; y++) {	
								
				if(x[y].getType().equals(listType.get(i))){
					System.out.println("		"+x[y].getType().toUpperCase().substring(1,x[y].getType().length())+"		"+x[y].getNom());		
					}
				
				
			}
		}
		System.out.println("		-----------------------------------------------");
	}
	
	
	

}
