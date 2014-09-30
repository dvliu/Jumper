import java.net.ServerSocket;



public class Shutdown {
	
	public final static int PORT_NUMBER = 13267;

	public static void main(String[] args) throws Throwable {	
		ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
		serverSocket.bind(null);
		serverSocket.close();
	}

}
