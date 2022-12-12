package day2;
import java.io.*;
import java.util.Scanner;

public class Solution1 {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day2\\input.txt"));
		long total=0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			char s1 = line.charAt(0);
			char s2 = line.charAt(2);			
			switch(s2) {
				case 'X': {
					total+=1;
					switch (s1) {
						case 'A': total+=3; break; 
						case 'B': break; 
						case 'C': total+=6; break; 
					}
					break;
				}
				case 'Y': {
					total+=2;
					switch (s1) {
						case 'A': total+=6; break; 
						case 'B': total+=3; break; 
						case 'C': break; 
					}
					break;
				}
				case 'Z':{
					total+=3;
					switch (s1) {
						case 'A': break; 
						case 'B': total+=6; break; 
						case 'C': total+=3; break; 
					}
					break;
				} 
			}
		}
		System.out.print(total);
		scanner.close();
	}
	
}
