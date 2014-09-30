import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Client {

	public final static int PORT_NUMBER = 13267;
	public final static String SERVER = "127.0.0.1";
	public final static String WORKING_DIR = System.getProperty("user.dir");
	public final static String FILE = WORKING_DIR + "\\data-downloaded.txt";
	public final static int FILE_SIZE = 6022386;

	public static void main(String[] args) throws Throwable {
		int bytesRead;
		int current = 0;
		FileOutputStream fileOutputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		Socket socket = null;
		try {
			socket = new Socket(SERVER, PORT_NUMBER);
			System.out.println("Connecting...");

			// receive file
			byte[] mybytearray = new byte[FILE_SIZE];
			InputStream inputStream = socket.getInputStream();
			fileOutputStream = new FileOutputStream(FILE);
			bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
			bytesRead = inputStream.read(mybytearray, 0, mybytearray.length);
			current = bytesRead;

			do {
				bytesRead = inputStream.read(mybytearray, current, (mybytearray.length - current));
				if (bytesRead >= 0)
					current += bytesRead;
			} while (bytesRead > -1);

			bufferedOutputStream.write(mybytearray, 0, current);
			bufferedOutputStream.flush();
			System.out.println("File " + FILE + " downloaded (" + current + " bytes read)");
		} finally {
			if (fileOutputStream != null)
				fileOutputStream.close();
			if (bufferedOutputStream != null)
				bufferedOutputStream.close();
			if (socket != null)
				socket.close();
		}
	}

}