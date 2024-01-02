import java.net.*;
import java.io.*;

public class Server {
	public static void main(String[] args) throws IOException{
		ServerSocket server = new ServerSocket(8888);
		System.out.print("Server: " + server);
		Socket client = server.accept();
		System.out.print("Client connected ");
	}

}
