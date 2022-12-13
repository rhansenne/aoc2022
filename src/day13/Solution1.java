package day13;
import java.io.*;
import java.util.*;
import org.json.simple.parser.*;

public class Solution1 {
	
	static enum Result { RIGHT_ORDER, WRONG_ORDER, CONTINUE}; 
	
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day13\\input.txt"));
		JSONParser jsonParser = new JSONParser();
		int index = 1, sum = 0;
		while (scanner.hasNextLine()) { 
			if (compare(jsonParser.parse(scanner.nextLine()),jsonParser.parse(scanner.nextLine()))==Result.RIGHT_ORDER) sum+=index;
			if (scanner.hasNextLine()) scanner.nextLine();
			index++;
		}
		scanner.close();
		System.out.println(sum);
	}

	static Result compare(Object l, Object r) {
		if (l instanceof Long && r instanceof Long)
			return (Long)l < (Long)r? Result.RIGHT_ORDER: (Long)l == (Long)r? Result.CONTINUE: Result.WRONG_ORDER;
		else if (l instanceof List && r instanceof Long)
			return compare(l,Arrays.asList(r));
		else if (l instanceof Long && r instanceof List)
			return compare(Arrays.asList(l),r);
		else {
			List listL = (List)l;
			List listR = (List)r;
			for (int i=0;i<listL.size();i++) {
				if (i>=listR.size()) return Result.WRONG_ORDER;
				Result result = compare(listL.get(i),listR.get(i));
				if (result!=Result.CONTINUE) return result;
			}
			return listL.size() == listR.size()? Result.CONTINUE : Result.RIGHT_ORDER;
		}
	}
}