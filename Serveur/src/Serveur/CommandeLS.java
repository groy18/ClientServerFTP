package Serveur;

/*
 * Commande permettant d'afficher le contenu du répertoire courant
 * 
 */

import java.io.File;
import java.io.PrintStream;

public class CommandeLS extends Commande {
	
	public CommandeLS(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
		File dir  = new File(Main.UserMap.get(commandeArgs[0]).repertoire);
		  File[] liste = dir.listFiles();
		  for(File item : liste){
			  if(item.isFile())
			  { 
			  	ps.println("Nom du fichier : " + item.getName()); 
			  } 
			  else if(item.isDirectory())
			  {
				  ps.println("Nom du répertoire : " + item.getName()); 
			  } 
		  }
	}

}
