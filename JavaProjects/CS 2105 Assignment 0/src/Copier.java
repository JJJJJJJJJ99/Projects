/*
 * This program makes copy of an existing file.
 * Sample run:
 * Copier dog.jpg puppy.jpg
 * Result:
 * dog.jpg successfully copied to puppy.jpg
 * 
 * @author Jingjing Wang
 */

import java.io.*;
import java.util.*;


public class Copier {
	public static void main(String[] args) throws IOException{
		
			try {
				InputStream file1 = new BufferedInputStream(
				        new FileInputStream(args[0]));
										
				InputStream file2 = new BufferedInputStream(
					    new FileInputStream(args[1]));
				
				OutputStream output = new FileOutputStream(args[1]);
				
				/*
				 * Check if these two files have same contents or not.
				 * If they have same contents do nothing.
				 */
				if (issame(file1, file2)){
					System.out.println();
				} else{
					int readnum;
					byte[] buffer = new byte[1024];
					while ((readnum = file1.read(buffer)) > 0) {
						output.write(buffer, 0, readnum);
					}
					
					System.out.println(args[0]+" successfully copied to "+args[1]);
					
					file1.close();
					file2.close();
				}
			} catch (FileNotFoundException e) {
				System.err.print("File not found");
			} catch (IOException e){
				throw e;
			}
		}
	
	public static boolean issame (InputStream file1, InputStream file2) throws IOException{

		 try{
			 byte[] buffer1 = new byte[1024];
	         byte[] buffer2 = new byte[1024];
	         while (true){
	        	 	if (file1.read() > 0){
	        	 		if (file1.read(buffer1) != file2.read(buffer2)) return false;
		        	 	if (!buffer1.equals(buffer2)) return false;
	        	 	} else{
	        	 		return true;
	        	 	}        	 	
	         }	
         }  catch (IOException e) {
				throw e;
		}
	}
}
