/*
 * This exercise is a refresher on converting a binary string input to integers.
 * The command format is given by the example below:
 * 11001011100001001110010110000000
 * The result should be:
 * 203.132.229.128
 * 
 * @author Jingjing Wang
 */

import java.io.IOException;
import java.util.*;

public class IPAaddress {
	public static void main(String[] args) throws IOException{
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		String one= input.substring(0,8);
		String two = input.substring(8,16);
		String three = input.substring(16,24);
		String four = input.substring(24,32);
		String ip = convert(one, two, three, four);
		System.out.println(ip);
		scan.close();
	}
	
	public static String convert(String one, String two, String three, String four){
		int first = Integer.parseInt(one,2);
		int second = Integer.parseInt(two,2);
		int third = Integer.parseInt(three,2);
		int forth = Integer.parseInt(four,2);
		return first+"."+second+"."+third+"."+forth+".";
	}
}
