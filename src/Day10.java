import java.io.*;
import java.util.*;

public class Day10 {

	static int x = 1;
	static int cycle = 0;
	static int strength = 0;

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day10\\input.txt"));
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			checkAndIncCycle();
			if (line.startsWith("addx")) {
				checkAndIncCycle();
				x += Integer.parseInt(line.substring(5));
			}
		}
		scanner.close();
		System.out.print(strength);
	}

	static void checkAndIncCycle() { if ((++cycle-20)%40==0) strength += cycle*x; } 
	
}
