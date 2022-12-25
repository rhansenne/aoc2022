package day25;
import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day25\\input.txt"));
		double sum=0;
		while (scanner.hasNextLine()) sum+=toDecimal (scanner.nextLine());
		scanner.close();
		System.out.println(toSnafu(sum));
	}
	static double toDecimal(String snafu) {
		int pow=0;
		double decimal=0;
		for (int i=snafu.length()-1; i>=0; i--) {
			int d=0;
			if (snafu.charAt(i)=='-') d=-1; 
				else if (snafu.charAt(i)=='=') d=-2;
				else d=Integer.parseInt(snafu.charAt(i)+"");
			decimal+=Math.pow(5, pow)*d;
			pow++;
		}
		return decimal;
	}
	static String toSnafu(double decimal) {
		int maxPow=0;
		while (decimal>=Math.pow(5, maxPow)) maxPow++;
		String snafu="";
		for (int pow=maxPow-1; pow>=0; pow--) {
			int d = (int) Math.round(decimal/Math.pow(5, pow));
			decimal-=d*Math.pow(5, pow);
			if (d==-2) snafu+="=";
				else if (d==-1) snafu+="-";
					else snafu+=d;
			decimal = decimal%Math.pow(5, pow);
		}			
		return snafu;
	}	
}
