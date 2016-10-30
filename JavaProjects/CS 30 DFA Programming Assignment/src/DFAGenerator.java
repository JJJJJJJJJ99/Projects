import java.util.*;


public class DFAGenerator {
	
	public String generate(Set<String> whitelist) {
		String dfa = null;
		State start = new State("S"); // Create a start state named S
		int num = 1; // Count the number of states
		 // Create a temperary state to store start state
		for (String x: whitelist){
			State temp = start;
			// Partition whitelist into several strings
			for (int i = 0; i < x.length(); i++) { // Loop through the string, except for the last character
				
				if (temp.containEdge(x.charAt(i))){ // Check if this state has edge which has character from string. If there isn't
					temp = temp.getEdge(x.charAt(i)).getState();
				}else{				
					State newState = new State("Q"+num);
					num++;
					Edge newEdge = new Edge(x.charAt(i));
					newEdge.addState(newState);
					temp.addEdge(newEdge);
					temp = newState;
				}
				temp.accept();
			}
			System.out.println(temp.getName());
			
		}
		String[] initial = new String[1];
		initial[0] = "";
		
		getDFA(start, initial);
		
		//System.out.println(dfa);
		return dfa;
	}
	
	public void getDFA(State x, String[] initial){
		
		if(x.checkDiscover()==0){
			for(Edge temp: x.viewEdges()){
				initial[0] = initial[0] + String.valueOf(temp.getName())+", "+x.getName()+", "+temp.getState().getName()+"\n";
				
				State y = temp.getState();
				getDFA(y, initial);
			}
			x.discover();
			
		}

	}
	
	
}
