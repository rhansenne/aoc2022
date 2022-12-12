package day1;
import java.io.*;
import java.util.*;

public class Solution1 {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day1\\input.txt"));
		long max=0,count=0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.isEmpty()) {
				if (count > max) max = count;
				count = 0;
			} else
				count += Long.parseLong(line);
				
		}
		System.out.print(max);
		scanner.close();
	}
	
}
