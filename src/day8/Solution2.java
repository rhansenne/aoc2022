package day8;


import java.io.*;

public class Solution2 {
	
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
		int max = 0;
		for (int j=0;j<ver;j++) {
			for (int i=0;i<hor;i++) {
				int scoreL = 0;
				int scoreR = 0;
				int scoreU = 0;
				int scoreD = 0;
				for (int ii=i-1;ii>=0;ii--)
					if (arr[j][ii] < arr[j][i])
							scoreL++;
					else
						{scoreL++; break;}
				for (int ii=i+1;ii<hor;ii++)
					if (arr[j][ii] < arr[j][i])
							scoreR++;
					else
						{scoreR++; break;}
				for (int jj=j-1;jj>=0;jj--)
					if (arr[jj][i] < arr[j][i])
							scoreU++;
					else
						{scoreU++; break;}
				for (int jj=j+1;jj<ver;jj++)
					if (arr[jj][i] < arr[j][i])
							scoreD++;
					else
						{scoreD++; break;}
				int score = scoreL*scoreR*scoreU*scoreD;
				if (score > max)
					max = score;
			}
		}
		System.out.println(max);
		br.close();
	}

}
