package Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection extends Thread {

	private Socket socket;
	private int playerID;
	private String login;

	public Connection(Socket socket, int playerID) {
		this.socket = socket;
		this.playerID = playerID;
	}

	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println("POLACZONO");
			String input = in.readLine();
			while (!input.startsWith("LOGIN")) {
				out.println("Podaj swój login! w formacie : LOGIN <twoj login>");
				input = in.readLine();
				System.out.println("Wiadomość od gracza nr " + playerID + " :\n" + input);
			}
			this.login = input.substring(6, input.length());
			System.out.println(login);
			
			while(!input.equals("quit")){
				input = in.readLine();
				System.out.println("Wiadomość od gracza " + login + " :\n" + input);
			}
			// socket.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Socket getSocket() {
		return socket;
	}
}
