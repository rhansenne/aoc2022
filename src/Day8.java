

import java.io.*;

public class Day8 {
	
	public static void main(String[] args) throws Exception {
		File file = new File("src/day8/input.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine();
		int hor=line.length();
		int ver=(int)(file.length()/hor)-1;
		int[][] arr = new int[ver][hor];
		int v=0;
		do {
			for (int i=0;i<line.length();i++)
				arr[v][i] = Integer.parseInt(line.substring(i,i+1));
			v++;
		} while ((line=br.readLine())!=null);
		int numvis = 0;
		for (int j=0;j<ver;j++) {
			for (int i=0;i<hor;i++) {
				boolean visible = true;
				for (int ii=0;ii<i;ii++)
					if (arr[j][ii] >= arr[j][i])
							visible = false;
				if (visible) {numvis++; continue;}
				visible = true;
				for (int ii=i+1;ii<hor;ii++)
					if (arr[j][ii] >= arr[j][i])
							visible = false;
				if (visible) {numvis++; continue;}
				visible = true;
				for (int jj=0;jj<j;jj++) 
					if (arr[jj][i] >= arr[j][i])
							visible = false;
				if (visible) {numvis++; continue;}
				visible = true;
				for (int jj=j+1;jj<ver;jj++) 
					if (arr[jj][i] >= arr[j][i])
							visible = false;
				if (visible) {numvis++; continue;}
			}
		}
		System.out.println(numvis);
		br.close();
	}

}
