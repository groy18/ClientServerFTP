package Serveur;

import java.io.File;
import java.io.PrintStream;

public class CommandeRMDIR extends Commande{

	public CommandeRMDIR(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}
	
	public void execute() {
		
		//R�cup�re le chemin du dossier courant
		File file = new File(Main.UserMap.get(commandeArgs[1]).repertoire);
		String s = file.getAbsoluteFile().toString()+"\\";
		
		File folder = new File(s + commandeArgs[0]);
		
		//Supprime le dossier s'il est vide
		if(folder.delete()) {
			ps.println("Le r�pertoire " + commandeArgs[0] + " a �t� supprim� !");
		}
		else {
			ps.println("Le r�pertoire n'a pas pu �tre supprim� !");
		}
	}
}
