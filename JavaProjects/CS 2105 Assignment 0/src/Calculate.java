/*
 * This exercise is a refresher on command-line arguments and exception handling.
 * The command format is given by the example below:
 * Calculate 30 - 7
 * The result should be:
 * 23
 * 
 * @author Jingjing Wang
 */


import java.io.IOException;
import java.util.*;


public class Calculate {
	
	public static void main(String[] args) throws IOException{
		int operand1 = Integer.parseInt(args[0]);
		int operand2 = Integer.parseInt(args[2]);
		String operator = args[1];
		System.out.println(calculate(operand1, operand2, operator));
				
	}
	
	public static String calculate(int operand1, int operand2, String operator){
		
		// Check if operator is the correct form and return the result.
		if (operator.equals( "+")){
			return Integer.toString(operand1 + operand2);
		} else if (operator.equals("-")){
			return Integer.toString(operand1 - operand2);			
		} else if (operator.equals("*")){
			return Integer.toString(operand1 * operand2);
		} else if (operator.equals("/")){
			if (operand2 == 0){
				return "Division by zero";
			} else{
				return Integer.toString(operand1 / operand2);
			}			
		} else if (operator.equals("**")){ 
			return Integer.toString((int) Math.pow(operand1, operand2));
		} else{
			return "Invalid inputs";
		}
	}
}
