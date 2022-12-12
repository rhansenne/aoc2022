package day1;
import java.io.*;
import java.util.Scanner;

public class Solution2 {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day1\\input.txt"));
		long max1=0,max2=0,max3=0,count=0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.isEmpty()) {
				if (count >= max1) {
					max3 = max2;
					max2= max1;
					max1 = count;
				} else if (count >= max2) {
					max3 = max2;
					max2= count;					
				} else if (count >= max3) 
					max3 = count;
				count = 0;
			} else
				count += Long.parseLong(line);				
		}
		System.out.print(max3+max2+max1);
		scanner.close();
	}
	
}
