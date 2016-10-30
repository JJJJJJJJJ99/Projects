import java.util.*;
import java.util.zip.*;
import java.io.*;

public class Checksum {
	public static void main(String[] args) throws FileNotFoundException, IOException{
		try {
			InputStream file = new BufferedInputStream(
			        new FileInputStream(args[0]));
			byte[] buffer = new byte[1024];
			boolean flag = true;
			CRC32 checksum = new CRC32();
			while (flag){
	        	 	if (file.read(buffer) > 0){
	        	 		checksum.update(buffer, 0, buffer.length);
	        	 	} else{
	        	 		flag = false;
	        	 	}        	 	
			}			
			long checksumValue = checksum.getValue();
			System.out.println (checksumValue);

		}catch (FileNotFoundException e) {
			System.err.print("File not found");
		}
	}
}
