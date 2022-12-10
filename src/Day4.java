import java.io.*;
import java.util.*;

public class Day4 {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day4\\input.txt"));
		int count=0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] sections = line.split(",");
			String[] s1 = sections[0].split("-");
			String[] s2 = sections[1].split("-");
			int i1 = Integer.parseInt(s1[0]);
			int i2 = Integer.parseInt(s1[1]);
			int i3 = Integer.parseInt(s2[0]);
			int i4 = Integer.parseInt(s2[1]);			
			if ((i1<=i3 && i2>=i4)||(i3<=i1&&i4>=i2)) count++;
		}
		System.out.print(count);
	}
	
}
