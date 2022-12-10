import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Day5 {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day5\\input.txt"));
		
		LinkedList<Character> l1 = new LinkedList<Character>();
		LinkedList<Character> l2 = new LinkedList<Character>();
		LinkedList<Character> l3 = new LinkedList<Character>();
		LinkedList<Character> l4 = new LinkedList<Character>();
		LinkedList<Character> l5 = new LinkedList<Character>();
		LinkedList<Character> l6 = new LinkedList<Character>();
		LinkedList<Character> l7 = new LinkedList<Character>();
		LinkedList<Character> l8 = new LinkedList<Character>();
		LinkedList<Character> l9 = new LinkedList<Character>();
		
		l1.add('Q');
		l1.add('M');
		l1.add('G');
		l1.add('C');
		l1.add('L');
		
		l2.add('R');
		l2.add('D');
		l2.add('L');
		l2.add('C');
		l2.add('T');
		l2.add('F');
		l2.add('H');
		l2.add('G');
		
		l3.add('V');
		l3.add('J');
		l3.add('F');
		l3.add('N');
		l3.add('M');
		l3.add('T');
		l3.add('W');
		l3.add('R');

		l4.add('J');
		l4.add('F');
		l4.add('D');
		l4.add('V');
		l4.add('Q');
		l4.add('P');

		l5.add('N');
		l5.add('F');
		l5.add('M');
		l5.add('S');
		l5.add('L');
		l5.add('B');
		l5.add('T');

		l6.add('R');
		l6.add('N');
		l6.add('V');
		l6.add('H');
		l6.add('C');
		l6.add('D');
		l6.add('P');

		l7.add('H');
		l7.add('C');
		l7.add('T');

		l8.add('G');
		l8.add('S');
		l8.add('J');
		l8.add('V');
		l8.add('Z');
		l8.add('N');
		l8.add('H');
		l8.add('P');

		l9.add('Z');
		l9.add('F');
		l9.add('H');
		l9.add('G');

		LinkedList[] lists = new LinkedList[] {l1,l2,l3,l4,l5,l6,l7,l8,l9};
		
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (!line.startsWith("move")) continue;
			String[] split = line.split(" ");
			int amount = Integer.parseInt(split[1]);
			int from = Integer.parseInt(split[3]);
			int to = Integer.parseInt(split[5]);
			LinkedList fromList = lists[from-1];
			LinkedList toList = lists[to-1];
			LinkedList tempList = new LinkedList<Character>();
			for (int i=0;i<amount;i++) tempList.add(fromList.removeLast());
			for (int i=0;i<amount;i++) toList.add(tempList.removeLast());
		}

		for (int i=0;i<9;i++) System.out.print(lists[i].removeLast());
	
	}
	
	
	
}
