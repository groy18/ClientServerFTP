package Client;

/*
 * Partie client de la commande permettant d'enregistrer 
 * un fichier provenant du serveur en local
 * 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class CommandeGET {
	String nomFichier;
	static int port = 5000;
	
	public CommandeGET(String nomFichier) {
		
		this.nomFichier = nomFichier;
		
	}
	
	public void run() throws IOException {
		
		Socket socketGET = new Socket("localhost", port);	
		BufferedReader br = new BufferedReader(new InputStreamReader(socketGET.getInputStream()));
		PrintStream ps = new PrintStream(socketGET.getOutputStream());
		File file = new File("");
		
		//Chemin du dossier local dans lequel va �tre enregistr� la copie du fichier provenant
		// du dossier courant du serveur
		File fichier = new File(file.getAbsoluteFile() +"\\" + this.nomFichier+".txt");
			
		try {
			// Cr�ation du fichier en local s'il n'existe pas
			if(fichier.createNewFile()) {
				System.out.println("Le fichier a �t� cr�� en local");
				Main.ihm.JTexecutions.append("\n" + "Le fichier a �t� cr�� en local");
			}
			else {
				System.out.println("Le fichier existe d�j� en local");
				Main.ihm.JTexecutions.append("\n" + "Le fichier existe d�j� en local");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileReader fr = new FileReader(fichier);
		String line;
		PrintWriter pw = new PrintWriter(fichier, "UTF-8");
		
		// Boucle de r�ception du contenu du fichier provenant du dossier courant du serveur
		// -> On �crit dans le fichier tant qu'on re�oit des donn�es 
		while((line = br.readLine()) != null) {
			pw.println(line);
		}
		pw.close();
		fr.close();
		ps.close();
		port = port +1;
		socketGET.close();
		
	}
}
