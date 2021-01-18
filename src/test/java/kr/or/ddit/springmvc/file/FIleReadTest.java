package kr.or.ddit.springmvc.file;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;


public class FIleReadTest {
	public static void main(String[] args) throws IOException {
		File file = new File("c:/temp/sample.java");
		List<String> lines = FileUtils.readLines(file, "UTF-8");
		for(String line : lines) {
			System.out.println(line);
		}
	}
}
