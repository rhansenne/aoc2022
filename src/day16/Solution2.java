package day16;
import java.io.*;
import java.util.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.shortestpath.*;

public class Solution2 {
	
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
		follow("AA", "AA", 0, 0, new HashSet<String>(), 26 ,0 ,0);
		System.out.println(max);
	}
	
	static void follow(String vMe, String vElephant, int distMe, int distEl, Set<String> openedValves, int timeRemaining, long flow, long total) {
		if (timeRemaining==0) {
			if (total>max)
				max=total;
			return;
		}		
		if (distMe==0 && distEl>0) {
			// I arrived at valve vMe and can select the next valve to open. Elephant is still en route.
			flow+=getFlow(vMe);
			for (Map.Entry<String,Map<String,Integer>> e: shortestPaths.entrySet()) {
				String toValve =e.getKey();
				int dist = e.getValue().get(vMe);
				if (!openedValves.contains(toValve) && timeRemaining>(dist+1)) {
					Set<String> newOpenedValves = new HashSet<String>(openedValves);
					newOpenedValves.add(toValve);			
					follow(toValve, vElephant, dist, distEl-1, newOpenedValves, timeRemaining-1, flow, total+flow);
				}
			}
			// nothing more to do for me - Elephant continues alone
			follow(vMe, vElephant, Integer.MAX_VALUE, distEl-1, openedValves, timeRemaining-1, flow, total+flow);
		} else if (distMe>0 && distEl==0) {
			// Elephant arrived at valve vElephant and can select the next valve to open. I am still en route.
			flow+=getFlow(vElephant);
			for (Map.Entry<String,Map<String,Integer>> e: shortestPaths.entrySet()) {
				String toValve =e.getKey();
				int dist = e.getValue().get(vElephant);
				if (!openedValves.contains(toValve) && timeRemaining>(dist+1)) {
					Set<String> newOpenedValves = new HashSet<String>(openedValves);
					newOpenedValves.add(toValve);			
					follow(vMe, toValve, distMe-1, dist, newOpenedValves, timeRemaining-1, flow, total+flow);
				}
			}
			// nothing more to do for elephant - I continue alone
			follow(vMe, vElephant, distMe-1, Integer.MAX_VALUE, openedValves, timeRemaining-1, flow, total+flow);
		} else if (distMe==0 && distEl==0) {
			// Both Elephant and I arrived and can select the next valve to open.
			flow+=getFlow(vMe);
			flow+=getFlow(vElephant);
			// my selection
			for (String myNextValve: shortestPaths.keySet()) {
				int myNextDist = shortestPaths.get(myNextValve).get(vMe);
				if (!openedValves.contains(myNextValve) && timeRemaining>(myNextDist+1)) {
					Set<String> newOpenedValves = new HashSet<String>(openedValves);
					newOpenedValves.add(myNextValve);
					for (String elephantNextValve: shortestPaths.keySet()) {
						int elephantNextDist = shortestPaths.get(elephantNextValve).get(vElephant);
						if (!newOpenedValves.contains(elephantNextValve) && timeRemaining>(elephantNextDist+1)) {
							Set<String> newOpenedValves2 = new HashSet<String>(newOpenedValves);
							newOpenedValves2.add(elephantNextValve);			
							follow(myNextValve, elephantNextValve, myNextDist, elephantNextDist, newOpenedValves2, timeRemaining-1, flow, total+flow);
						}
					}
				}
			}
		} else {
			// we are both still en route.
			follow(vMe, vElephant, distMe-1, distEl-1, openedValves, timeRemaining-1, flow, total+flow);
		}
	}
	
	static long getFlow(String valve) {
		return graph.containsEdge(valve, valve)? (long) graph.getEdgeWeight(graph.getEdge(valve, valve)) : 0;
	}

}