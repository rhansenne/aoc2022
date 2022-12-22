package day22;
import java.io.*;
import java.util.*;

public class Solution1 {

	static enum Facing { right(0), down(1), left(2), up(3); int val; Facing(int v) {val=v;}}; 
	
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day22\\input.txt"));
		List<int[]> mapList = new ArrayList<int[]>();
		String instructions="";
		int length=0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.isEmpty()) {
				instructions = scanner.nextLine();
				break;
			}
			if (line.length()>length) {
				length = line.length();
				for (int[] i : mapList)
					i = Arrays.copyOf(i, length); 
			}			
			int[] row = new int[length];
			for (int i=0; i<line.length();i++) {
					switch(line.charAt(i)) {
					case '.': row[i]=1; break;
					case '#': row[i]=2; break;
					default: row[i]=0;
				}
			}
			mapList.add(row);			
		}
		scanner.close();

		String[] instr = instructions.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");
		int[][] map = mapList.toArray(new int[0][0]);
		int facing = Facing.right.val;
		int iPos=0, jPos=0;
		while (map[iPos][jPos]!=0) jPos++;
		outer: for (int ins=0; ins<instr.length; ins++) {
			if (instr[ins].equals("R")) facing = (facing+1)%4;
			else if (instr[ins].equals("L")) facing = (facing+3)%4;
			else {
				int steps = Integer.parseInt(instr[ins]);
				if (facing==Facing.right.val)
					for (int i=0;i<steps; i++) {
						int prevJPos = jPos;
						if ((jPos+1)==map[iPos].length || map[iPos][jPos+1]==0) {
							jPos=0;
							while (map[iPos][jPos]==0) jPos++;
							if (map[iPos][jPos]==2) {
								jPos = prevJPos;
								continue outer;
							}
						}
						else if (map[iPos][jPos+1]==2) continue outer;
						else jPos++;
					}
				if (facing==Facing.left.val)
					for (int i=0;i<steps; i++) {
						int prevJPos = jPos;
						if ((jPos-1)<0 || map[iPos][jPos-1]==0) {
							jPos=map[iPos].length-1;
							while (map[iPos][jPos]==0) jPos--;						
							if (map[iPos][jPos]==2) {
								jPos = prevJPos;
								continue outer;
							}
						}
						else if (map[iPos][jPos-1]==2) continue outer;
						else jPos--;
					}
				if (facing==Facing.up.val)
					for (int i=0;i<steps; i++) {
						int prevIPos = iPos;
						if ((iPos-1)<0 || map[iPos-1][jPos]==0) {
							iPos=map.length-1;
							while (map[iPos][jPos]==0) iPos--;
							if (map[iPos][jPos]==2) {
								iPos = prevIPos;
								continue outer;
							}							
						} 
						else if (map[iPos-1][jPos]==2) continue outer;
						else iPos--;
					}
				if (facing==Facing.down.val)
					for (int i=0;i<steps; i++) {
						int prevIPos = iPos;
						if ((iPos+1)==map.length || map[iPos+1][jPos]==0) {
							iPos=0;
							while (map[iPos][jPos]==0) iPos++;	
							if (map[iPos][jPos]==2) {
								iPos = prevIPos;
								continue outer;
							}								
						} 
						else if (map[iPos+1][jPos]==2) continue outer;
						else iPos++;
					}
			}
		}
		System.out.println(1000*(iPos+1)+4*(jPos+1)+facing);
	}
	
}