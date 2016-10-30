import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.zip.CRC32;
public class FileSender {

	private static String host;
	private static String sourcefile;
	private static String destination;
	private static InetSocketAddress addr;
	private static DatagramSocket sk;
	private static ByteBuffer ackBuffer;
	private static CRC32 crc = new CRC32();
	private static File f;
	private static DatagramPacket packet;
	private static DatagramPacket ackPacket;
	private static DataInputStream fileReader;
	private static boolean received = false;
	private static int numofBytes;
	private static int port;
	private static long checksum;
	private static byte seqNum;
	private static byte ackNum;
	private static byte firstPkt;
	private static byte isEOF;
	private static byte[] pathData;
	private static byte[] fileData = new byte[1000-15];
	private static byte[] packetData = new byte[1000];
	private static byte[] ackData = new byte[10];

		
	public static void main(String args[]) throws Exception{
		
		if (args.length != 4) {
			System.err.println("Usage: SimpleUDPReceiver <host> <port> <sourcefile> <destfile>");
			System.exit(-1);
		}
		
		// Set up for sending file
		host = args[0];
		port = Integer.parseInt(args[1]);
		sourcefile = args[2];
		destination = args[3];
		pathData = destination.getBytes();
		numofBytes = pathData.length;
		addr = new InetSocketAddress(host, port);				
		sk = new DatagramSocket();
		sk.setSoTimeout(5);		
		f = new File(sourcefile);
		seqNum = 0;	
		fileReader = new DataInputStream(new FileInputStream(f));	
		isEOF = 0;
		firstPkt = 1;		
		ackBuffer = ByteBuffer.wrap(ackData);
		
		//Sending first data package
		send(pathData, numofBytes);
		seqNum++;
		firstPkt = 0;
	
		while ((numofBytes = fileReader.read(fileData)) != -1) {
			System.out.println("package " + seqNum + ", size " + numofBytes);
			send(fileData, numofBytes);		
			// 1 byte covers values from -128 to 127 rewind to 0 when reach this point
			if(seqNum == 127) {
				seqNum = 0;
			} else {
				seqNum++;
			}
		}
		
		// Stop sending file
		isEOF = 1;		
		send(new byte[0], 0);
		
	}
	

	private static void send(byte[] data, int size) throws IOException {
		ByteBuffer dataBuffer = ByteBuffer.wrap(packetData);
		dataBuffer.clear();
		dataBuffer.putLong(0);
		dataBuffer.put(seqNum);
		dataBuffer.put(firstPkt);
		dataBuffer.put(isEOF);
		dataBuffer.putInt(size);
		dataBuffer.position(15);
		dataBuffer.put(data);	
		crc.reset();
		crc.update(packetData, 8, packetData.length - 8);
		checksum = crc.getValue();
		dataBuffer.rewind();
		dataBuffer.putLong(checksum);
		packet = new DatagramPacket(packetData, packetData.length, addr);
		while(true) {
			sk.send(packet);
			ackBuffer.clear();
			ackPacket = new DatagramPacket(ackData, 10);
			try {
				sk.receive(ackPacket);
				received = true;
			} catch (SocketTimeoutException e) {
				received = false;
			}		
			if (received) {
				ackBuffer.rewind();			
				crc.reset();
				crc.update(ackData, 8, ackData.length - 8);
				checksum = ackBuffer.getLong();
				ackNum = ackBuffer.get();
				if (ackNum == seqNum && crc.getValue() == checksum) {
					break;
				}
			}
		}
			
	}
	
	
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
}