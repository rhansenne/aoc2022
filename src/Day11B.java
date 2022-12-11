import java.io.*;
import java.util.*;

public class Day11B {

	static Map<String,Monkey> monkeys = new TreeMap<String,Monkey>();
	
	static class Monkey {
		List<Integer> items = new ArrayList<Integer>();
		String op;
		int test;
		String ifTrue;
		String ifFalse;
		int inspected;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day11\\input.txt"));
		int mod=1;
		while (scanner.hasNextLine()) {
			String monkeyId = scanner.nextLine().substring(7,8);
			Monkey m = new Monkey() {};			
			String[] items = scanner.nextLine().substring(18).split(", ");
			for (String item:items)
				m.items.add(Integer.parseInt(item));
			m.op = scanner.nextLine().substring(19);
			m.test = Integer.parseInt(scanner.nextLine().substring(21));
			mod*=m.test;
			m.ifTrue =	scanner.nextLine().substring(29);
			m.ifFalse = scanner.nextLine().substring(30);
			monkeys.put(monkeyId, m);
			if (scanner.hasNextLine()) scanner.nextLine();
		}
		scanner.close();
		for (int round=0; round<10000; round++)
			for (Monkey m: monkeys.values()) {
				for (int item: m.items) {
					String operation = m.op.replaceAll("old", Integer.toString(item));
					String op[] = operation.split(" ");
					long worry = Integer.parseInt(op[0]);						
					switch (op[1].charAt(0)) {
						case '+': worry+=Integer.parseInt(op[2]); break;
						case '*': worry*=Integer.parseInt(op[2]);
					}
					worry %= mod;					
					if (worry % m.test == 0)
						monkeys.get(m.ifTrue).items.add((int)worry);
					else
						monkeys.get(m.ifFalse).items.add((int)worry);
				}
				m.inspected += m.items.size();
				m.items.clear();
			}
		
		long max1=0, max2=0;
		for (Monkey m: monkeys.values()) {		
			if (m.inspected>max1) {
				max2 = max1;
				max1=m.inspected;
			} else if (m.inspected>max2)
				max2=m.inspected;
		}
		System.out.println(max1*max2);
	}

}