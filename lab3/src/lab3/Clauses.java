package lab3;
import java.util.*;
import org.apache.commons.collections4.CollectionUtils;

//A tautology is when there is a c and not c in the same claus, for example A = a v b v c v -c
//Then that whole claus is remove.
//A tautology is not when there is a c and a not c after the resolution step??? Did not get it!
//He said now: "then also remove the claus"
//If duplicates in C (resoultion of A and B) then remove duplicates.

public class Clauses {
	public ArrayList<String> pos = new ArrayList<String>();
	public ArrayList<String> neg = new ArrayList<String>();
	
	//Default constructor
	public Clauses() {
		pos.add("");
		neg.add("");
	}
	// Create a clause from a sentence
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
		
		System.out.println("Pos contains: " + pos);
		System.out.println("Neg contains: " + neg);
		
	}
	
	public ArrayList<Clauses> CNF(ArrayList<Clauses> KB) {
	
		ArrayList<Clauses> KBprim = new ArrayList<Clauses>();
		ArrayList<Clauses> S;
		Clauses C = new Clauses();
				// While KB is still updating
				while(!KBprim.equals(KB)) {
					
					//KB, S and C needs to be reset every loop
					KBprim = new ArrayList<Clauses>(KB);
					S = new ArrayList<Clauses>();
					//C = new Clauses();
					
					for(int i = 0; i < KB.size()-1; i++) {
						for(int j = i+1; j < KB.size(); j++) {

							//Resolutionen return the resolvent of A and B
							C = Resolution(KB.get(i), KB.get(j));
							//KB.get(j).print();
							//System.out.println("C Pos contains: " + C.pos);
							//System.out.println("C Neg contains: " + C.neg);
							
							if(C != null) {
								S.add(C);
								System.out.println("C Before Pos contains: " + C.pos);
								System.out.println("C Before Neg contains: " + C.neg);
								C.print();
							}
							else {
								System.out.println("false");
							}
						}
					}
					for(Clauses s: S) {
						//System.out.println("S Pos contains: " + s.pos);
						//System.out.println("S Neg contains: " + s.neg);
					}
					
					for(Clauses kb: KB) {
						//System.out.println("KB Pos contains: " + kb.pos);
						//System.out.println("KB Neg contains: " + kb.neg);
					}
					
					//Done
					if(S.isEmpty()) {
											
						return KB; 
					}				
					//Update KB with S
					
					KB = Incorporate(S, KB);
					KB= RemoveDuplicates(KB);
				}
		//S not empty what does this mean?
		return null;
		
	}
	
	public Clauses Resolution(Clauses A, Clauses B) {
		Clauses C = new Clauses();
		// No common clauses, the intersection is empty
		if(Collections.disjoint(A.pos, B.neg) && Collections.disjoint(A.neg, B.pos) ) {
			return null;
		}// if intersection is not empty
		if(!Collections.disjoint(A.pos, B.neg)) {
			ArrayList<String> random_element_B = (ArrayList<String>) CollectionUtils.intersection(A.pos, B.neg);
			System.out.println("Intersection A pos: " + A.pos);
			System.out.println("Intersection B neg: " + B.neg);
			A.pos.remove(random_element_B.get(0));
			B.neg.remove(random_element_B.get(0));
			System.out.println("Intersection A pos: " + A.pos);
			System.out.println("Intersection B neg: " + B.neg);

			
		}else {
			System.out.println("Intersection A neg: " + A.neg);
			System.out.println("Intersection B pos: " + B.pos);
			ArrayList<String> random_element_A = (ArrayList<String>) CollectionUtils.intersection(A.neg, B.pos);
			A.neg.remove(random_element_A.get(0));
			B.pos.remove(random_element_A.get(0));
			System.out.println("Intersection A neg: " + A.neg);
			System.out.println("Intersection B pos: " + B.pos);
		}
		C.pos = (ArrayList<String>) CollectionUtils.union(A.pos, B.pos);
		C.neg = (ArrayList<String>) CollectionUtils.union(A.neg, B.neg);
		System.out.println("C Before Pos contains: " + C.pos);
		System.out.println("C Before Neg contains: " + C.neg);
		//make union in C 
		//ArrayList<String> C_union = (ArrayList<String>) CollectionUtils.union(C.pos, C.neg);
		//remove duplicates in C (the strings in C "a v b v ~c")
		//Clauses C;
		
		if(!Collections.disjoint(C.pos, C.neg)) { //C.pos and C.neg has common literals
			return null;
		}	

		//C = RemoveDuplicates(C);

		
	
		return C;
	}
	
	public ArrayList<Clauses> RemoveDuplicates(ArrayList<Clauses> KB){
		ArrayList<Clauses> temp = new ArrayList<Clauses>(KB);
		for(int i = 0; i < KB.size()-1; i++) {
			if(KB.get(i).equal(KB.get(i+1))) {
				temp.remove(KB.get(i));
			}
		}
		return temp;
	}
	
	/*public Clauses RemoveDuplicates(Clauses C) {
		for(int i = 0; i< C.pos.size()-1; i++ ) {
			for(int j=i+1; j < C.pos.size(); j++) {
				if(C.pos.get(i).equals(C.pos.get(j))) {
					C.pos.remove(j);
				}
			}
		}
		
		for(int i = 0; i< C.neg.size()-1; i++ ) {
			for(int j=i+1; j < C.neg.size(); j++) {
				if(C.neg.get(i).equals(C.neg.get(j))) {
					C.neg.remove(j);
				}
			}
		}
		
		/*for(int i = 0; i< C.pos.size(); i++ ) {
			for(int j=0; j< C.neg.size(); j++) {
				if(C.neg.get(j).equals(C.pos.get(i))) {
					C.neg.remove(j);
					C.pos.remove(i);
				}
			}
		}*/
		//remove duplicates in 
		//Clauses C = new Clauses();
		//return C;*/
		
	//}
	
	public boolean equal(Clauses rhs) {
		if (rhs == null) return false;
		
		else if(this.pos.equals(rhs.pos) && this.neg.equals(rhs.neg)) {
			return true;
		}
		
		else if(this.pos.isEmpty() && rhs.pos.isEmpty() && this.neg.equals(rhs.neg)) {
			return true;
		}
		else if(this.neg.isEmpty() && rhs.neg.isEmpty() && this.pos.equals(rhs.pos)) {
			return true;
		}
		
		else return false;
	}
	
	public ArrayList<Clauses> Incorporate(ArrayList<Clauses>S,ArrayList<Clauses> KB){
		
		//for(Clauses A : S) {
		for(int i = 0; i < KB.size(); i++) {
			KB = Incorporate_clause(KB.get(i), KB);
		}
		
		//Clauses C = new Clauses();
		
		for(Clauses kb : KB) {
			//System.out.println("Before: " + kb.pos);
			//System.out.println("Before: " + kb.neg);
		}
		
		
		//KB = Incorporate_clause(C,KB);
		
		for(Clauses kb : KB) {
			//System.out.println("After: " + kb.pos);
			//System.out.println("After: " + kb.neg);
		}
		
		return KB;
	}
	
	public ArrayList<Clauses> Incorporate_clause(Clauses A,ArrayList<Clauses> KB){
		for(int i = 0; i < KB.size(); i++) {
			
			if(isSubsetOf(A, KB.get(i))) {
				KB.remove(KB.get(i));
			}
			else if(isSubsetOf(KB.get(i), A)) {
				return KB;
			}

		}
		KB.add(A);
		return KB;
	}
	
	public boolean isSubsetOf(Clauses lhs, Clauses rhs) {
		if(CollectionUtils.isSubCollection(lhs.pos, rhs.pos)) {
			if (CollectionUtils.isSubCollection(lhs.neg, rhs.neg)){
				return true;
			}
		}
		return false;
	}
	
	public void print() {
		String printString = "";
		
		
		for (String l:pos) {
			printString+= l;
			if(!pos.get(pos.size()-1).equals(l)) {
				printString +=" v ";
			}
			
		}
		for (String l:neg) {
		printString +="~" + l;
		}	
		

		
		
		System.out.println(printString);
	}
}
