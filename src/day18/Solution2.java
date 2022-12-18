package day18;
import java.io.*;
import java.util.*;

public class Solution2 {

	static int minX=0, maxX=0, minY=0, maxY=0, minZ=0, maxZ=0;
	static List<int[]> scan = new ArrayList<int[]>();
	static boolean[][][] steamFilled;

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day18\\input.txt"));
		while (scanner.hasNextLine())			
			scan.add(Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray());
		scanner.close();
		for (int[] co: scan) {
			maxX = Math.max(maxX, co[0]+1);
			maxY = Math.max(maxY, co[1]+1);
			maxZ = Math.max(maxZ, co[2]+1);
		}
		// fill free space with steam and count sides touching steam
		steamFilled = new  boolean[maxX+1][maxY+1][maxZ+1];
		fillNeighbours(new int[] {minX,minY,minZ});
		int touchingSteam=0;
		for (int[] co: scan) {
			int[][] adjacant = new int[][] { {co[0]+1,co[1],co[2]},{co[0]-1,co[1],co[2]},{co[0],co[1]+1,co[2]},{co[0],co[1]-1,co[2]},{co[0],co[1],co[2]+1}, {co[0],co[1],co[2]-1} };
			for (int[] adj: adjacant)
				if (outOfBounds(adj) || steamFilled[adj[0]][adj[1]][adj[2]]) touchingSteam++;
		}
		System.out.println(touchingSteam);
	}
	
	static void fillNeighbours(int[] co) {
		if (outOfBounds(co) || steamFilled[co[0]][co[1]][co[2]] || scanContains(co)) return;
		steamFilled[co[0]][co[1]][co[2]]=true;
		fillNeighbours(new int[] {co[0]-1,co[1],co[2]});
		fillNeighbours(new int[] {co[0]+1,co[1],co[2]});
		fillNeighbours(new int[] {co[0],co[1]-1,co[2]});
		fillNeighbours(new int[] {co[0],co[1]+1,co[2]});
		fillNeighbours(new int[] {co[0],co[1],co[2]-1});
		fillNeighbours(new int[] {co[0],co[1],co[2]+1});		
	}
	
	static boolean outOfBounds(int[] co) {
		return co[0]<minX || co[0]>maxX || co[1]<minY || co[1]>maxY || co[2]<minZ || co[2]>maxZ;
	}
	
	static boolean scanContains(int[] co) {
		for (int[] s:scan)
			if (Arrays.equals(s, co)) return true;
		return false;
	}
}
