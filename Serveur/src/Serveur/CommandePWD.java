package Serveur;

/*
 * Commande permettant d'afficher le chemin du dossier courant
 * 
 */

import java.io.File;
import java.io.PrintStream;

public class CommandePWD extends Commande {
	
	public CommandePWD(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
		
		//Récupère le chemin du dossier courant
		File file = new File(Main.UserMap.get(commandeArgs[0]).repertoire);
		String s = file.getAbsoluteFile().toString();
		ps.println(s);
	}

}
