package day21;
import java.io.*;
import java.util.*;
import org.matheclipse.core.eval.ExprEvaluator;

public class Solution2 {

	static Map<String,String> monkeys = new HashMap<String,String>(); 
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day21\\input.txt"));
		while (scanner.hasNextLine()) {
			String[] line = scanner.nextLine().split(": ");
			monkeys.put(line[0], line[1]);
		}
		String[] root = monkeys.get("root").split(" ");
		System.out.println(new ExprEvaluator().eval("Solve("+expand(monkeys.get(root[0]))+"=="+expand(monkeys.get(root[2]))+",x)"));
		scanner.close();
	}

	static String expand(String expression) {
		String[] parts = expression.split(" ");
		if (parts.length==1)
			return parts[0];
		String e1 = parts[0].equals("humn")? "x":"("+expand(monkeys.get(parts[0]))+")";
		String e2 = parts[2].equals("humn")? "x":"("+expand(monkeys.get(parts[2]))+")";
		return e1 + parts[1] + e2;
	}
}
