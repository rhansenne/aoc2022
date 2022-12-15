package day15;
import java.io.*;
import java.util.*;

public class Solution1 {
	
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day15\\input.txt"));
		int y=2000000;
		Set<Integer> noBeacon = new HashSet<Integer>();
		Set<Integer> beacon = new HashSet<Integer>();
		while (scanner.hasNextLine()) {
			String[] co = scanner.nextLine().split("Sensor at x=|, y=|: closest beacon is at x=");
			int sX = Integer.parseInt(co[1]);
			int sY = Integer.parseInt(co[2]);
			int bX = Integer.parseInt(co[3]);
			int bY = Integer.parseInt(co[4]);
			if (bY==y) beacon.add(bX);
			int mhDist = Math.abs(bX-sX) + Math.abs(bY-sY);
			int yDist = Math.abs(y-sY);
			if (yDist<=mhDist) {
				int l = mhDist-yDist;
				for (int x=sX-l;x<=sX+l;x++)
					noBeacon.add(x);
			}
		}
		noBeacon.removeAll(beacon);
		System.out.println(noBeacon.size());
		scanner.close();
	}

}