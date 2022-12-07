package Serveur;

/*
 * Commande permettant de se déplacer dans le serveur
 * 
 */

import java.io.File;
import java.io.PrintStream;

public class CommandeCD extends Commande {
	
	public CommandeCD(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
		
		
		String rep = "";
		File fileRep;
		//recuperation du repertoire courant
		if(commandeArgs[0].equals(".")) {
			ps.println(Main.UserMap.get(commandeArgs[1]).repertoire);
		}
		
		
		if(commandeArgs[0].equals("..")) {

			if(Main.UserMap.get(commandeArgs[1]).repertoire.equals("C:")) {
				ps.println(Main.UserMap.get(commandeArgs[1]).repertoire);
			}
			else {
			
		        String[] words = Main.UserMap.get(commandeArgs[1]).repertoire.split("\\\\");
		        //System.out.print(words[0]);
		        
		        for (int i = 0; i < words.length - 1; i++) {
		            rep += words[i] + "\\";
		            
		        }
		        rep = rep.substring(0, rep.length()-1);
		        Main.UserMap.get(commandeArgs[1]).repertoire = rep;
				ps.println(Main.UserMap.get(commandeArgs[1]).repertoire);
			}
		
		}
		
		if(!(commandeArgs[0].equals("")) && !(commandeArgs[0].equals("..")) && !(commandeArgs[0].equals("."))) {
			
			if(Main.UserMap.get(commandeArgs[1]).repertoire.equals("C:\\Users\\Gwendal Roy\\Desktop\\UBO\\Semestre 1\\Java\\WorkspaceJava\\User") && !(commandeArgs[0].equals(commandeArgs[1]))) {
				ps.println("Vous n'avez pas accès aux répertoires de cet utilisateurs");
			}
			else {
				rep = Main.UserMap.get(commandeArgs[1]).repertoire + "\\" + commandeArgs[0];
				fileRep = new File(rep);
				if (!(fileRep.exists())) { 
					ps.println("Chemin introuvable");
				}
				else{
					Main.UserMap.get(commandeArgs[1]).repertoire = rep;
					ps.println(Main.UserMap.get(commandeArgs[1]).repertoire);
				}
			}
		}

}
}
