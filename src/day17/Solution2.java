package day17;
import java.io.*;
import java.util.*;

public class Solution2 {

	static long numRocks=1000000000000L;
	static long counter=0;
	static List<boolean[][]> figures = new ArrayList<boolean[][]>();
	static long scrolled=0;
	static int towerHeight=0;
	static int jetIndex=0;
	static boolean skipped=false;
	static String jet = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>";
	static long[]jetIndexFloorHeight;
	static long[]jetIndexHash;
	static long[]jetIndexNumRocks;
	static int scrollHeight=100;
	static int chamberHeight = scrollHeight*2;
	static int chamberWidth = 9;
	// scrollable chamber which only caches the last few lines
	static boolean[][] chamber = new boolean[chamberHeight][chamberWidth];
	static boolean[] emptyRow = new boolean[] {true,false,false,false,false,false,false,false,true};

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
						counter++;
						addToChamber(figure,prevHeight,posLeftOffset);
						if (counter==numRocks) {
							System.out.println(towerHeight+scrolled);
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
		
		// cache a hash of most recent rows per jet position to identify recurrences and skip ahead
		int numFloorsToHash=20;
		long hash=0;
		if (towerHeight>numFloorsToHash)
			for (int i=0;i<numFloorsToHash;i++)
				hash += Arrays.toString(chamber[chamber.length-towerHeight-1+i]).hashCode();				
		if (jetIndexFloorHeight==null) {
			jetIndexFloorHeight = new long[jet.length()];
			jetIndexNumRocks = new long[jet.length()];
			jetIndexHash = new long[jet.length()];
		}
		if (!skipped && hash!=0 && jetIndexHash[jetIndex]==hash) {
			// recurring part detected at same jet index -> we can skip over repetitions
			long currentTotalHeight = towerHeight + scrolled;
			long heightDiff = currentTotalHeight - jetIndexFloorHeight[jetIndex];
			long rocksDiff = counter-jetIndexNumRocks[jetIndex];
			long skippableRepetitions = (numRocks-counter)/rocksDiff;
			long skippableRocks = skippableRepetitions * rocksDiff;
			long skippableHeight = skippableRepetitions * heightDiff;
			long newTotalHeight = currentTotalHeight+skippableHeight;
			// create the chamber and set the variables as they would look after skipping the repetitions
			for (int i=1;i<=numFloorsToHash;i++)
				chamber[chamber.length-1-i]=chamber[chamber.length-1-towerHeight+numFloorsToHash-i];
			for (int i=0;i<chamber.length-1-numFloorsToHash;i++)
				chamber[i] = Arrays.copyOf(emptyRow, chamberWidth);
			counter += skippableRocks;
			towerHeight = numFloorsToHash;
			scrolled = newTotalHeight-numFloorsToHash;
			skipped = true;
		}
		jetIndexFloorHeight[jetIndex]=towerHeight+scrolled;
		jetIndexNumRocks[jetIndex]=counter;
		jetIndexHash[jetIndex]=hash;
		
		// refresh/scroll chamber to make room for new blocks whenever the chamber is getting full
		if (towerHeight>(3*scrollHeight/2))
			scrollChamber();
	}
	
	static void scrollChamber() {
		for (int i=0;i<scrollHeight;i++) {
			chamber[i+scrollHeight]=chamber[i];
			chamber[i] = Arrays.copyOf(emptyRow, chamberWidth);
		}
		towerHeight-=scrollHeight;
		scrolled+=scrollHeight;
	}
}
