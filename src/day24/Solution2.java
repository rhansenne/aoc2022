package day24;
import java.io.*;
import java.util.*;

public class Solution2 {
	static enum Blizzard { left,right,up,down; } 	
	static class Space {
		int row,col;
		Set<Blizzard> blizzards = new HashSet<Blizzard>();
		Set<Blizzard> newBlizzards = new HashSet<Blizzard>();
		public Space(int r, int c) { row=r; col=c; }
		public boolean equals(Space s) { return row==s.row && col==s.col; }
	}
	static int time = 0;
	static Space[][] map;
	static boolean endReached=false, startReached=false;
	
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day24\\input.txt"));
		int row=0;
		List<Space[]> list = new ArrayList<Space[]>();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			Space[] spaces = new Space[line.length()];
			for (int col=0; col<line.length(); col++) {
				Space space = new Space(row,col);
				switch (line.charAt(col)) {
					case '>' : {space.blizzards.add(Blizzard.right); break;}
					case '<' : {space.blizzards.add(Blizzard.left); break;}
					case '^' : {space.blizzards.add(Blizzard.up); break;}
					case 'v' : {space.blizzards.add(Blizzard.down); break;}
					case '#' : continue; 
				}
				spaces[col]=space;
			}
			list.add(spaces);
			row++;
		}
		scanner.close();
		map = list.toArray(new Space[0][0]);
		Set<Space> potentialCurrentPositions = new HashSet<Space>();
		potentialCurrentPositions.add(map[0][1]);
		traverse(potentialCurrentPositions);
	}

	static void traverse(Set<Space> potentialCurrentPositions) {
		time++;
		updateBlizzards();
		for (Space space: potentialCurrentPositions) {
			int row = space.row, col = space.col;
			if (row==map.length-1 && col==map[0].length-2) {
				if (startReached) {
					System.out.println((time-1));
					return;
				} else if (!endReached){
					endReached=true;
					potentialCurrentPositions = new HashSet<Space>();
					potentialCurrentPositions.add(space);
					break;
				}
			}
			if (endReached && !startReached && row==0 && col==1) {
				startReached=true;
				potentialCurrentPositions = new HashSet<Space>();
				potentialCurrentPositions.add(space);
				break;
			}
		}
		Set<Space> potentialNextPositions = new HashSet<Space>();
		for (Space space: potentialCurrentPositions) {
			int row = space.row, col = space.col;
			Space right = map[row][col+1];
			Space below = row<map.length-1?map[row+1][col]:null;
			Space above = row>0?map[row-1][col]:null;
			Space left = map[row][col-1];		
			if (right!=null && right.blizzards.isEmpty()) {
				potentialNextPositions.add(right);
			} 
			if (below!=null && below.blizzards.isEmpty()) {
				potentialNextPositions.add(below);
			}
			if (above!=null && above.blizzards.isEmpty()) {
				potentialNextPositions.add(above);
			}
			if (left!=null && left.blizzards.isEmpty()) {
				potentialNextPositions.add(left);
			}
			if (space.blizzards.isEmpty())
				potentialNextPositions.add(space);
		}
		traverse(potentialNextPositions);
	}

	static void updateBlizzards() {
		for (int row=1; row<map.length-1;row++) {
			for (int col=1; col<map[0].length-1; col++) {
				Space space = map[row][col];
				Space right = map[row][col+1];
				if (right==null) right = map[row][1];
				if (right.blizzards.contains(Blizzard.left)) {
					space.newBlizzards.add(Blizzard.left);
				} 
				Space left = map[row][col-1];
				if (left==null) left = map[row][map[0].length-2];
				if (left.blizzards.contains(Blizzard.right)) {
					space.newBlizzards.add(Blizzard.right);
				}
				Space up = map[row-1][col];
				if (up==null) up = map[map.length-2][col];
				if (up.blizzards.contains(Blizzard.down)) {
					space.newBlizzards.add(Blizzard.down);
				}
				Space down = map[row+1][col];
				if (down==null) down = map[1][col];
				if (down.blizzards.contains(Blizzard.up)) {
					space.newBlizzards.add(Blizzard.up);
				}			
			}
		}
		for (int row=1; row<map.length-1;row++) {
			for (int col=1; col<map[0].length-1; col++) {
				Space space = map[row][col];
				if (space!=null) {
					space.blizzards = space.newBlizzards;
					space.newBlizzards = new HashSet<Blizzard>();
				}
			}
		}
	}
}