import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class SimpleClient {
	

	static String authCode = "12345";
	static boolean userAuthenticated = false;

	public static void main(String[] args) throws IOException,
			InterruptedException {
		PrintWriter clientOutStream;
		BufferedReader clientInSocket;
		Socket clientSocket = null;
		final int SERVERPORT = 8111;
		InetAddress clientIPAddr = InetAddress.getByName("localhost");
		clientSocket = new Socket(clientIPAddr, SERVERPORT);
		clientOutStream = new PrintWriter(clientSocket.getOutputStream(), true);
		clientInSocket = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(
				System.in));
		String userInput;
		String line = null;

		InputStream ipStream = clientSocket.getInputStream();
		OutputStream opStream = clientSocket.getOutputStream();
		byte[] data = new byte[100000];
		String query = null;
		int count = 0;

		query = stdIn.readLine();
		// Read console and send data(use to send request for testing purpose)
		opStream.write(query.getBytes());

		// while ((query = stdIn.readLine())!= null) {
		while (true) {
			// Read console and send data(use to send request for testing
			// purpose)
			// opStream.write(query.getBytes());

			// read bytes sent by HOST
			count = ipStream.read(data);
			if (count != -1) {
				String message = new String(data, 0, count);
				System.out.println("RESPONSE : " + message);
			}
			// System.out.println("bytes of data read :" + count);
		}
		// System.out.println("Terminating client");
		// System.exit(-1);
	}

	static boolean authenticateUser(String code) {
		if (authCode.equals(code)) {
			return true;
		} else {
			return false;
		}
	}
}
