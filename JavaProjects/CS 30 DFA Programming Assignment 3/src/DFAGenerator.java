/* Name: Jingjing Wang
 * Email: jjwang95@brandeis.edu
 * Date: 3/11/2015
 * This program is going to build a DFA and minimize it.
*/

import java.util.*;
public class DFAGenerator {
	// Some global variables
	String dfa = "";
	String minidfa = "";
	Set<State> states = new HashSet();
	Set<State> distinguishable = new HashSet();
	
	public String generate(Set<String> whitelist) {
		
		State start = new State("S");
		Iterator iter = whitelist.iterator();
		buildDFA(start, iter);				
		getDFA(start);
		minimize(start);
		getMinimizedDFA();
	
		return minidfa; 
	}
	
	
	//This is for part 1 
	//Transform the tree of states into string
	public void getDFA(State s){
		for (String x: s.getEdges().keySet()){
			states.add(s); // This is for part 2
			dfa = dfa+x+", "+ s.getName()+", "+s.getNextState(x).getName()+"\n";
			getDFA(s.getNextState(x));
		}		
	}
	
	//This is for part 1
	//Iterate the words, if seeing same character move to the next state
	// If it is a new character, make a new state
	public void buildDFA(State start, Iterator iter){
		int num = 1;		
		while(iter.hasNext()){
			String x = (String) iter.next();
			State temp = start;
			for (int i = 0; i < x.length(); i++){
				String newEdge = String.valueOf(x.charAt(i));
				if(!temp.containsEdge(String.valueOf(x.charAt(i)))){
					State newState = new State("Q"+num);
					num++;
					temp.addEdge(String.valueOf(x.charAt(i)), newState);
					temp = newState;					
				}else{
					temp = temp.getNextState(String.valueOf(x.charAt(i)));
				}
			}			
			temp.accept();
		}
	} // This is for part 1
	
	//This is for part 2
	public State minimize(State s){
		for(Map.Entry<String, State> entry: s.getEdges().entrySet()){			
			entry.setValue(minimize(entry.getValue()));// Take next state as parameter
		}		
		return has(s);
	
	}
	
	//This is for part 2
	public State has(State s ){ // Check if distinguishable has the minimized state
		State forreturn = s;
		for(State temp : distinguishable){
			if(temp.isEqual(s)){
				forreturn = temp;
			}
		}
		distinguishable.add(forreturn);
		return forreturn;
	}
	
	//This is for part 2
	public State get(State s){ // Get the equivalent state
		State x = new State("");
		for(State temp: distinguishable){
			if(temp.isEqual(s)){
				x = temp;
			}
		}
		return x;
	}
	
	// Turn start state into a string
	public void getMinimizedDFA(){
		for(State s: distinguishable){
			for(String x: s.getEdges().keySet()){
				minidfa = minidfa+x+", "+s.getName()+", "+s.getNextState(x).getName()+"\n";
			}
			
		}
	}
	
}
