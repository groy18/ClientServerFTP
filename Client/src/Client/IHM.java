package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.*;
import Client.Main;

public class IHM extends JFrame implements ActionListener {

	protected JInternalFrame jif;
	
	protected JPanel p;
	protected JPanel pConnexion;
	protected JPanel pExecution;
	protected JPanel contentPane;
	protected JPanel pActions;
	protected JPanel pParametre;
	
	protected JLabel JLuser;
	protected JLabel JLmdp;
	protected JLabel JLparametre;
	
	protected JTextField JTuser;
	protected JTextField JTmdp;
	protected JTextArea JTexecutions;
	protected JTextArea JTparametre;
	
	protected JButton JBconnexion;
	protected JButton JBvider;
	protected JButton JBCd;
	protected JButton JBGet;
	protected JButton JBLs;
	protected JButton JBPwd;
	protected JButton JBStor;
	protected JButton JBMkdir;
	protected JButton JBRmdir;
	
	protected boolean connecte;
	
	public IHM() {
		
		connecte = false;
		
		// Initialisation des JPanels
		p = new JPanel();
		pConnexion = new JPanel();
		pExecution = new JPanel();
		pParametre = new JPanel();
		contentPane = (JPanel) this.getContentPane();
		pActions=new JPanel();
		
		
		// Initialisation des JLabels
		JLuser = new JLabel("Nom d'utilisateur :");
		JLmdp = new JLabel("Mot de passe :");
		JLparametre = new JLabel("Paramètre de la commande (cd, get, stor, mkdir ou rmdir) : ");

		// Initialisation des JTextFields
		JTuser = new JTextField(20);
		JTmdp = new JTextField(20); 
		JTexecutions = new JTextArea(20, 60);
		JTexecutions.setEditable(false);
		JTparametre = new JTextArea(1,10);

		
		
		// Initialisation des JButtons
		JBconnexion = new JButton("Connexion");
		JBvider = new JButton("Vider trace d'exécution");
		JBCd = new JButton("CD");
		JBGet = new JButton("GET");
		JBLs = new JButton("LS");
		JBPwd = new JButton("PWD");
		JBStor = new JButton("STOR");
		JBMkdir = new JButton("MKDIR");
		JBRmdir = new JButton("RMDIR");

		
		// Initilisation du JInternalFrame
		jif = new JInternalFrame();
		
		
		setTitle("Client/Serveur FTP");
		setSize(1000, 500);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	
		// Ajouts d'éléments sur le Panel Connexion
		
		pConnexion.add(JLuser);
		pConnexion.add(JTuser);
		pConnexion.add(JLmdp);
		pConnexion.add(JTmdp);
		pConnexion.add(JBconnexion);
		
		// Ajout des actions liées aux boutons 
		JBconnexion.addActionListener(this);
		JBvider.addActionListener(this);
		JBLs.addActionListener(this);
		JBPwd.addActionListener(this);
		JBCd.addActionListener(this);
		JBGet.addActionListener(this);
		JBStor.addActionListener(this);
		JBMkdir.addActionListener(this);
		JBRmdir.addActionListener(this);
		
		// Ajout d'élément sur le panel Execution
		pExecution.add(JTexecutions);
		pExecution.add(JBvider);
		
		// Ajouts d'éléments sur le panel Parametre
		pParametre.add(JLparametre);
		pParametre.add(JTparametre);
		
		// Ajouts d'éléments sur le panel Actions
		pActions.add(JBCd);
		pActions.add(JBGet);
		pActions.add(JBLs);
		pActions.add(JBPwd);
		pActions.add(JBStor);
		pActions.add(JBMkdir);
		pActions.add(JBRmdir);
		
		// Ajout d'éléments sur le panel Principal
		p.add(pConnexion);
		p.add(pExecution);
		p.add(pParametre);
		p.add(pActions);
		
		contentPane=(JPanel) getContentPane();
		contentPane.add(p);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
			// Action effectuée lorsque l'utilisateur clique sur le bouton de connexion
			if(source == JBconnexion) {
				
				// Affichage d'une boîte de dialogue si un utilisateur déjà connecté tente de se reconnecter
				if(connecte==true) {
					JOptionPane.showInternalMessageDialog(jif, "Vous êtes déjà connecté");
				}
				else {
					String user = JTuser.getText();
					String mdp = JTmdp.getText();
					
					// Affichage d'une boîte de dialogue si les champs nécessaires à la connexion sont vides
					if(user.isBlank()||mdp.isBlank()) {
						JOptionPane.showInternalMessageDialog(jif, "Veuillez entrer votre nom d'utilisateur et/ou mot de passe");
					}	
					else{
						Main.ps.println("user " + user);
						Main.ps.println("pass " + mdp);
					}
				}
				
			}
			
			// Action effectuée lorsque l'utilisateur clique sur le bouton de la commande "ls"
			else if(source == JBLs) {
				if(connecte==true) {
					Main.ps.println("ls");
				}
				// Affichage d'une boîte de dialogue si l'utilisateur n'est pas connecté et tente d'utiliser la commande		
				else {
					JOptionPane.showInternalMessageDialog(jif, "Vous devez être connecté pour utiliser cette commande");
				}
			}
			
			// Action effectuée lorsque l'utilisateur clique sur le bouton de la commande "pwd"
			else if(source == JBPwd) {
				
				if(connecte == true) {
					Main.ps.println("pwd");
				}
					
				else {
					JOptionPane.showInternalMessageDialog(jif, "Vous devez être connecté pour utiliser cette commande");
				}
			
			}
			
			// Action effectuée lorsque l'utilisateur clique sur le bouton de la commande "cd"
			else if(source == JBCd) {
					if(connecte==true) {
						String parametre = JTparametre.getText();
						// Affichage d'une boîte de dialogue si le champs nécessaire à l'exécution de la commande est vide
						if(parametre.isBlank()) {
							JOptionPane.showInternalMessageDialog(jif, "Paramètre vide");
						}
						else {
							Main.ps.println("cd " + parametre);
							JTparametre.setText("");
						}
					}
					else {
						JOptionPane.showInternalMessageDialog(jif, "Vous devez être connecté pour utiliser cette commande");
					}
			}
			
			// Action effectuée lorsque l'utilisateur clique sur le bouton de la commande "get"
			else if(source == JBGet) {
				
					if(connecte==true) {
						String parametre = JTparametre.getText();
						if(parametre.isBlank()) {
							JOptionPane.showInternalMessageDialog(jif, "Paramètre vide");
						}
						else{
							Main.ps.println("get " + parametre);
							JTparametre.setText("");
						}
					}
					else {
						JOptionPane.showInternalMessageDialog(jif, "Vous devez être connecté pour utiliser cette commande");
					}
					
					
				
			}
			
			// Action effectuée lorsque l'utilisateur clique sur le bouton de la commande "stor"
			else if(source == JBStor) {
				if(connecte==true) {
					String parametre = JTparametre.getText();
					if(parametre.isBlank()) {
						JOptionPane.showInternalMessageDialog(jif, "Paramètre vide");
					}
					else{
						Main.ps.println("stor " + parametre);
						JTparametre.setText("");
					}
				}
				else {
					JOptionPane.showInternalMessageDialog(jif, "Vous devez être connecté pour utiliser cette commande");
				}
			}
			
			// Action effectuée lorsque l'utilisateur clique sur le bouton de la commande "mkdir"
			else if(source == JBMkdir) {
				if(connecte==true) {
					String parametre = JTparametre.getText();
					if(parametre.isBlank()) {
						JOptionPane.showInternalMessageDialog(jif, "Paramètre vide");
					}
					else{
						Main.ps.println("mkdir " + parametre);
						JTparametre.setText("");
					}
				}
				else {
					JOptionPane.showInternalMessageDialog(jif, "Vous devez être connecté pour utiliser cette commande");
				}
			}
			
			// Action effectuée lorsque l'utilisateur clique sur le bouton de la commande "rmdir"
			else if(source == JBRmdir) {
				if(connecte==true) {

					String parametre = JTparametre.getText();
					if(parametre.isBlank()) {
						JOptionPane.showInternalMessageDialog(jif, "Paramètre vide");
					}
					else{
						Main.ps.println("rmdir " + parametre);
					}
				}
				else {
					JOptionPane.showInternalMessageDialog(jif, "Vous devez être connecté pour utiliser cette commande");
				}
				
			}
			
			// Action effectuée lorsque l'utilisateur clique sur le bouton pour supprimer les traces d'exécutions
			else if(source == JBvider) {
				JTexecutions.setText("");
			}
			
	}
	
	
}
