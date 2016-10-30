/*
 * This program schedules a task to be executed periodically after a certain delay.
 * Example input:
 * $java TimePrinter Hello 5 10
 * 
 * Result:
 * (5-second pause)
Hello
(10-second pause)
Hello
(10-second pause)
Hello
q
(program terminates)
$

@author Jingjing Wang
 */


import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class TimePrinter extends TimerTask{
	public String outputString;

	public TimePrinter(String outputString){
		this.outputString = outputString;
	}
		
	public static void main (String[] args){
		String outputString = "";
		int i = 0;
		for (String word: args){
			if (!word.matches(".*\\d.*")){
				i = i + 1;
				outputString = outputString + word + " ";
			}
		}
		int startTime = Integer.parseInt(args[i]);
		int interval = Integer.parseInt(args[i + 1]);
		Timer timer = new Timer();
		timer.schedule(new TimePrinter(outputString), 
	    		(long) startTime*1000, (long) interval*1000);
		Scanner scan = new Scanner(System.in);
		if (scan.next().equals("q")){
			timer.cancel();
		}
		scan.close();
	}

	public void run() {
		System.out.println(outputString + "\n");							
	}
}
