package day16;
import java.io.*;
import java.util.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.shortestpath.*;

public class Solution1 {
	
	static long max=0;
	static DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> graph = new DefaultDirectedWeightedGraph(DefaultWeightedEdge.class);
	static Map<String,Map<String,Integer>> shortestPaths = new HashMap<String,Map<String,Integer>>();
	
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day16\\input.txt"));
		while (scanner.hasNextLine()) {
			String[] line = scanner.nextLine().split("Valve | has flow rate=|; tunnels? leads? to valves? ");
			int rate = Integer.parseInt(line[2]);
			String from = line[1];
			graph.addVertex(from);
			if (rate>0)
	            graph.setEdgeWeight(graph.addEdge(from,from), rate);
			for (String to: line[3].split(", ")) {
				graph.addVertex(to);
				graph.setEdgeWeight(graph.addEdge(from, to), 1);
			}
		}
		scanner.close();		
		// determine shortest paths between each valve with a non-zero rate and each other valve
		DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<String, DefaultWeightedEdge>(graph);
		for (String v: graph.vertexSet()) {
			if (getFlow(v)<=0)
				continue;
			Map<String,Integer> sps= new HashMap<String,Integer>();
			shortestPaths.put(v, sps);
			for (String v2: graph.vertexSet())
				sps.put(v2, dijkstra.getPath(v, v2).getLength());
		}				
		follow("AA", new HashSet<String>(), 25 ,0 ,0);
		System.out.println(max);
	}
	
	static void follow(String v, Set<String> openedValves, int timeRemaining, long flow, long total) {		
		for (Map.Entry<String,Map<String,Integer>> e: shortestPaths.entrySet()) {
			String toValve =e.getKey();
			int dist = e.getValue().get(v);
			if (!openedValves.contains(toValve) && timeRemaining>(dist+1)) {
				Set<String> newOpenedValves = new HashSet<String>(openedValves);
				newOpenedValves.add(toValve);			
				long newFlow = flow+getFlow(toValve);
				long newTotal = total+flow*(dist+1);
				int newTimeRemaining = timeRemaining-(dist+1);
				follow(toValve, newOpenedValves, newTimeRemaining, newFlow, newTotal);
			}
		}
		long newTotal = total+timeRemaining*flow;				
		if (newTotal>max)
			max=newTotal;				
	}
	
	static long getFlow(String valve) {
		return graph.containsEdge(valve, valve)? (long) graph.getEdgeWeight(graph.getEdge(valve, valve)) : 0;
	}

}