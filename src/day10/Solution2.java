package day10;
import java.io.*;
import java.util.*;

public class Solution2 {

	static int x = 1;
	static int cycle = 0;

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day10\\input.txt"));
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			drawAndIncCycle();
			if (line.startsWith("addx")) {
				drawAndIncCycle();
				x += Integer.parseInt(line.substring(5));
			}
		}
		scanner.close();
	}

	static void drawAndIncCycle() { 
		if (++cycle>=x && cycle<=x+2) 
			System.out.print("#");
		else 
			System.out.print(".");
		if (cycle==40) {
			cycle=0;
			System.out.println();			
		}
	} 
	
}
