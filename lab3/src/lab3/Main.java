package lab3;
import java.util.*;

public class Main {
	  
	public static void main(String[] args) {
		   
		  String sentence_a = "~sun v ~money v ice";
		  String sentence_b = "~money v ice v movie"; //Should not be included!!
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
		  KB.add(F);
		
		  //String A = "a v b v ~c";
		  //String B = "c v b";
		  //String A = "a v b v ~c";
		  //String B = "d v b v ~g";
		  //String A = "~b v c v t";
		  //String B = "~c v z v b";
		
		  //Clauses a = new Clauses(A);
		  //Clauses b = new Clauses(B);
		  
		  //ArrayList<Clauses> KB = new ArrayList<Clauses>(2);
		  //KB.add(a);
		  //KB.add(b);
		  
		  Clauses res = new Clauses();
			ArrayList<Clauses> result = new ArrayList<Clauses>();

			
		  result = res.CNF(KB);
			if (result == null){
				System.out.println("Result is empty");
			}
			else{
				for(Clauses c:result){
				c.print();
			}
			}
			
		  
		  
	  }

}
