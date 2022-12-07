package Client;

/*
 * Partie du client permettant de g�rer les threads 
 * 
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread extends Thread {
	private Socket socket;
	private BufferedReader br;
	private PrintStream ps;
	
	public ClientThread(Socket socket) throws IOException {
		this.socket = socket;
		this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.ps = new PrintStream(socket.getOutputStream());
	}
	
	public void run()
	{
		try {
		String reponse = "";
		String s = "";
		while(!(reponse.equals("exit"))) {
			reponse = br.readLine();
			System.out.println(reponse);
			Main.ihm.JTexecutions.append("\n"+reponse);
			if(reponse.contentEquals("0 Vous �tes bien connect� sur notre serveur")){
				Main.ihm.connecte=true;
			}
			//Si le serveur envoi qu'il est pr�t pour l'ex�cution des commandes GET et STOR
			if(reponse.contentEquals("Le serveur est pr�t")) {
				s = br.readLine();
				if(s.split(" ")[0].equals("get")) {
					System.out.println("-> Ex�cution de la commande get");
					Main.ihm.JTexecutions.append("\n"+ "-> Ex�cution de la commande get");
					(new CommandeGET(s.substring(4))).run();
				}
				
				if(s.split(" ")[0].equals("stor")) {
					System.out.println("-> Ex�cution de la commande stor");
					Main.ihm.JTexecutions.append("\n"+ "-> Ex�cution de la commande stor");
					(new CommandeSTOR(s.substring(5))).run();
				}
			}
			
		}
		}
		catch(Exception e) {
			System.out.println("D�connexion du client");
			
		}
	}
}
