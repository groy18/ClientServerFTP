package Client;

/*
 * Partie client de la commande permettant d'enregister
 * un fichier du  client dans le dossier courant du serveur
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class CommandeSTOR {

	String nomFichier;
	static int port = 4000;
	
	public CommandeSTOR(String nomFichier) {
		
		this.nomFichier = nomFichier;
		
	}
	
	public void run() throws IOException {
		
		Socket socketSTOR = new Socket("localhost", port);
		OutputStream os = socketSTOR.getOutputStream();
		PrintStream ps = new PrintStream(os);
		BufferedReader br = new BufferedReader(new InputStreamReader(socketSTOR.getInputStream()));
		
		
		//Récupère le chemin du fichier à envoyer au dossier courant du serveur
		File fichier = new File("C:\\Users\\Gwendal Roy\\Desktop\\UBO\\Semestre 1\\Java\\WorkspaceJava\\Client\\" + this.nomFichier +".txt");

		try {
						
			if(!fichier.exists()) {
				System.out.println("Le fichier n'existe pas en local !");
				Main.ihm.JTexecutions.append("\n" + "Le fichier n'existe pas en local !");
			}
			else {
				
				
				BufferedReader f = new BufferedReader(new FileReader(fichier));
				
				String line;
				
				// Boucle d'envoi du contenu du fichier tant qu'on a pas atteint la fin de ce dernier
				while((line = f.readLine()) != null) {
					ps.println(line);
				}
				ps.close();
				br.close();
				f.close();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		port = port + 1;
		socketSTOR.close();
	}
	
}
