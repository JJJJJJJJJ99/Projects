import java.util.*;

public class Edge {
	private State nextState;
	private Character name;
	
	public Edge(Character name){
		this.name = name;
	}
	
	public Character getName(){
		return name;
	}
	
	public State getState(){
		return this.nextState;
	}
	
	public void addState(State x){
		this.nextState = x;
	}
}
