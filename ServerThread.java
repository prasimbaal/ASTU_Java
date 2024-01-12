import java.net.*;
import java.io.*;

public class ServerThread{
	int port_no = 4999;
	ServerSocket server;
	BufferedReader bfRead;
	BufferedWriter bfWrite;

	public ServerThread(){
		try{
			this.server = new ServerSocket(port_no);
		}catch(IOException e){
			e.printStackTrace();
		}

	}

	private void listen(){
		while(true){ //until program is terminated
			try{
				//wait for connection
				Socket socket = server.accept();
				System.out.println("Client succesfully conncected. ");

				//start new client thread
				new ClientThread(socket).start();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}

	public static void main(String[] args) {
		new ServerThread().listen();
	}

	class ClientThread extends Thread{

	Socket socket;
	//BufferedReader bfRead;


	public ClientThread(Socket socket){
		this.socket = socket;
	}

	@Override
	public void run(){
		InputStream in;
		try{
			in = socket.getInputStream();
			int bytRead;
			//bfRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while((bytRead = in.read()) != -1){
				System.out.print((char)bytRead);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
}