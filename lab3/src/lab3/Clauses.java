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

	// Default constructor
	public Clauses() {
		pos.add("");
		neg.add("");
		// pos = new ArrayList<String>();
		// neg = new ArrayList<String>();
	}

	// Create a clause from a sentence
	public Clauses(String sentence) {
		// pos = new ArrayList<String>();
		// neg = new ArrayList<String>();

		String[] splitSentence;

		splitSentence = sentence.split(" ");

		for (String s : splitSentence) {
			if (s.startsWith("~")) {
				neg.add(s.replace("~", ""));
			} else if (!(s.equals("v"))) {

				pos.add(s);
			}
		}
		;

	}

	public Clauses(Clauses copy) {
		this.pos = (ArrayList<String>) copy.pos.clone();
		this.neg = (ArrayList<String>) copy.neg.clone();
	};

	public ArrayList<Clauses> CNF(ArrayList<Clauses> KB) {

		ArrayList<Clauses> KBprim = new ArrayList<Clauses>();
		ArrayList<Clauses> S = new ArrayList<Clauses>();
		Clauses C;// new Clauses();
		Clauses A;
		Clauses B;

		S = new ArrayList<Clauses>(KB);
		KB.clear();
		KB = Incorporate(S,KB);

		// While KB is still updating
		while (!KBprim.equals(KB)) {

			// KB, S and C needs to be reset every loop
			KBprim = new ArrayList<Clauses>(KB);

			S.clear();
			C = new Clauses();

			for (int i = 0; i < KB.size() - 1; i++) {
				for (int j = i + 1; j < KB.size(); j++) {

					A = new Clauses(KB.get(i));
					B = new Clauses(KB.get(j));

					// Resolutionen return the resolvent of A and B
					C = Resolution(A, B);

					if (C != null) {
						S.add(C);
						// C.print();
					} else {
						// System.out.println("false");
					}
				}
			}
			// Done
			if (S.isEmpty()) {
				return KB;
			}
			// Update KB with S
			KB = Incorporate(S, KB);
			KB = RemoveDuplicates(KB);
			// for(Clauses i:KB){
			// i.print();
			// }
		}
		// S not empty what does this mean?
		return null;

	}

	public Clauses Resolution(Clauses A, Clauses B) {
		Clauses C = new Clauses();

		// No common clauses, the intersection is empty
		if (Collections.disjoint(A.pos, B.neg) && Collections.disjoint(A.neg, B.pos)) {
			return null;
			// A = "~movie v money" B = "~movie v ~ice" gives null, should it keep ~movie?
			// A = "~movie v money" B = "sun v money v cry" gives null?
		}

		// if intersection is not empty
		if (!Collections.disjoint(A.pos, B.neg)) {
			ArrayList<String> random_element_B = (ArrayList<String>) CollectionUtils.intersection(A.pos, B.neg);
			A.pos.remove(random_element_B.get(0));
			B.neg.remove(random_element_B.get(0));

		} else {
			ArrayList<String> random_element_A = (ArrayList<String>) CollectionUtils.intersection(A.neg, B.pos);
			A.neg.remove(random_element_A.get(0));
			B.pos.remove(random_element_A.get(0));
		}

		C.pos = (ArrayList<String>) CollectionUtils.union(A.pos, B.pos);
		C.neg = (ArrayList<String>) CollectionUtils.union(A.neg, B.neg);

		// make union in C
		// ArrayList<String> C_union = (ArrayList<String>) CollectionUtils.union(C.pos,
		// C.neg);
		// remove duplicates in C (the strings in C "a v b v ~c")
		// Clauses C;

		if (!Collections.disjoint(C.pos, C.neg)) { // C.pos and C.neg has common literals, C is a tautology
			return null;
		}
		//C = RemoveDuplicates(C);
		return C;
	}

	public ArrayList<Clauses> RemoveDuplicates(ArrayList<Clauses> KB) {
		ArrayList<Clauses> temp = new ArrayList<Clauses>(KB);
		for (int i = 0; i < KB.size() - 1; i++) {
			if (KB.get(i).equal(KB.get(i + 1))) {
				temp.remove(KB.get(i));
			}
		}
		return temp;
	}

	public Clauses RemoveDuplicates(Clauses C) {
		// Function to remove duplicates from an ArrayList
		// Create a new ArrayList
		ArrayList<String> newPos = new ArrayList<String>();
		ArrayList<String> newNeg = new ArrayList<String>();

		// Traverse through the first list
		for (String pos_element : C.pos) {
			// If this element is not present in newList
			// then add it
			if (!newPos.contains(pos_element)) {
				newPos.add(pos_element);
			}
		}

		for (String neg_element : C.neg) {

			// If this element is not present in newList
			// then add it
			if (!newNeg.contains(neg_element)) {

				newNeg.add(neg_element);
			}
		}

		// return the new list
		Clauses newClaus = new Clauses();
		newClaus.pos = newPos;
		newClaus.neg = newNeg;

		return newClaus;

		/*
		 * for(int i = 0; i < C.pos.size()-1; i++ ) {
		 * for(int j=i+1; j < C.pos.size(); j++) {
		 * if(C.pos.get(i).equals(C.pos.get(j))) {
		 * C.pos.remove(i);
		 * }
		 * }
		 * }
		 * 
		 * for(int i = 0; i < C.neg.size()-1; i++ ) {
		 * for(int j=i+1; j < C.neg.size(); j++) {
		 * if(C.neg.get(i).equals(C.neg.get(j))) {
		 * C.neg.remove(i);
		 * }
		 * }
		 * }
		 */

		/*
		 * for(int i = 0; i < C.pos.size(); i++ ) {
		 * for(int j=0; j < C.neg.size(); j++) {
		 * if(C.neg.get(j).equals(C.pos.get(i))) {
		 * C.neg.remove(j);
		 * C.pos.remove(i);
		 * }
		 * }
		 * }
		 */

		// remove duplicates in
		// Clauses C = new Clauses();
		// return C;

	};

	public boolean equal(Clauses rhs) {
		if (rhs == null)
			return false;

		else if (this.pos.equals(rhs.pos) && this.neg.equals(rhs.neg)) {
			return true;
		}

		else if (this.pos.isEmpty() && rhs.pos.isEmpty() && this.neg.equals(rhs.neg)) {
			return true;
		} else if (this.neg.isEmpty() && rhs.neg.isEmpty() && this.pos.equals(rhs.pos)) {
			return true;
		}

		else
			return false;
	}

	public ArrayList<Clauses> Incorporate(ArrayList<Clauses> S, ArrayList<Clauses> KB) {
		ArrayList<Clauses> KBTemp = new ArrayList<Clauses>(KB);
		for (Clauses A : S) {
			KBTemp = Incorporate_clause(A, KBTemp);
		}

		return KBTemp;
	}

	public ArrayList<Clauses> Incorporate_clause(Clauses A, ArrayList<Clauses> KB) {
		int KBSize = KB.size();
		// for(Clauses B : KB){
		for (int i = 0; i < KBSize; i++) {
			if (isSubsetOf(KB.get(i), A)) {
				return KB;
			}
			// }
			// for(Clauses B : KB){
			if (isSubsetOf(A, KB.get(i))) { // A is a subset of B, A have all knowledge of B but B has additional
				KB.remove(KB.get(i)); // Remove B, since B can be an "or" but A says what is true, so we want the one
										// with least "ors"
				KBSize = KB.size();
			}
		}
		KB.add(A); // New knowledge we add
		return KB;

	}

	// Returns true iff lhs is a sub-collection of rhs
	public boolean isSubsetOf(Clauses lhs, Clauses rhs) {
		boolean subset = true;
		if (!CollectionUtils.isSubCollection(lhs.pos, rhs.pos)) {
			subset = false;
		}
		if (!CollectionUtils.isSubCollection(lhs.neg, rhs.neg)) {
			subset = false;
		}
		return subset;
	}
	// public boolean isSubsetOf(Clauses lhs, Clauses rhs) {
	// boolean subset = false;

	// for(String i:lhs.pos){
	// subset = false;
	// for(String j:rhs.pos){
	// if(i.equals(j)){
	// subset = true;
	// break;
	// }
	// }
	// }
	// if(!subset)return false;

	// for(String i:lhs.neg){
	// subset = false;
	// for(String j:rhs.neg){
	// if(i.equals(j)){
	// subset = true;
	// break;
	// }
	// }
	// }
	// if(!subset)return false;
	// return true;
	// }

	public void print() {
		String printString = "";

		for (String l : pos) {
			printString += l;
			if (!pos.get(pos.size() - 1).equals(l)) {
				printString += " v ";
			}

		}
		for (String l : neg) {
			printString += "~" + l;
		}

		System.out.println(printString);
	}
}
