package lab3;
import java.util.*;

public class Clauses {
	public Vector<String> pos = new Vector<String>();
	public Vector<String> neg = new Vector<String>();
	
	//Default constructor
	public Clauses() {
		pos.add("");
		neg.add("");
	}
	
	public Clauses(String sentence) {
		String[] splitSentence;
		
		splitSentence = sentence.split(" ");
		
		for (String s: splitSentence) {
			if(s.startsWith("~")) {
				neg.add(s.replace("~", ""));
			}
			else if(!(s.equals("v"))) {
				
				pos.add(s);
			}
		};
		
		//System.out.println("Pos contains: " + pos);
		//System.out.println("Neg contains: " + neg);
		
	}
	
	public Clauses CNF(Vector<Clauses> KB) {
		Clauses C = new Clauses();
		
		
		return null;
		
	}
	
	public Clauses Resolution(Clauses A, Clauses B) {
		return null;
	}
	
	
}
