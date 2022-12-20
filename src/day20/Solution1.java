package day20;
import java.io.*;
import java.util.*;

public class Solution1 {

	// wrapper class to uniquely identify value in list with multiple same numbers
	static class Value { int v; Value(int v) {this.v=v;} } 
	
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day20\\input.txt"));
		List<Value> ori = new ArrayList<Value>();
		while (scanner.hasNextLine())
			ori.add(new Value(Integer.parseInt(scanner.nextLine())));		
		scanner.close();
		LinkedList<Value> transformed = new LinkedList<Value>(ori);
		int length = transformed.size();
		for (Value v: ori) {			
			int newpos = (transformed.indexOf(v) + v.v)%(length-1);
			if (newpos<=0) newpos+=length-1;
			transformed.remove(v);
			if (newpos==length)
				transformed.add(v);
			else
				transformed.add(newpos, v);
		}
		int posZero=0;
		for (Value v: transformed) {
			if (v.v==0)
				posZero = transformed.indexOf(v);
		}
		int total = transformed.get((posZero+1000)%length).v + transformed.get((posZero+2000)%length).v + transformed.get((posZero+3000)%length).v;
		System.out.println(total);
	}
	
}