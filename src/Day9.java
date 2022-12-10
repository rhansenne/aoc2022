import java.io.*;
import java.util.*;

public class Day9 {

	static Set visited = new HashSet();
	static int xH = 0;
	static int yH = 0;
	static int xT = 0;
	static int yT = 0;

	public static void main(String[] args) throws Exception {
		visited.add(xT+"_"+yT);
		Scanner scanner = new Scanner(new File("src\\day9\\input.txt"));
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			char dir = line.charAt(0);
			int steps = Integer.parseInt(line.substring(2));
			for (int i=0;i<steps;i++) {
				switch (dir) {
					case 'R': { xH++; updateTail(); break; }
					case 'L': { xH--; updateTail(); break; }
					case 'U': { yH++; updateTail(); break; }
					case 'D': { yH--; updateTail(); break; }
				}
			}
			
		}
		scanner.close();
		System.out.println(visited.size());
	}
	
	static void updateTail() {
		if (xH==xT+2) {
			xT++;
			if (yH>yT) yT++; else if (yH<yT) yT--;
		} else if (xH==xT-2) {
			xT--;
			if (yH>yT) yT++; else if (yH<yT) yT--;
		} else if (yH==yT+2) {
			yT++;
			if (xH>xT) xT++; else if (xH<xT) xT--;
		} else if (yH==yT-2) {
			yT--;
			if (xH>xT) xT++; else if (xH<xT) xT--;
		}
		visited.add(xT+"_"+yT);
	}
	
}
