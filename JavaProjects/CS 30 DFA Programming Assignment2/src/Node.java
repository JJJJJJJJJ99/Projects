import java.util.*;
public class Node {
	Map <String , Node > transitions ;
	String nodeLabel ;
	boolean dfsMark;
	public Node ( String label ) {
		transitions = new HashMap < String , Node >();
		this.dfsMark = false;
		this.nodeLabel = label ;
	}
	public void addTrans ( String on , Node to) {
		transitions.put(on , to );
	}
}
