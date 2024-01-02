import java.net.*;
import java.io.*;
import java.util.*;

public class Client{
	
	public static void main(String[] args) throws IOException{
		Scanner input = new Scanner(System.in);
		
		Socket client = new Socket("localhost", 4999);
		System.out.print("Client message: " );

		String message = input.nextLine();
		
		PrintWriter out = new PrintWriter(client.getOutputStream());

		out.println(message);
		out.flush();
	}
}