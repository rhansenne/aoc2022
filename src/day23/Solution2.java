package day23;
import java.io.*;
import java.util.*;

public class Solution2 {

	static int[][] map;
	static int border = 100;
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
		map = new int[mapList.size()+2*border][mapList.get(0).length+2*border];
		for (int i=0; i<mapList.size();i++) {
			System.arraycopy(mapList.get(i), 0, map[border+i], border, mapList.get(i).length);
		}
		intentions = new char[map.length+2*border][map[0].length+2*border];
		int round=1;
		rounds: while(true) {			
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
			boolean move = false;
			for (int i=0; i<map.length; i++) {
				for (int j=0; j<map[0].length; j++) {
					switch (intentions[i][j]) {
						case 'N': {
							if (map[i-1][j]==1) {
								map[i-1][j]=-1;
								map[i][j]=0;
								move=true;
							}
							break;
						}
						case 'S': {
							if (map[i+1][j]==1) {
								map[i+1][j]=-1;
								map[i][j]=0;
								move=true;
							}
							break;
						}
						case 'E': {
							if (map[i][j+1]==1) {
								map[i][j+1]=-1;
								map[i][j]=0;
								move=true;
							}
							break;
						}
						case 'W': {
							if (map[i][j-1]==1) {
								map[i][j-1]=-1;
								map[i][j]=0;
								move=true;
							}
						}
					}
				}
			}
			if (!move) {
				System.out.println(round);
				break rounds;
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
			round++;
		}	
	}
	

}
