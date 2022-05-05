package lab3;

import java.util.*;

public class Main {

	public static void main(String[] args) {

		/*String sentence_a = "~sun v ~money v ice";
		String sentence_b = "~money v ice v movie"; // Should not be included!!
		String sentence_c = "~movie v money";
		String sentence_d = "~movie v ~ice";
		String sentence_e = "movie";
		String sentence_f = "sun v money v cry";

		Clauses A = new Clauses(sentence_a);
		Clauses B = new Clauses(sentence_b);
		Clauses C = new Clauses(sentence_c);
		Clauses D = new Clauses(sentence_d);
		Clauses E = new Clauses(sentence_e);
		Clauses F = new Clauses(sentence_f);

		ArrayList<Clauses> KB = new ArrayList<Clauses>(6);
		KB.add(A);
		KB.add(B);
		KB.add(C);
		KB.add(D);
		KB.add(E);
		KB.add(F);*/

		// String A = "a v b v ~c";
		// String B = "c v b";
		// String A = "a v b v ~c";
		// String B = "d v b v ~g";
		// String A = "~b v c v t";
		// String B = "~c v z v b";

		// Clauses a = new Clauses(A);
		// Clauses b = new Clauses(B);

		// ArrayList<Clauses> KB = new ArrayList<Clauses>(2);
		// KB.add(a);
		// KB.add(b);

	/*******************TASK B***********************/
	//(A -> -C & (B | -B)) & (-A -> (- C | C) & (-B | B)) & (B -> A & -C) & (-C -> A & (-B | B))
		
		//transform to CNF
		// (-A V -C) &
		// (B V -B) &
		// (A V -C V C) &
		// (-B V B) &
		// (-B V A) &
		// (B V -A) &
		// -C &
		// (C V A) &
		// (-B V B)
		//result: Clause set is true if we assign values to variables as: A -C B
		// Alex is knight
		// Cody is knave
		// Ben is spy
		String sentence_a = "~A v ~C";
		String sentence_b = "~B"; 
		String sentence_c = "~A v ~C v C";
		String sentence_d = "~B v B";
		String sentence_e = "~B v A";
		String sentence_f = "~C";
		String sentence_g = "C v A";
		String sentence_h = "B v ~A";

		Clauses A = new Clauses(sentence_a);
		Clauses B = new Clauses(sentence_b);
		Clauses C = new Clauses(sentence_c);
		Clauses D = new Clauses(sentence_d);
		Clauses E = new Clauses(sentence_e);
		Clauses F = new Clauses(sentence_f);
		Clauses G = new Clauses(sentence_g);
		Clauses H = new Clauses(sentence_h);
		ArrayList<Clauses> KB = new ArrayList<Clauses>(8);

		KB.add(A);
		KB.add(B);
		KB.add(C);
		KB.add(D);
		KB.add(E);
		KB.add(F);
		KB.add(G);
		KB.add(H);

		Clauses res = new Clauses();
		ArrayList<Clauses> result = new ArrayList<Clauses>();

		result = res.CNF(KB);
		if (result == null) {
			System.out.println("Result is empty");
		} else {
			for (Clauses c : result) {
				c.print();
			}
		}

	}

}
