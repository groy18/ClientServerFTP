package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import Client.IHM;

public class Main {
	
	
	static PrintStream ps;
	static IHM ihm;
	
	public static void main(String[] args) throws IOException {
				
		try {
			Socket socket = new Socket("localhost", 2121);
			OutputStream os = socket.getOutputStream();
			ps = new PrintStream(os);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Scanner scanner = new Scanner(System.in);
			String s ;
			String reponse = "";
			ihm = new IHM();
			reponse=br.readLine();
			System.out.println(reponse);
			ihm.JTexecutions.setText(reponse);
			reponse=br.readLine();
			System.out.println("\n"+reponse);
			ihm.JTexecutions.append("\n"+reponse);
			reponse=br.readLine();
			System.out.println(reponse);
			ihm.JTexecutions.append("\n"+reponse);
			ClientThread th = new ClientThread(socket);
			th.start();
			while(!(s=scanner.nextLine()).equals("exit")) {
				ps.println(s);
				
			}
			
			scanner.close();
			socket.close();
			}
			catch(Exception e) {
				System.out.println("error");
			}
	}
}
