import java.net.*;
import java.io.*;

public class Server {
	public static void main(String[] args) throws IOException{
		ServerSocket server = new ServerSocket(4999);
		System.out.print("Server: " + server);
		Socket client = server.accept();

		System.out.print("Client connected ");

		InputStreamReader in =  new InputStreamReader(client.getInputStream());

		BufferedReader bf = new BufferedReader(in);
		String str = bf.readLine();
		System.out.println(str);
	}

}
