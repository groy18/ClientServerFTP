package Serveur;

import java.io.File;
import java.io.PrintStream;

public class CommandeMKDIR extends Commande{

	public CommandeMKDIR(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}
	
	public void execute() {
		
		//Récupère le chemin du dossier courant
		File file = new File(Main.UserMap.get(commandeArgs[1]).repertoire);
		String s = file.getAbsoluteFile().toString()+"\\";
		
		File folder = new File(s + commandeArgs[0]);
		
		//Créer le dossier dans le chemin courant s'il n'existe pas
		if(folder.mkdir()) {
			ps.println("Le répertoire " + commandeArgs[0] + " a été créé !");
		}
		else {
			ps.println("Le répertoire n'a pas pu être créé !");
		}
	}
	
	
}
