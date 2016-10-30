import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

public class Proxy {
	/** Port for the proxy */
	private static int port;

	/** Socket for client connections */
	private static ServerSocket server;
	private static String URI = "";
	private static Socket clientSocket = null;
	private static Socket serverSocket = null;
	private static String host = "";
	private static URI uri;
	private static int size = 0;
	
	public static void main(String args[]) throws IOException{
		port = Integer.parseInt(args[0]);
		server = new ServerSocket(port);
		
		while (true) {
			try {
				byte[] byteArray = new byte[5120];
				clientSocket = server.accept();
				System.out.println("'Received a connection from: " + clientSocket);

				/** Read client's HTTP request **/
				InputStream clientInput = clientSocket.getInputStream();
				size = clientInput.read(byteArray);
				System.out.println("tttttttttttttttest "+ size + " client input stream size");
				
				String firstLine = new String(byteArray);
				System.out.println("tttttttttttttttest "+ firstLine + " content from byteArray");
				
				String[] tmp = firstLine.split(" ");
				String method = tmp[0];
				URI = tmp[1];
				String version = tmp[2];
								
			} 
			catch (IOException e) {
				System.out.println("Error reading request from client: " + e);
				/* Definitely cannot continue, so skip to next
				 * iteration of while loop. */
				continue;
			}
			
			try {
				uri = new URI(URI);
				
				System.out.println("ttttttttttest Parse URI here");
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			port = uri.getPort();
			System.out.println("ttttttttest Port number " + port  );
			if (port == -1) {
				port = 80;
			}
			host = uri.getHost();
			System.out.println("tttttttttest host number " + host);
			
			/** Check cache if file exists **
			File f = new File(URI);
			if (f.exists())
			{
				/** Read the file **
				byte[] fileArray;
				fileArray = Files.readAllBytes(file);
				
				/** generate appropriate respond headers and send the file contents **
				
			}
			else {*/
				try {
					
					byte[] clientBuffer = new byte[5120];
					byte[] serverBuffer = new byte[5120];
					/** connect to server and relay client's request **/
					serverSocket = new Socket(host, port);
					OutputStream serverOutput = serverSocket.getOutputStream();
					serverOutput.write(serverBuffer, 0, size);
					
					InputStream serverInput = serverSocket.getInputStream();
					OutputStream clientOutput = clientSocket.getOutputStream();
					size = serverInput.read(clientBuffer);
					
					while (size != -1) {
						clientOutput.write(clientBuffer, 0, size);
						clientOutput.flush();
						size = serverInput.read(clientBuffer);
					}
				
					serverSocket.close();
					clientSocket.close();
					serverInput.close();
					clientOutput.close();
					
					
					/** Send respose to client **/
					
				}
				catch (UnknownHostException e) {
					System.out.println("ERROR 502: Cannot reach server");
					clientSocket.close();
				}
			}
			
		}

	}