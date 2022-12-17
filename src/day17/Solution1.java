package day17;
import java.io.*;
import java.util.*;

public class Solution1 {

	static List<boolean[][]> figures = new ArrayList<boolean[][]>();
	static int towerHeight=0;
	static int jetIndex=0;
	static String jet = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>";
	static boolean[][] chamber = new boolean[5000][9];
	
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day17\\blocks.txt"));
		while (scanner.hasNextLine()) {
			List<boolean[]> list = new ArrayList<boolean[]>();
			do{
				String line = scanner.nextLine();
				if (line.isEmpty()) break;
				boolean[] l = new boolean[line.length()];
				for (int i=0;i<line.length();i++)
					if (line.charAt(i) == '#') l[i]=true;
						else l[i]=false;
				list.add(l);
			} while (scanner.hasNextLine());
			figures.add(list.toArray(new boolean[list.size()][]));
		}
		scanner.close();		
		scanner = new Scanner(new File("src\\day17\\input.txt"));
		jet = scanner.nextLine();
		scanner.close();
		
		// add chamber floor and walls
		for (int i=0;i<chamber[0].length;i++)
			chamber[chamber.length-1][i]=true;
		for (int i=0;i<chamber.length;i++) {
			chamber[i][0]=true;
			chamber[i][chamber[0].length-1]=true;
		}
		
		int counter=1;
		outer: while (true) {
			for (boolean[][] figure: figures) {
				int posHeight=towerHeight+4;
				int posLeftOffset=3;
				// start falling
				inner: while (true) {
					int prevHeight=posHeight;
					int prevLeftOffset=posLeftOffset;
					
					// check jet impact
					if(jetLeft()) {
						posLeftOffset--;
					} else {
						posLeftOffset++;
					} if (overlap(figure,posHeight,posLeftOffset)) {
						posLeftOffset=prevLeftOffset;
					}
					
					// check drop
					posHeight--;
					if (overlap(figure,posHeight,posLeftOffset)) {
						addToChamber(figure,prevHeight,posLeftOffset);
						if (counter++==2022) {
							System.out.println(towerHeight);
							break outer;
						}
						break inner;
					}					
				}
			}
		}
	}

	static boolean jetLeft() {
		if (jetIndex>=jet.length()) jetIndex=0;
		if(jet.charAt(jetIndex++)=='<') return true;
			else return false;
	}
	
	static boolean overlap(boolean[][] figure, int posHeight, int posLeftOffset) {
		for (int i=0;i<figure.length;i++)
			for (int j=0;j<figure[i].length;j++)
				if(figure[i][j] && chamber[chamber.length-posHeight-figure.length+i][j+posLeftOffset]) return true;
		return false;
	}
	
	static void addToChamber(boolean[][] figure, int posHeight, int posLeftOffset) {
		for (int i=0;i<figure.length;i++)
			for (int j=0;j<figure[i].length;j++)
				if (figure[i][j]) chamber[chamber.length-posHeight-figure.length+i][j+posLeftOffset] = true;
		towerHeight=Math.max(towerHeight,posHeight+figure.length-1);
	}
}
