package lab3;
import java.util.*;

public class Main {
	  
	public static void main(String[] args) {
		   
		  String sentence_a = "~sun v ~money v ice";
		  String sentence_b = "~money v ice v movie";
		  String sentence_c = "~movie v money";
		  String sentence_d = "~movie v ~ice";
		  String sentence_e = "sun v money v cry";
		  String sentence_f = "movie";
		  
		  Clauses A = new Clauses(sentence_a);
		  Clauses B = new Clauses(sentence_b);
		  Clauses C = new Clauses(sentence_c);
		  Clauses D = new Clauses(sentence_d);
		  Clauses E = new Clauses(sentence_e);
		  Clauses F = new Clauses(sentence_f);
		  
		  Vector<Clauses> KB = new Vector<Clauses>(6);
		  KB.add(A);
		  KB.add(B);
		  KB.add(C);
		  KB.add(D);
		  KB.add(E);
		  KB.add(F);
		  
		  Clauses res = new Clauses();
		  
		  res.CNF(KB);
		  
	  }

}
