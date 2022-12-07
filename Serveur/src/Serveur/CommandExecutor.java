package Serveur;
import java.io.IOException;
import java.io.PrintStream;
import java.net.UnknownHostException;

public class CommandExecutor {
	
	
	public static void executeCommande(PrintStream ps, String commande, String user) throws UnknownHostException, IOException {
		if(Main.UserMap.get(user).pwOk && Main.UserMap.get(user).userOk) {
			// Changer de repertoire. Un (..) permet de revenir au repertoire superieur
			if(commande.split(" ")[0].equals("cd")) (new CommandeCD(ps, commande)).execute();
	
			// Telecharger un fichier
			if(commande.split(" ")[0].equals("get")) (new CommandeGET(ps, commande)).execute();
			
			// Afficher la liste des fichiers et des dossiers du repertoire courant
			if(commande.split(" ")[0].equals("ls")) (new CommandeLS(ps, commande)).execute();
		
			// Afficher le repertoire courant
			if(commande.split(" ")[0].equals("pwd")) (new CommandePWD(ps, commande)).execute();
			
			// Envoyer (uploader) un fichier
			if(commande.split(" ")[0].equals("stor")) (new CommandeSTOR(ps, commande)).execute();
			
			if(commande.split(" ")[0].equals("mkdir")) (new CommandeMKDIR(ps, commande)).execute();
			
			if(commande.split(" ")[0].equals("rmdir")) (new CommandeRMDIR(ps, commande)).execute();
		}
		else {
			if(commande.split(" ")[0].equals("pass") || commande.split(" ")[0].equals("user")) {
				// Le mot de passe pour l'authentification
				if(commande.split(" ")[0].equals("pass")) (new CommandePASS(ps, commande)).execute();
	
				// Le login pour l'authentification
				if(commande.split(" ")[0].equals("user")) (new CommandeUSER(ps, commande)).execute();
			}
			else
				ps.println("2 Vous n'êtes pas connecté !");
		}
	}

}
