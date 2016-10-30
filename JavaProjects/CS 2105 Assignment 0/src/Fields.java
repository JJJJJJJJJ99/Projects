/*
 * This program takes several lines of text from the interactive user-input and 
 * extracts information to reshows on screen.
 * Example input:
 * Name: John Smith
Age: 21 years
Year of matriculation: 2014 CAP: 3.76

Result:
Name
John Smith
Year
Unknown field
cap
3.76
year of matriculation
2014
quit

@author Jingjing Wang
 */

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Scanner;


public class Fields {
	// LinkedHashMap takes O(1) time, it inserts in order.
	private static LinkedHashMap<String, String> inputMap = new LinkedHashMap<String, String>();
	
	public static void main(String[] args) throws IOException{
		Scanner scan = new Scanner(System.in);
		String line = null;
		while(!(line = scan.nextLine()).isEmpty()){
			// Input is non-empty string and should be in the form of field-value.
			readInput(line);
		}
		
		String header = null;
		String value = null;
		while(!(header = scan.next()).equalsIgnoreCase("quit")){
			// Since field is not case-sensitive, change them all into lower case.
			value = inputMap.get(header.toLowerCase());
			System.out.println(value);
		}
	}
	
	public static void readInput(String input) throws IOException{
		Scanner read = new Scanner (input);
		
		String field = read.next();
		while (!field.endsWith(":")){
			field = field + read.next();
		}
		
		field = field.substring(0, field.length()-1);
		
		
		String value = "";
		
		while(read.hasNext()){
			value = value + read.next();
		}
		
		inputMap.put(field.toLowerCase(), value);
	}
}
