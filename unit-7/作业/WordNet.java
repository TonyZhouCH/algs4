package edu.princeton.cs.algs4;
import edu.princeton.cs.algs4.In;
import java.util.*;

public class WordNet {
   private ST<String, Bag<Integer>> st;;
   private ST<Integer, Bag<String>> stId;
   private Digraph G;
   private SAP sap;

   // constructor takes the name of the two input files
   public WordNet(String synsets, String hypernyms) {
   		st = new ST<String, Bag<Integer>>(); 
   		stId = new ST<Integer, Bag<String>>();
   		In in1 = new In(synsets);
   		
   		while (in1.hasNextLine()) {
   			String[] a = in1.readLine().split(",");
   			int id = Integer.parseInt(a[0]);  //a[0] is the id
   			String[] nounset = a[1].split(" "); //a[1] is the word,maybe one or more
   			stId.put(id, new Bag<String>());
   			for (String noun : nounset) {
   				if (!st.contains(noun)) {
   					st.put(noun, new Bag<Integer>());
   				}
	   			st.get(noun).add(id);
	   			stId.get(id).add(noun);
   			}

   		}

   		G = new Digraph(st.size()); //construct wordnet
   		In in2 = new In(hypernyms);
   		while (in2.hasNextLine()) {
   			String[] b = in2.readLine().split(",");
   			for (int i = 1; i < b.length; i++) {
   				G.addEdge(Integer.parseInt(b[0]), Integer.parseInt(b[i]));
   			}
   		}
   		sap = new SAP(G);
   }

   // returns all WordNet nouns
   public Iterable<String> nouns() {
   		return st.keys();
   }

   // is the word a WordNet noun?
   public boolean isNoun(String word) {
   		return st.contains(word);
   }

   // distance between nounA and nounB (defined below)
   public int distance(String nounA, String nounB) {
		Iterable<Integer> A = st.get(nounA);
        Iterable<Integer> B = st.get(nounB);

        return sap.length(A, B);
   }

   // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
   // in a shortest ancestral path (defined below)
   public String sap(String nounA, String nounB) {
   		Iterable<Integer> A = st.get(nounA);
        Iterable<Integer> B = st.get(nounB);

        int ancestor = sap.ancestor(A, B); //获得的只是ancestor的id
        Bag<String> ancestors =  stId.get(ancestor);
        String result = "";
        for (String s : ancestors) {
        	 result = result + s;
        }
        return result;
   }

   // do unit testing of this class
   public static void main(String[] args) {

   }
}