import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.zip.CRC32;

public class FileReceiver {

	private static String filePath;
	private static File destination;
	private static FileOutputStream fos;
	private static SocketAddress addr;
	private static CRC32 crc = new CRC32();
	private static DatagramSocket sk;
	private static DatagramPacket dataPacket;
	private static int port;
	private static int size;
	private static byte actualNum = 0;
	private static byte firstPkt;
	private static byte isEOF;
	private static byte seqNum;
	private static long checksum;
	private static byte[] dataField;
	private static byte[] data = new byte[1000];
	private static byte[] ackData = new byte[10];
	



	
	
	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.err.println("Usage: SimpleUDPReceiver <port number>");
			System.exit(-1);
		}
		
		port = Integer.parseInt(args[0]);
		sk = new DatagramSocket(port);
		
		dataPacket = new DatagramPacket(data, data.length);
		ByteBuffer b = ByteBuffer.wrap(data);
		
		while(true)	{
			dataPacket.setLength(data.length);
			sk.receive(dataPacket);
			addr = dataPacket.getSocketAddress();
			b.rewind();
			checksum = b.getLong();
			crc.reset();
			crc.update(data, 8, dataPacket.getLength()- 8);
			seqNum = b.get();
			if (corrupted()){
				System.out.println("File corrupted");
				continue;
			}
			if (outofOrder()){
				System.out.println("Packages out of order");
				continue;
			}			
			firstPkt = b.get();		
			isEOF = b.get();
			size = b.getInt();
			b.position(15);
			dataField = new byte[size];
			b.get(dataField, 0, size);	
			if (isEOF == 1) {
				fos.close();
			}
			createFilePath();
			if(actualNum == 127) {
				actualNum = 0;
			} else {
				actualNum++;
			}
		}
	}
	
	private static void createFilePath() throws Exception{
		if (firstPkt == 1) {
			filePath = new String(dataField);
			destination = new File(filePath);
			fos = new FileOutputStream(destination);
			sk.send(createAck(actualNum));
		} else {
			fos.write(dataField);
			sk.send(createAck(actualNum));
		}
	}
	
	private static DatagramPacket createAck(byte actualNum) {	
		ByteBuffer ackDataBuffer = ByteBuffer.wrap(ackData);
		ackDataBuffer.clear();
		ackDataBuffer.putLong(0);
		ackDataBuffer.put(actualNum);
		crc.reset();
		crc.update(ackData, 8, ackData.length - 8);
		checksum = crc.getValue();
		ackDataBuffer.rewind();
		ackDataBuffer.putLong(checksum);
		return new DatagramPacket(ackData, ackData.length, addr);
	}
		
	private static boolean corrupted() throws IOException{
		if (crc.getValue() != checksum) {
			sk.send(createAck((byte)(actualNum - 1)));
			return true;
		}
		return false;
	}
	
	private static boolean outofOrder() throws IOException{
		if (actualNum != seqNum) {
			sk.send(createAck((byte)((actualNum == 0) ? 127 : actualNum - 1)));
			return true;
		}
		return false;
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