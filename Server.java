import java.net.*;
import java.io.*;
import java.util.*;


public class Server {
	public static void main(String[] args) throws IOException{

		Scanner input = new Scanner(System.in);
		ServerSocket server = new ServerSocket(4999);
		// System.out.print("Server: " + server);
		Socket client = server.accept();

		System.out.println("Client connected ");

		//server
		InputStreamReader in =  new InputStreamReader(client.getInputStream());

		BufferedReader bf = new BufferedReader(in);
		String str = bf.readLine();
		System.out.println("Received from client1: " + str);

		//client
		System.out.print("Client2 says: ");
		String message = input.nextLine();
		PrintWriter out = new PrintWriter(client.getOutputStream());

		out.println(message);
		out.flush();
	}

}
