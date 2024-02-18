package p2pChat;

import java.awt.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;


public class GUIClient extends JFrame implements ActionListener {

	Socket client=null;
	String serverAddr = "localhost";
	String username;
	PrintWriter out;
	//prinReader readIn;
	private BufferedWriter bfWrite;
	private BufferedReader bfRead;
	private ArrayList<GUIClient> clientGUI = new ArrayList<>();
	int serverPort = 4999;
	private JTextField tfUsername, tfRecieved, tfMessage;
	private JButton send, close, userbtn;
	

	public GUIClient(){
		try{	
			client = new Socket(serverAddr,serverPort);
			System.out.println("Client " + client);
			out = new PrintWriter(client.getOutputStream());
			clientGUI.add(this); //add new client into arraylist of clients
			
			out.println();
			out.flush();
		}catch(IOException e){
			e.printStackTrace();
		// }catch(UnknownHostException e){
		// 	e.printStackTrace();
		}

		Container cp = this.getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(new JLabel("Enter Your user name: "));
		tfUsername = new JTextField("Enter User name", 40);
		tfUsername.addActionListener(this);

		userbtn = new JButton("Username submit");
		userbtn.addActionListener(this);
		cp.add(tfUsername);


		send =  new JButton("SEND");
		send.addActionListener(this);

		close = new JButton("CLOSE");
		close.addActionListener(this);
		
		cp.add(userbtn); //username submition button
		cp.add(send);
		cp.add(close);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setTitle("Client page");
		this.setSize(500,400);
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e){
			Object obj = e.getSource();
			if(obj==userbtn){
				username = tfUsername.getText();
				out.print(username);
				out.flush();
				System.out.println(username);
				tfUsername.setText("");

			}
			else if(obj==send){
				String message = tfUsername.getText();
				if(message.equals("quit")){
					try{
						out.close();
						client.close();
						System.exit(0);
					}catch(IOException ex){ex.printStackTrace();}}
				else{
					out.print(message);
					out.flush();
					tfUsername.setText("");
				}
	}else{
		try{
			out.close();
			client.close();
			System.exit(0);
		}catch(IOException ex){ex.printStackTrace();}}
	}

public static void main(String[] args){
		new GUIClient();
	}
}