import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Client{

	public static void main(String[] args) throws IOException{
		Scanner input = new Scanner(System.in);
		
		Socket client = new Socket("localhost", 4999);
		System.out.println("Connected to server");
		
		//client
		System.out.print("Client1 sends: ");
		String message = input.nextLine();

		PrintWriter out = new PrintWriter(client.getOutputStream());

		out.println(message);
		out.flush();

		//server
		InputStreamReader in =  new InputStreamReader(client.getInputStream());

		BufferedReader bf = new BufferedReader(in);
		String str = bf.readLine();
		System.out.println("received from client2: "+str);

	}
}