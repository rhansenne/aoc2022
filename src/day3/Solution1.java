package day3;
import java.io.*;
import java.util.Scanner;

public class Solution1 {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day3\\input.txt"));
		long count=0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String s1 = line.substring(0, line.length()/2);
			String s2 = line.substring(line.length()/2, line.length());
			for (int i=0;i<s1.length();i++) {
				char c = s1.charAt(i);
				if (s2.contains(c+"")) {
					count += Character.getNumericValue(c)-9;
					if (Character.isUpperCase(c)) count += 26;
					break;
				}
			}
		}
		System.out.print(count);
		scanner.close();
	}
	
}
