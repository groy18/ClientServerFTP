package Serveur;
import java.io.File;
import java.io.PrintStream;

public class CommandeUSER extends Commande {
	
	public CommandeUSER(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
		// Ce serveur accepte uniquement le user personne
		//System.out.println(commandeArgs[0]);
		
		File rep = new File("C:\\Users\\Gwendal Roy\\Desktop\\UBO\\Semestre 1\\Java\\WorkspaceJava\\User\\" + commandeArgs[0]);
		//System.out.println(commandeArgs[1]);
		// Vérifiez s'il existe.
		//System.out.println("Path exists? " + rep.exists()); 
		if (rep.exists()) { 
			Main.UserMap.get(commandeArgs[1]).userOk = true;
			ps.println("0 Commande user OK");
			
		}
		else {
			ps.println("2 Le user " + commandeArgs[0] + " n'existe pas");
		}
		}
		/*
		if(commandeArgs[0].toLowerCase().equals("personne")) {
			CommandExecutor.userOk = true;
			ps.println("0 Commande user OK");
		}
		else {
			ps.println("2 Le user " + commandeArgs[0] + " n'existe pas");
		}
		*/
		
	}

