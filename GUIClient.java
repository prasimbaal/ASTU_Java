import java.awt.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;


public class GUIClient extends JFrame implements ActionListener {

	Socket client=null;
	String serverAddr = "localhost";
	PrintWriter out;
	int serverPort = 4999;
	private JTextField tf;
	private JButton send, close;
	Scanner input;

	public GUIClient(){
		input = new Scanner(System.in);
		try{

			client = new Socket(serverAddr,serverPort);
			System.out.println("Client " + client);
			out = new PrintWriter(client.getOutputStream());
			System.out.print("Your Message: ");
			//String str = input.nextLine();
			out.println();
			out.flush();
		}catch(IOException e){
			e.printStackTrace();
		// }catch(UnknownHostException e){
		// 	e.printStackTrace();
		}

		Container cp = this.getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(new JLabel("Enter Your Message: "));
		tf = new JTextField(40);
		tf.addActionListener(this);
		cp.add(tf);

		send =  new JButton("SEND");
		send.addActionListener(this);

		close = new JButton("CLOSE");
		close.addActionListener(this);
		cp.add(send);
		cp.add(close);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setTitle("Client page");
		this.setSize(200,300);
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e){
			Object obj = e.getSource();
			if(obj==send){
				String message = tf.getText();
				if(message.equals("quit")){
					try{
						out.close();
						client.close();
						System.exit(0);
					}catch(IOException ex){ex.printStackTrace();}}
				else{
					out.print(message);
					out.flush();
					tf.setText("");
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