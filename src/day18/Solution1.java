package day18;
import java.io.*;
import java.util.*;

public class Solution1 {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day18\\input.txt"));
		List<int[]> scan = new ArrayList<int[]>();
		while (scanner.hasNextLine())			
			scan.add(Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray());
		scanner.close();
		int notTouching=0;
		for (int[] co1: scan) {
			int sidesTouching=0;
			for (int[] co2: scan) {
				int sameCo=0, nearCo=0;
				for (int i=0;i<3;i++)
					if (co1[i]==co2[i]) sameCo++;
						else if (Math.abs(co1[i]-co2[i])==1) nearCo++;
				if (sameCo==2 && nearCo==1) sidesTouching++;
			}
			notTouching += 6-sidesTouching;
		}
		System.out.println(notTouching);
	}
	
}
