import java.net.*;
import java.io.*;

public class Client{
	
	public static void main(String[] args) throws IOException{
		Socket client = new Socket("localhost", 8888);
		System.out.println("Client: "  + client);
	}
}