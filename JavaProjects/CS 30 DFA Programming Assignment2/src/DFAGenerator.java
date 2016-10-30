import java.util.*;


public class DFAGenerator {
	
	public String generate(Set<String> whitelist) {
		Node s = new Node("S");
		int count = 1;
		String name = "Q";
		for(String str: whitelist){
			Node temp = s;
			for(int i = 0; i < str.length(); i++){
				String current = Character.toString(str.charAt(i));
				if(temp.transitions.containsKey(current)){
					temp = temp.transitions.get(current);
				}else{
					name = "Q" + count;
					count++;
					Node newNode = new Node(name);
					temp.addTrans(current, newNode);
					temp = newNode;
				}
			}
			
			temp.nodeLabel = temp.nodeLabel + "*";
		}
		String [] result = new String [1];
		result[0] = "";
		dfs(result, s);
		System.out.println(result[0]);
		String answer = result[0];
		return answer; 
	}
	public void dfs(String[] result, Node n){
		if(!n.dfsMark){
			for(String str: n.transitions.keySet()){
				result[0] = result[0] + str + ", " + n.nodeLabel + ", " + n.transitions.get(str).nodeLabel + "\n";
			}
			n.dfsMark = true;
			for(String str: n.transitions.keySet()){
				dfs(result, n.transitions.get(str));
			}
		}
	}
	
	
	
	}
	
	
	
	

