package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class ServerMainClass {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = new ServerSocket(10000);
		int i = 0;
		Connection[] ct = new Connection[3];
		try {
			while (i != 3) {
				Connection c = new Connection(serverSocket.accept(), i++);
				c.start();
				ct[i - 1] = c;
			}
			for(int j = 0; j < ct.length; j++){
				System.out.println("Klient : " + ct[j].getName());
				PrintWriter out = new PrintWriter(ct[j].getSocket().getOutputStream(), true);
				out.println("message");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			serverSocket.close();
		}
	}

}
