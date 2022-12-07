package Serveur;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.ClosedFileSystemException;

public class CommandePASS extends Commande {
	
	
	private User client;
	
	public CommandePASS(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
		// Le mot de passe est : abcd
		
		
		try
		{
			// Le fichier d'entrée
			File rep = new File("C:\\Users\\Gwendal Roy\\Desktop\\UBO\\Semestre 1\\Java\\WorkspaceJava\\User\\" + commandeArgs[1] + "\\pass.txt");
			// Créer l'objet File Reader
			FileReader fr = new FileReader(rep);  
			// Créer l'objet BufferedReader  			
			BufferedReader br = new BufferedReader(fr);  
			StringBuffer sb = new StringBuffer();    
			String line;
			while((line = br.readLine()) != null)
			{
				// ajoute la ligne au buffer
				sb.append(line);     
			}
			br.close();
			fr.close();    
			//System.out.println("Contenu du fichier: ");
			//System.out.println(sb.toString());  
			if(commandeArgs[0].toLowerCase().equals(sb.toString())) {
				Main.UserMap.get(commandeArgs[1]).pwOk = true;
				ps.println("1 Commande pass OK");
				ps.println("0 Vous êtes bien connecté sur notre serveur");
				Main.UserMap.get(commandeArgs[1]).repertoire = "C:\\Users\\Gwendal Roy\\Desktop\\UBO\\Semestre 1\\Java\\WorkspaceJava\\User\\" + commandeArgs[1];
				
			}
			else {
				ps.println("2 Le mode de passe est faux");
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		/*
		System.out.println(commandeArgs[1]);
		if(commandeArgs[0].toLowerCase().equals("abcd")) {
			CommandExecutor.pwOk = true;
			ps.println("1 Commande pass OK");
			ps.println("0 Vous êtes bien connecté sur notre serveur");

		}
		else {
			ps.println("2 Le mode de passe est faux");
		}
		*/
	}

}
