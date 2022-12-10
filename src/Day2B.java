import java.io.*;
import java.util.Scanner;

public class Day2B {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day2\\input.txt"));
		long total=0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			char s1 = line.charAt(0);
			char s2 = line.charAt(2);
			char choice = s2;
			switch(s2) {
				case 'X': {
					switch (s1) {
						case 'A': choice = 'Z'; break; 
						case 'B': choice = 'X'; break; 
						case 'C': choice = 'Y'; break; 
					}
					break;
				}
				case 'Y': {
					switch (s1) {
						case 'A': choice = 'X'; break; 
						case 'B': choice = 'Y'; break; 
						case 'C': choice = 'Z'; break; 
					}
					break;
				}
				case 'Z': {
					switch (s1) {
						case 'A': choice = 'Y'; break; 
						case 'B': choice = 'Z'; break; 
						case 'C': choice = 'X'; break; 
					}
					break;
				}
			}
			switch(choice) {
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
	}
	
}
