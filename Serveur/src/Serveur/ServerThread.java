package Serveur;

/*
 * Partie du serveur permettant de gérer les threads
 * 
 */


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;


public class ServerThread extends Thread{
	private Socket socket;
	private ArrayList<ServerThread> threadlist; 
	private PrintWriter ps;

	
	public ServerThread(Socket socket, ArrayList<ServerThread> thread) {
		this.socket = socket;
		this.threadlist = thread;
	}
	
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream ps = new PrintStream(socket.getOutputStream());
		
		ps.println("1 Bienvenue ! ");
		ps.println("1 Serveur FTP Personnel.");
		ps.println("0 Authentification : ");
		
		String commande = "";
		String nom = "";
		Repertoire.repertoire = "";
		
		
		// Attente de reception de commandes et leur execution
		while(!(commande=br.readLine()).equals("exit")) {
			System.out.println(">> "+commande );
			if(!(commande.length() == 0)) {
				if(commande.charAt(0) == 'u') {
				
					if(commande.substring(0, 4).equals("user")) {
						User client = new User(commande.substring(5));
						Main.UserMap.put(commande.substring(5), client);
						nom = client.nom;
						
					}
				}
			}
			
			CommandExecutor.executeCommande(ps, commande + " " + Main.UserMap.get(nom).nom, Main.UserMap.get(nom).nom);
			
		}
		socket.close();
		}catch(Exception e) {
			System.out.println("Un client a été déconnecté");
		}
	}
}
