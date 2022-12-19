package day19;
import java.io.*;
import java.util.*;

import day19.Solution1.Material;

public class Solution2 {
	static enum Material { ore(0), clay(1), obsidian(2), geode(3); int index; Material(int i) {index=i;}  };	
	static class Blueprint {
		int id;
		int[][]costs = new int[4][4]; // cost per robot per material
		Blueprint(int id) { this.id=id; }
	}	
	static int minutes = 32;
	static int maxGeode=0;
	static int[] geodesAtMinutesLeft;
	
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day19\\input.txt"));
		List<Blueprint> blueprints = new ArrayList<Blueprint>();
		int counter=0;
		while (scanner.hasNextLine() & counter++<3) {
			String[] line = scanner.nextLine().split("Blueprint |[.:] Each ");
			Blueprint bp = new Blueprint(Integer.parseInt(line[1]));
			blueprints.add(bp);
			for (int i=2;i<line.length;i++) {
				String[] s = line[i].split(" robot| costs | and |\\.");		
				for (int j=2;j<s.length;j++) {
					String[] split = s[j].split(" ");
					bp.costs[Material.valueOf(s[0]).index][Material.valueOf(split[1]).index]=Integer.parseInt(split[0]);
				}
			}
		}
		scanner.close();
		int result = 1;
		for (Blueprint bp: blueprints) {
			maxGeode=0;
			geodesAtMinutesLeft = new int[minutes+1];
			tryAllBuildCombinations(bp, minutes, new int[] {1,0,0,0}, new int[] {0,0,0,0});			
			System.out.println("blueprint "+bp.id+" -> geodes: "+maxGeode);
			result *= maxGeode;
		}
		System.out.println(result);
	}

	static void tryAllBuildCombinations(Blueprint bp, int minutesLeft, int[] robots, int[] material) {
		if (minutesLeft == 0) {
			maxGeode = Math.max(maxGeode, material[Material.geode.index]);
			return;
		}		
	
		// dirty hack to stop suboptimal scenarios - trial & error to come to 10 as working cutoff
		if (minutesLeft < 10 && robots[Material.geode.index]<geodesAtMinutesLeft[minutesLeft]) {
				return;
		} else {
			geodesAtMinutesLeft[minutesLeft] = robots[Material.geode.index];
		}		
		
		// collect
		for (Material robot: Material.values())
			material[robot.index] += robots[robot.index];

		// build robots (geode first)
		outer: for (int i=3;i>=0;i--) {
			int[] cost = bp.costs[i];
			// try scenario where you build this robot
			int[] materialIfRobotBuilt =  new int[] {material[0],material[1],material[2],material[3]};
			for (int j=0;j<4;j++)
				materialIfRobotBuilt[j] -= cost[j];
			for (int j=0;j<4;j++) {
				if (materialIfRobotBuilt[j]<robots[j])
					continue outer; // insufficient material
			}
			int[] robotsIfRobotBuilt =  new int[] {robots[0],robots[1],robots[2],robots[3]};
			robotsIfRobotBuilt[i]++;				
			tryAllBuildCombinations(bp, minutesLeft-1, robotsIfRobotBuilt, materialIfRobotBuilt);
		}
		// don't build robots
		tryAllBuildCombinations(bp, minutesLeft-1, robots, material);
	}	
}
