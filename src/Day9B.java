import java.io.*;
import java.util.*;

public class Day9B {

	static Set visited = new HashSet();
	static int x[] = new int[10];
	static int y[] = new int[10];

	public static void main(String[] args) throws Exception {
		visited.add(x[0]+"_"+y[0]);
		Scanner scanner = new Scanner(new File("src\\day9\\input.txt"));
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			char dir = line.charAt(0);
			int steps = Integer.parseInt(line.substring(2));
			for (int i=0;i<steps;i++) {
				switch (dir) {
					case 'R': { x[0]++; updateTail(); break; }
					case 'L': { x[0]--; updateTail(); break; }
					case 'U': { y[0]++; updateTail(); break; }
					case 'D': { y[0]--; updateTail(); break; }
				}
			}
			
		}
		scanner.close();
		System.out.println(visited.size());
	}
	
	static void updateTail() {
		for (int i=0;i<9;i++) {
			if (x[i]==x[i+1]+2) {
				x[i+1]++;
				if (y[i]>y[i+1]) y[i+1]++; else if (y[i]<y[i+1]) y[i+1]--;
			} else if (x[i]==x[i+1]-2) {
				x[i+1]--;
				if (y[i]>y[i+1]) y[i+1]++; else if (y[i]<y[i+1]) y[i+1]--;
			} else if (y[i]==y[i+1]+2) {
				y[i+1]++;
				if (x[i]>x[i+1]) x[i+1]++; else if (x[i]<x[i+1]) x[i+1]--;
			} else if (y[i]==y[i+1]-2) {
				y[i+1]--;
				if (x[i]>x[i+1]) x[i+1]++; else if (x[i]<x[i+1]) x[i+1]--;
			}				
		}		
		visited.add(x[9]+"_"+y[9]);
	}
	
}
