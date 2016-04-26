package Server;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerMainClass {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = new ServerSocket(10000);
		int i = 1;

		try {
			while (true) {
				Connection c = new Connection(serverSocket.accept(), i++);
				c.start();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			serverSocket.close();
		}
	}

}
