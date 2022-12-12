package day6;
import java.io.*;
import java.util.Scanner;

public class Solution2 {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day6\\input.txt"));
		String line = scanner.nextLine();
		String seq = line.substring(0,4);
		for (int i=4;i<line.length();i++) {
			if (
					seq.charAt(0) != seq.charAt(1) && 
					seq.charAt(0) != seq.charAt(2) && 
					seq.charAt(0) != seq.charAt(3) && 
					seq.charAt(1) != seq.charAt(2) && 
					seq.charAt(1) != seq.charAt(3) && 
					seq.charAt(2) != seq.charAt(3) 
				) {
				System.out.println(seq);
				break;
			}
			seq = seq.substring(1,4) + line.charAt(i);
		}
		scanner.close();
	}
	
}
