package Serveur;

/*
 * Partie serveur de la commande permettant d'enregistrer
 * un fichier provenant du serveur en local
 * 
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CommandeGET extends Commande {
	
	static int port = 5000;
	
	public CommandeGET(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() throws IOException {
		
		ServerSocket serverSocketGET = new ServerSocket(port);
		ps.println("Le serveur est prêt");
		ps.println("get " + commandeArgs[0]);
		Socket socket = serverSocketGET.accept();
		OutputStream os = socket.getOutputStream();
		PrintStream ps = new PrintStream(os);
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		//Récupère le chemin du fichier du dossier courant à envoyer au client
		File fichier = new File(Main.UserMap.get(commandeArgs[1]).repertoire + "\\" + commandeArgs[0] +".txt");

		try {
						
			if(!fichier.exists()) {
				ps.println("Le fichier n'existe pas dans le dossier courant !");
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
		socket.close();
		
	}

}
