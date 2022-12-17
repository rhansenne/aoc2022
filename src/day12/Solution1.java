package day12;
import java.io.*;
import java.util.*;

import org.jgrapht.*;
import org.jgrapht.alg.shortestpath.*;
import org.jgrapht.graph.*;

public class Solution1 {
	
	public static void main(String[] args) throws Exception {
		// read file as matrix
		Scanner scanner = new Scanner(new File("src\\day12\\input.txt"));		
		List<char[]> matrix = new ArrayList<char[]>();
		while (scanner.hasNextLine())
			matrix.add(scanner.nextLine().toCharArray());
		scanner.close();

		// convert to directed graph
		String start="", end="";
		DefaultDirectedGraph<String, DefaultEdge> graph = new DefaultDirectedGraph(DefaultWeightedEdge.class);
		for (int i=0;i<matrix.size();i++) {
			char[] row = matrix.get(i);
			for (int j=0;j<row.length;j++) {
				if (row[j]=='S') {
					start = i+"."+j;
					row[j] = 'a';
				} else if (row[j]=='E') {
					end = i+"."+j;
					row[j] = 'z';
				}
				graph.addVertex(i+"."+j);
				if (i>0) checkAddEdge(graph, matrix.get(i-1)[j], row[j], (i-1)+"."+j, i+"."+j);
				if (j>0) checkAddEdge(graph, row[j-1], row[j], i+"."+(j-1), i+"."+j);
			}
		}
		
		// get shortest path
		System.out.println(new DijkstraShortestPath<String, DefaultEdge>(graph).getPath(start, end).getLength());
	}
	
	private static void checkAddEdge(Graph<String, DefaultEdge> graph, char a, char b, String coA, String coB) {
		if (b<=a+1)	graph.addEdge(coA, coB);
		if (a<=b+1) graph.addEdge(coB, coA);
	}
	
}