package Serveur;

/*
 * Partie serveur de la commande permettant d'enregistrer
 * un fichier du client dans le dossier courant du serveur
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class CommandeSTOR extends Commande {
	
	static int port = 4000;
	
	public CommandeSTOR(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() throws UnknownHostException, IOException {
		
		ServerSocket serverSocketSTOR = new ServerSocket(port);
		ps.println("Le serveur est pr�t");
		ps.println("stor " + commandeArgs[0]);
		Socket socket = serverSocketSTOR.accept();
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		File file = new File("");
		
		//Chemin du dossier courant dans lequel va �tre cr�� la copie du fichier provenant du client
		File fichier = new File(Main.UserMap.get(commandeArgs[1]).repertoire +"\\" + commandeArgs[0]+".txt");
			
		try {
			
			//Cr�ation du fichier dans le dossier courant s'il n'existe pas
			if(fichier.createNewFile()) {
				ps.println("Le fichier a �t� cr�� dans le dossier courant");
			}
			else {
				ps.println("Le fichier existe d�j� dans le dossier courant");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		PrintStream ps = new PrintStream(socket.getOutputStream());
		FileReader fr = new FileReader(fichier);
		String line;
		PrintWriter pw = new PrintWriter(fichier, "UTF-8");
		
		// Boucle de r�ception du contenu du fichier provenant du client
		// -> On �crit dans le fichier du dossier courant tant qu'on re�oit des donn�es 
		while((line = br.readLine()) != null) {
			pw.println(line);
		}
		pw.close();
		fr.close();
		ps.close();
		port = port + 1;
		socket.close();
	}

}
