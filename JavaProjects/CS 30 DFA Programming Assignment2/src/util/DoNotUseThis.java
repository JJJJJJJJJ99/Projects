package util;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class DoNotUseThis {
	
	private Map<String, Map<String, String>> transitions;
	private Set<String> acceptStates;
	
	public DoNotUseThis(String repr) {
		transitions = new HashMap<String, Map<String, String>>();
		acceptStates = new HashSet<String>();
		
		for (String l : repr.split("\n")) {
			if (l.startsWith("#"))
				continue;
			
			String[] threeTuple = l.trim().split(",");
			String transChar = threeTuple[0].trim();
			String fromState = threeTuple[1].trim();
			String toState = threeTuple[2].trim();
			
			if (fromState.endsWith("*")) {
				fromState = fromState.substring(0, fromState.length() - 1);
				acceptStates.add(fromState);
			}
			
			if (toState.endsWith("*")) { 
				toState = toState.substring(0, toState.length() - 1);
				acceptStates.add(toState);
			}
			
			if (!transitions.containsKey(fromState)) {
				transitions.put(fromState, new HashMap<String, String>());
			}
			
			if (!transitions.containsKey(toState)) {
				transitions.put(toState, new HashMap<String, String>());
			}
			
			Map<String, String> outEdges = transitions.get(fromState);
			outEdges.put(transChar, toState);
			
			
		}
	}
	
	public int getNumStates() {
		return transitions.size();
	}
	
	public boolean testString(String s) {
		String state = "S";
		for (int i = 0; i < s.length(); i++) {

			state = transitions.get(state).get(s.substring(i, i + 1));
			if (state == null)
				return false;
		}
		
		return acceptStates.contains(state);
	}
	
	
	public Set<String> unreachableStates() {
		Set<String> unreachable = new HashSet<String>(transitions.keySet());
		
		// iterate over the graph, remove from unreachable
		Deque<String> search = new LinkedList<String>();
		search.push("S");
		
		while (!search.isEmpty()) {
			String curr = search.pop();
			unreachable.remove(curr);
			if (!transitions.containsKey(curr))
				System.out.println("Don't have: " + curr);
			search.addAll(transitions.get(curr).values());
		}
		
		return unreachable;
	}
	
	
}
