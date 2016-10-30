import java.util.*;

//Create a state that contains a map of edges
// Each edge relates to a state

public class State {
	private HashMap<String, State> edges;
	private String name;

	public State(String name) {
		this.edges = new HashMap<String, State>();
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public HashMap<String, State> getEdges() {
		return this.edges;
	}

	// Change the state into accept state
	public void accept() {
		this.name = this.name + "*";
	}

	public void addEdge(String x, State y) {
		this.edges.put(x, y);
	}

	public State getNextState(String x) {
		return this.edges.get(String.valueOf(x));
	}

	//
	public boolean containsEdge(String x) {
		boolean flag = false;
		for (String edgeName : edges.keySet()) {
			if (edgeName.equals(x)) {
				flag = true;
			}
		}
		return flag;
	}

	public boolean isFinal() {
		return this.getEdges().isEmpty();
	}

	// This is for part 2
	public boolean isEqual(State s) { // Check if two states has equal
										// transition function

		if (this.isFinal() && s.isFinal()) {
		
		} else {
			if (this.getEdges().size() != s.getEdges().size())
				return false;
			for (Map.Entry<String, State> thistemp : edges.entrySet()) {
				if (s.getEdges().get(thistemp.getKey()) != thistemp.getValue())
					return false;
			}
		}
		return true;
	}
}
