package day3;
import java.io.*;
import java.util.Scanner;

public class Solution2 {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day3\\input.txt"));
		long count=0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String line2 = scanner.nextLine();
			String line3 = scanner.nextLine();
			char badge = ' ';
			for (char c='a';c<='z';c++) 
				if (line.contains(c+"") && line2.contains(c+"") && line3.contains(c+"")) badge = c;
			for (char c='A';c<='Z';c++)
				if (line.contains(c+"") && line2.contains(c+"") && line3.contains(c+"")) badge = c;
			count += Character.getNumericValue(badge)-9;
			if (Character.isUpperCase(badge))
				count += 26;			
		}
		System.out.print(count);
		scanner.close();
	}
	
}
