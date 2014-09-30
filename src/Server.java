import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public final static int PORT_NUMBER = 13267;
	public final static String WORKING_DIR = System.getProperty("user.dir");
	public final static String FILE = WORKING_DIR + "\\data.txt";


	public static void main(String[] args) throws Throwable {
		FileInputStream fileInputStream = null;
		BufferedInputStream bufferedInputStream = null;
		OutputStream outputStream = null;
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(PORT_NUMBER);
			while (true) {
				System.out.println("Waiting...");
				try {
					socket = serverSocket.accept();
					System.out.println("Accepted connection : " + socket);
					// send file
					File myFile = new File(FILE);
					byte[] mybytearray = new byte[(int) myFile.length()];
					fileInputStream = new FileInputStream(myFile);
					bufferedInputStream = new BufferedInputStream(fileInputStream);
					bufferedInputStream.read(mybytearray, 0, mybytearray.length);
					outputStream = socket.getOutputStream();
					System.out.println("Sending " + FILE + "(" + mybytearray.length + " bytes)");
					outputStream.write(mybytearray, 0, mybytearray.length);
					outputStream.flush();
					System.out.println("Done");
				} finally {
					if (bufferedInputStream != null)
						bufferedInputStream.close();
					if (outputStream != null)
						outputStream.close();
					if (socket != null)
						socket.close();
				}
			}
		} finally {
			if (serverSocket != null)
				serverSocket.close();
		}
	}
}