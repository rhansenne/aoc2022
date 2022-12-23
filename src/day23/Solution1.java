package day23;
import java.io.*;
import java.util.*;

public class Solution1 {

	static int[][] map;
	static int rounds = 10;
	static char[][] intentions;
	static List<Character> directions = new ArrayList<Character>(Arrays.asList('N','S','W','E'));
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day23\\input.txt"));
		List<int[]> mapList = new ArrayList<int[]>();		
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			int[] row = new int[line.length()];
			for (int i=0; i<line.length();i++)
				if (line.charAt(i) == '.') row[i]=0; else row[i]=-1;
			mapList.add(row);
		}
		scanner.close();
		map = new int[mapList.size()+2*rounds][mapList.get(0).length+2*rounds];
		for (int i=0; i<mapList.size();i++) {
			System.arraycopy(mapList.get(i), 0, map[rounds+i], rounds, mapList.get(i).length);
		}
		intentions = new char[map.length+2*rounds][map[0].length+2*rounds];
		for (int round=0; round<rounds; round++) {			
			for (int i=0; i<map.length; i++) {
				for (int j=0; j<map[0].length; j++) {
					if (map[i][j]==-1) {
						if (map[i-1][j]>=0 && map[i-1][j-1]>=0 && map[i-1][j+1]>=0 && map[i+1][j]>=0 && map[i+1][j-1]>=0 && map[i+1][j+1]>=0 && map[i][j-1]>=0 && map[i][j+1]>=0)
								continue;		
						dirs: for (char dir: directions) {
							switch (dir) {
								case 'N': {
									if (i>0 && map[i-1][j-1]>=0 && map[i-1][j]>=0 && map[i-1][j+1]>=0) {
										map[i-1][j]++;
										intentions[i][j]=dir;
										break dirs;	
									}
									break;
								}
								case 'S': {
									if (map[i+1][j-1]>=0 && map[i+1][j]>=0 && map[i+1][j+1]>=0) {
										map[i+1][j]++;
										intentions[i][j]=dir;
										break dirs;	
									}
									break;
								}
								case 'E': {
									if (map[i-1][j+1]>=0 && map[i][j+1]>=0 && map[i+1][j+1]>=0) {
										map[i][j+1]++;
										intentions[i][j]=dir;
										break dirs;	
									}
									break;
								}
								case 'W': {
									if ( map[i-1][j-1]>=0 && map[i][j-1]>=0 && map[i+1][j-1]>=0) {
										map[i][j-1]++;
										intentions[i][j]=dir;
										break dirs;	
									}
								}
							}
						}
					}
				}
			}
			for (int i=0; i<map.length; i++) {
				for (int j=0; j<map[0].length; j++) {
					switch (intentions[i][j]) {
						case 'N': {
							if (map[i-1][j]==1) {
								map[i-1][j]=-1;
								map[i][j]=0;
							}
							break;
						}
						case 'S': {
							if (map[i+1][j]==1) {
								map[i+1][j]=-1;
								map[i][j]=0;
							}
							break;
						}
						case 'E': {
							if (map[i][j+1]==1) {
								map[i][j+1]=-1;
								map[i][j]=0;
							}
							break;
						}
						case 'W': {
							if (map[i][j-1]==1) {
								map[i][j-1]=-1;
								map[i][j]=0;
							}
						}
					}
				}
			}
			for (int i=0; i<map.length; i++) {
				for (int j=0; j<map[0].length; j++) {
					if (map[i][j]>0)
						map[i][j]=0;
				}
			}
			char d = directions.get(0);
			directions.remove(0);
			directions.add(d);		
			intentions = new char[map.length][map[0].length];	
		}
		int rowStart=0;
		while(sumRow(rowStart)==0) rowStart++;
		int rowEnd=map.length-1;
		while(sumRow(rowEnd)==0) rowEnd--;
		int colStart=0;
		while(sumCol(colStart)==0) colStart++;
		int colEnd=map[0].length-1;
		while(sumCol(colEnd)==0) colEnd--;
		int empty=0;
		for (int i=rowStart; i<=rowEnd; i++)
			for (int j=colStart; j<=colEnd; j++)
				if (map[i][j]==0) empty++;
		System.out.println(empty);		
	}
	
	static int sumRow(int i) {
		int sum=0;
		for (int j=0; j<map[i].length;j++) sum+=map[i][j];
		return sum;
	}

	static int sumCol(int j) {
		int sum=0;
		for (int i=0; i<map.length;i++) sum+=map[i][j];
		return sum;
	}
}
