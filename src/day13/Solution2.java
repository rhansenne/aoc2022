package day13;
import java.io.*;
import java.util.*;
import org.json.simple.parser.*;

public class Solution2 {
	
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("src\\day13\\input.txt"));
		JSONParser jsonParser = new JSONParser();
		List list = new ArrayList();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (!line.isEmpty()) list.add(jsonParser.parse(line));
		}
		scanner.close();
		List div1 = Arrays.asList(Arrays.asList(Long.valueOf(2)));
		List div2 = Arrays.asList(Arrays.asList(Long.valueOf(6)));
		list.add(div1);
		list.add(div2);
		Collections.sort(list, new ListComparator());
		int key=1;
		for (int i=1;i<=list.size();i++) {
			if (list.get(i-1).equals(div1) || list.get(i-1).equals(div2)) key *= i;
		}
		System.out.println(key);
	}
	
	
	static class ListComparator implements Comparator {
		public int compare(Object l, Object r) {
			if (l instanceof Number && r instanceof Long)
				return (Long)l < (Long)r? -1: (Long)l == (Long)r? 0: 1;
			else if (l instanceof List && r instanceof Long)
				return compare(l,Arrays.asList(r));
			else if (l instanceof Long && r instanceof List)
				return compare(Arrays.asList(l),r);
			else {
				List listL = (List)l;
				List listR = (List)r;
				for (int i=0;i<listL.size();i++) {
					if (i>=listR.size()) return 1;
					int result = compare(listL.get(i),listR.get(i));
					if (result!=0) return result;
				}
				return listL.size() == listR.size()? 0 : -1;
			}
		}
	}

}