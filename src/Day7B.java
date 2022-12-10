import java.io.*;
import java.util.*;

public class Day7B {

	static Day7B d7 = new Day7B();
	static Map<String,Directory> dirs = new HashMap<String,Directory>();

	class File {
		String name;
		long size;
		File(String n, long s) {name=n;size=s;}
		long getSize() {return size;}
		String getName() {return name;}		
	}
	
	class Directory extends File {
		List<File> files = new ArrayList<File>();
		Directory(String n) {super(n,0);}
		void addFile(File f) {files.add(f);}
		long getSize() {
			long size = 0;
			for (File f: files)
				size += f.getSize();
			return size;
		}
		public String toString() {return getName() + ":" + getSize();}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("src\\day7\\input.txt"));
		String line = br.readLine();
		String currentPath = "/";
		Directory currentDir = getDir(currentPath);
		while ((line = br.readLine()) != null) {
			if (line.startsWith("$ cd ")) {
				String p = line.substring(5,line.length());
				if (p.equals("..")) {
					currentPath = currentPath.substring(0,currentPath.lastIndexOf("/"));
				} else 
					currentPath += "/" + p;
				currentDir = getDir(currentPath);
			}
			else if (line.equals("$ ls")) {
			}
			else if (line.startsWith("dir ")) {
				String dirName = line.substring(4,line.length());
				currentDir.addFile(getDir(currentPath+"/"+dirName));
			}
			else {
				String[] f = line.split(" ");
				long size = Long.parseLong(f[0]);
				currentDir.addFile(d7.new File(f[1],size));
			}
		}
		
		Directory selected = d7.new Directory("");
		selected.addFile(d7.new File("",Long.MAX_VALUE));
		for (Directory d: dirs.values()) {
			if (d.getSize()>=8381165 && d.getSize()<selected.getSize()) {
				selected = d;
			}
		}
		System.out.println(selected);
		
	}

	static Directory getDir(String path) {
		Directory dir = dirs.get(path);
		if (dir==null) {
			dir = d7.new Directory(path);
			dirs.put(path, dir);
		}
		return dir;
	}
	
}

