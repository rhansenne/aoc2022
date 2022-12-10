import java.io.*;
import java.util.Scanner;

public class Day6 {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day6\\input.txt"));
		String line = scanner.nextLine();
		String seq = line.substring(0,14);
		for (int i=4;i<line.length();i++) {
			boolean dup = false;
			for (int j=0;j<seq.length();j++)
				for (int k=j+1;k<seq.length();k++) 
					if (seq.charAt(j) == seq.charAt(k))
						dup = true;
			if (!dup) {
				System.out.println(i);
				break;
			}
			seq = seq.substring(1,seq.length()) + line.charAt(i);
		}
	}
	
}
