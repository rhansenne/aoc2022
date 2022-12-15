package day15;
import java.io.*;
import java.util.*;

import math.geom2d.*;
import math.geom2d.line.*;
import math.geom2d.polygon.*;

public class Solution2 {
	
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day15\\input.txt"));
		List<int[]> sr = new ArrayList<int[]>();
		while (scanner.hasNextLine()) {
			String[] co = scanner.nextLine().split("Sensor at x=|, y=|: closest beacon is at x=");
			int sX = Integer.parseInt(co[1]);
			int sY = Integer.parseInt(co[2]);
			int bX = Integer.parseInt(co[3]);
			int bY = Integer.parseInt(co[4]);
			int mhDist = Math.abs(bX-sX) + Math.abs(bY-sY);
			sr.add(new int[] {sX,sY, mhDist});
		}
		scanner.close();

		// find sensor range intersections - the free position must be on one of these rows
		int min=0,max=4000000;
		Set<Integer> ys = new HashSet<Integer>();	
		for (int i=0;i<sr.size();i++) {
			LinearRing2D s1 = toLine(sr.get(i));
			for (int j=i+1;j<sr.size();j++) {
				LinearRing2D s2 = toLine(sr.get(j));
				for (LineSegment2D ls: s2.edges()) {
					for (Point2D p: s1.intersections(ls)) {
						int y = (int) p.getY();
						if (y>=min && y<=max) ys.add(y);
					}
				}
			}
		}
		
		Set<Integer> noBeacon = new HashSet<Integer>();
		for (int y:ys) {
			noBeacon.clear();
			for (int[] co:sr) {
				int sX = co[0];
				int sY = co[1];
				int mhDist = co[2];
				int yDist = Math.abs(y-sY);
				if (yDist<=mhDist) {
					int l = mhDist-yDist;
					for (int x=Math.max(min,sX-l);x<=Math.min(max,sX+l);x++)
						noBeacon.add(x);
				}			
			}
			if (noBeacon.size()!=max+1) {
				for (int x=min;x<=max;x++) {
					if (!noBeacon.contains(x))
						System.out.println((long)x*4000000+y);
				}
				break;
			}
		}
	}
	
	static LinearRing2D toLine(int[] sensor) {
		return new LinearRing2D(
				new double[] {sensor[0],sensor[0]+sensor[2],sensor[0],sensor[0]-sensor[2]},				
				new double[] {sensor[1]-sensor[2],sensor[1],sensor[1]+sensor[2],sensor[1]}						
				);
	}

}