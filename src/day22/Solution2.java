package day22;
import java.io.*;
import java.util.*;

public class Solution2 {

	static int iPos=0, jPos=0;
	static int[][] map;
	static int mapRotation=0;
	static int tileSize = 50;
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day22\\input.txt"));
		List<int[]> mapList = new ArrayList<int[]>();
		int length=0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.isEmpty()) break;
			if (line.length()>length) {
				length = line.length();
				for (int i=0;i<mapList.size();i++)
					mapList.set(i, Arrays.copyOf(mapList.get(i), length)); 
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
		String[] instr = scanner.nextLine().split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");
		scanner.close();
		map = mapList.toArray(new int[0][0]);
		while (map[iPos][jPos]==0) jPos++;	
		outer: for (int ins=0; ins<instr.length; ins++) {
			if (instr[ins].equals("L")) rotateMapCW();
			else if (instr[ins].equals("R"))
				for (int i=0;i<3;i++) rotateMapCW();
			else {
				int steps = Integer.parseInt(instr[ins]);
				for (int i=0;i<steps; i++) {
					if ((jPos+1)==map[iPos].length) {
						if (iPos>=2*tileSize && map[iPos-tileSize][jPos] == 0 &&  map[iPos - tileSize - 2*(iPos%tileSize)-1][jPos - tileSize]!=0) {
							int newiPos = iPos - tileSize - 2*(iPos%tileSize)-1;
							int newjPos = jPos - tileSize;
							if (map[newiPos][newjPos]==1) {
								iPos = newiPos;
								jPos = newjPos;
								rotateMapCW();
								rotateMapCW();
							}
							else continue outer;
						} else if (iPos<2*tileSize && map[iPos+tileSize][jPos] == 0 && map[iPos + tileSize + 2*(tileSize-iPos%tileSize)-1][jPos - tileSize]!=0) {
							int newiPos = iPos + tileSize + 2*(tileSize-iPos%tileSize)-1;
							int newjPos = jPos - tileSize;
							if (map[newiPos][newjPos]==1) {
								iPos = newiPos;
								jPos = newjPos;
								rotateMapCW();
								rotateMapCW();
							}
							else continue outer;								
						} else if (jPos==4*tileSize-1 && iPos>=2*tileSize) {
							int newiPos = iPos-2*tileSize;
							int newjPos = 0;
							if (map[newiPos][newjPos]==1) {
								iPos = newiPos;
								jPos = newjPos;
							}
							else continue outer;											
						} else if (iPos<tileSize) {
							int newiPos = map.length-1;
							int newjPos = tileSize + iPos;
							if (map[newiPos][newjPos]==1) {
								iPos = newiPos;
								jPos = newjPos;
								rotateMapCW();
							}
							else continue outer;								
						} else {
							int newiPos = 0;
							int newjPos = tileSize - (iPos%tileSize) -1;
							if (map[newiPos][newjPos]==1) {
								iPos = newiPos;
								jPos = newjPos;
								for (int r=0;r<3;r++) rotateMapCW();
							}
							else continue outer;															
						}
					}
					else if (map[iPos][jPos+1]==0) {
						if ((iPos+tileSize)<map.length && map[iPos+tileSize][jPos+1]!=0) {
							int newiPos = iPos + (tileSize-iPos%tileSize);
							int newjPos = jPos + (tileSize-iPos%tileSize);
							if (map[newiPos][newjPos]==1) {
								iPos = newiPos;
								jPos = newjPos;
								for (int r=0;r<3;r++) rotateMapCW();
							}
							else continue outer;
						} else if ((iPos-tileSize)>=0 && map[iPos-tileSize][jPos+1]!=0) {								
							int newiPos = iPos - (iPos%tileSize + 1);
							int newjPos = jPos + (iPos%tileSize + 1);
							if (map[newiPos][newjPos]==1) {
								iPos = newiPos;
								jPos = newjPos;
								rotateMapCW();			
							} 
							else continue outer;
						} else {
							if ((iPos+2*tileSize)<map.length && map[iPos+2*tileSize][jPos+1]!=0) {
								int newiPos = iPos + tileSize + 2*(tileSize-iPos%tileSize)-1; 								
								int newjPos = jPos + tileSize;
								if (map[newiPos][newjPos]==1) {
									iPos = newiPos;
									jPos = newjPos;
									rotateMapCW();
									rotateMapCW();
								}
								else continue outer;
							} else {								
								int newiPos = iPos - tileSize - 2*(iPos%tileSize)-1; 
								int newjPos = jPos + tileSize;
								if (map[newiPos][newjPos]==1) {
									iPos = newiPos;
									jPos = newjPos;
									rotateMapCW();
									rotateMapCW();
								} 
								else continue outer;
							}
						}					
					}
					else if (map[iPos][jPos+1]==2) continue outer;
					else jPos++;
				}
			}
		}
		int facing = (4-(mapRotation%4))%4;	
		for (int i=0;i<facing;i++) rotateMapCW();
		System.out.println(1000*(iPos+1)+4*(jPos+1)+facing);
	}
	
	static void rotateMapCW() {
	    final int M = map.length;
	    final int N = map[0].length;
	    int[][] rotated = new int[N][M];
	    for (int r = 0; r < M; r++)
	        for (int c = 0; c < N; c++)
	        	rotated[c][M-1-r] = map[r][c];
	    map = rotated;
		mapRotation++;
		int tmp=iPos;
		iPos=jPos;
		jPos=map[0].length-tmp-1;
	}
}