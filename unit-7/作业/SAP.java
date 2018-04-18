package edu.princeton.cs.algs4;
import edu.princeton.cs.algs4.In;
import java.util.*;


public class SAP {
	private Digraph G;  
	public SAP(Digraph G) {
		this.G = new Digraph(G);	
	}

	public int length(int v, int w) {
		int[] result = shortest(v, w);
		return result[0];
	}

	public int ancestor(int v, int w) {
		int[] result = shortest(v, w);
		return result[1];
	}

	private int[] shortest(int v, int w) {
		int[] result = new int[2];
		Strongbfs vbfs = new Strongbfs(G, v);
		Strongbfs wbfs = new Strongbfs(G, w);
		boolean[] vmarked = vbfs.getMarked();
		boolean[] wmarked = wbfs.getMarked();
		int shortestLength = Integer.MAX_VALUE;
		int shortestAncestor = Integer.MAX_VALUE;
		int length;

		for (int i = 0; i < vmarked.length; i++) {
			if (vmarked[i] && wmarked[i]) {
				length = vbfs.distTo(i) + wbfs.distTo(i);
				if (length < shortestLength) {
					shortestLength = length;
					shortestAncestor = i;
				}
			}
		}

		if (shortestLength == Integer.MAX_VALUE) {
			result[0] = -1;
			result[1] = -1;
			return result; 
		}

		result[0] = shortestLength;
		result[1] = shortestAncestor;
		return result;
	}

	public int length(Iterable<Integer> v, Iterable<Integer> w){  
        int[] result = shortest(v, w);  
        return result[0];  
    }  

    public int ancestor(Iterable<Integer> v, Iterable<Integer> w){  
        int[] result = shortest(v, w);  
        return result[1];  
    } 

    private int[] shortest(Iterable<Integer> v, Iterable<Integer> w) {
    	int[] result = new int[2];
		int shortestLength = Integer.MAX_VALUE;
		int shortestAncestor = Integer.MAX_VALUE;

		for (int vnode : v) {
			for (int wnode : w) {
				int[] tempshortest = shortest(v, w);
				if (tempshortest[0] != -1 && (tempshortest[0] < shortestLength)) {
					shortestLength = tempshortest[0];
					shortestAncestor = tempshortest[1];
				}
			}
		}

		if (shortestLength == Integer.MAX_VALUE) {
			result[0] = -1;
			result[1] = -1;
			return result;
		}

		result[0] = shortestLength;
		result[1] = shortestAncestor;
		return result;
    }

    public static void main(String[] args) {

   }


}