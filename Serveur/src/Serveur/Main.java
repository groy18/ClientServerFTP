package Serveur;
/*
 * TP JAVA RIP
 * Main Serveur FTP
 * */

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main{

	public static Map<String, User> UserMap = new HashMap<String, User>();
	public static void main(String[] args){
		System.out.println("Le Serveur FTP");
		
		ArrayList<ServerThread> threadlist = new ArrayList<>();
		try(ServerSocket serveurFTP = new ServerSocket(2121)){
			while(true) {
				Socket socket = serveurFTP.accept();
				ServerThread serverThread = new ServerThread(socket, threadlist);
				
				threadlist.add(serverThread);
				serverThread.start();
			}
		} catch(Exception e) {
			System.out.println("Error main " + e.getStackTrace());
		}
		
		
	}
}