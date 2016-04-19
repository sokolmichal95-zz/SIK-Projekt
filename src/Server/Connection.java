package Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection extends Thread {

	private Socket socket;
	private int connectionNumber;

	public Connection(Socket socket, int connectionNumber) {
		this.socket = socket;
		this.connectionNumber = connectionNumber;
	}

	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println("Gracz nr : " + connectionNumber + ".");
			String input = in.readLine();
			while (!input.equals("quit")) {
				input = in.readLine();
				System.out.println("Wiadomość od gracza nr " + connectionNumber + " :\n" + input);
				out.println("Received message");
			}
			socket.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
