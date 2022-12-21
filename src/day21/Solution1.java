package day21;
import java.io.*;
import java.util.*;

public class Solution1 {

	static Map<String,String> monkeys = new HashMap<String,String>(); 
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day21\\input.txt"));
		while (scanner.hasNextLine()) {
			String[] line = scanner.nextLine().split(": ");
			monkeys.put(line[0], line[1]);
		}
		System.out.println(solve(monkeys.get("root")));
		scanner.close();
	}

	static long solve(String expression) {
		String[] parts = expression.split(" ");
		if (parts.length==1)
			return Long.parseLong(parts[0]);
		switch(parts[1].charAt(0)) {
			case '+': return solve(monkeys.get(parts[0]))+solve(monkeys.get(parts[2]));
			case '-': return solve(monkeys.get(parts[0]))-solve(monkeys.get(parts[2]));
			case '*': return solve(monkeys.get(parts[0]))*solve(monkeys.get(parts[2]));
			default : return solve(monkeys.get(parts[0]))/solve(monkeys.get(parts[2]));
		}
	}
}
