package day14;
import java.io.*;
import java.util.*;

public class Solution1 {
	
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day14\\input.txt"));
		List<List<int[]>> rocks = new ArrayList<List<int[]>>();		
		int minX=500, maxX=500, maxY=0;
		while (scanner.hasNextLine()) {
			List<int[]> rock = new ArrayList<int[]>();
			String[] line = scanner.nextLine().split(" -> |,");
			for (int i=0; i<line.length; i+=2) {
				int x=Integer.parseInt(line[i]), y=Integer.parseInt(line[i+1]);
				rock.add(new int[] {x,y});
				minX = Math.min(x, minX);
				maxX = Math.max(x, maxX);
				maxY = Math.max(y, maxY);
			}
			rocks.add(rock);
		}
		scanner.close();

		// build scan matrix
		boolean[][] scan = new boolean[maxX-minX+1][maxY+1];
		for (List<int[]> rock: rocks) {
			int[] prevCo = rock.get(0);
			for (int i=1;i<rock.size();i++) {
				int[] nextCo=rock.get(i);
				if (prevCo[0]==nextCo[0])
					for (int y=Math.min(prevCo[1], nextCo[1]); y<=Math.max(prevCo[1], nextCo[1]); y++) 
						scan[prevCo[0]-minX][y]=true;
				else
					for (int x=Math.min(prevCo[0], nextCo[0]); x<=Math.max(prevCo[0], nextCo[0]); x++) 
						scan[x-minX][prevCo[1]]=true;
				prevCo=nextCo;
			}
		}
		
		// drop sand
		int x = 500, y=0, count=0;
		do {
			if (scan[x-minX][y]) {				
				if (!scan[x-minX-1][y])	x--;
				else if (!scan[x-minX+1][y]) x++;
				else {
					scan[x-minX][y-1] = true;
					y=-1;
					x=500;
					count++;
				}
			}
			y++;			
		} while (x>minX && x<maxX && y<=maxY);	
		System.out.println(count);
	}

}