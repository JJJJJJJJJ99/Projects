import java.util.*;


public class State {
	private ArrayList<Edge> nextEdges;
	private String name;
	private int discover;
	
	public State(String name){
		this.nextEdges = new ArrayList<Edge>();
		this.name = name;
		this.discover = 0;
	}
	
	public String getName(){
		return this.name;
	}
	
	public boolean containEdge(Character x){
		boolean flag = false;
		for (Edge temp: nextEdges){
			if(temp.getName() == x){
				flag = true;
			}
		}		
		return flag;
	}
	
	public void acceptState(){
		this.name = "A*";
	}
	
	public void addEdge(Edge x){
		this.nextEdges.add(x);
	}
	
	public Edge getEdge(Character x){
		Edge forreturn = null;
		for(Edge temp: nextEdges){
			if(temp.getName() == x){
				forreturn = temp;
			}
		}
		return forreturn;
	}
	
	public ArrayList<Edge> viewEdges(){
		return this.nextEdges;
	}
	
	public void discover(){
		this.discover = 1;
	}
	
	public void accept(){
		this.name = this.name+"*";
	}
	
	public int checkDiscover(){
		return this.discover;
	}
	

}
