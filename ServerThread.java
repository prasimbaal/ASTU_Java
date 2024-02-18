package p2pChat;

import java.net.*;
import java.io.*;
import java.util.*;

public class ServerThread{
	int port_no = 4999;
	ServerSocket server;
	BufferedReader bfRead;
	BufferedWriter bfWrite;

	public ServerThread(){
		try{
			this.server = new ServerSocket(port_no);
		}catch(IOException e){
			System.out.println("Client disconnected!");
			e.printStackTrace();

		}

	}

	private void listen(){
		while(true){ //until program is terminated
			try{
				//wait for connection
				Socket socket = server.accept();
				System.out.println("Client succesfully conncected. ");

				//start new client Handler thread
				Thread clientThr = new Thread(new ClientThread(socket));
				clientThr.start();
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("Client disconnected!");
		}
	}
}

	public static void main(String[] args) {
		new ServerThread().listen();
	}

	class ClientThread implements Runnable {

	//list of clients and their data	
	private ArrayList<ClientThread> clientList = new ArrayList<>();	
	private Socket socket;
	private String userName;
	private BufferedReader bfRead;
	private BufferedWriter bfWrite;


	public ClientThread(Socket socket){
		try{
			this.socket = socket;
			

			bfWrite = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bfRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// this.userName = bfRead.readLine();
		
		}catch(IOException e){
			e.printStackTrace();
			
		}

	}

	@Override
	public void run(){
		InputStream in;
		userName = GUIClient.username();
	    clientList.add(this); //add this client to array list of users

		System.out.println(this.userName + " Username");
		try{
			in = socket.getInputStream();
			int bytRead;
			StringBuilder strMsg = new StringBuilder();
			String msg;
			//bfRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while((bytRead = in.read()) != -1){
				strMsg.append((char)bytRead);
				System.out.print((char)bytRead);
			}

			msg = strMsg.toString();
			strMsg.setLength(0);
		}catch(IOException e){
			//e.printStackTrace();
			System.out.println("Client disconnected!");
		
		}
	}

//	public String broadCast(String message){
//		for()
//	}

}
}