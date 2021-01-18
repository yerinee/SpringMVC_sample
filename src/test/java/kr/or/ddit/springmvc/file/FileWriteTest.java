package kr.or.ddit.springmvc.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileWriteTest {
	public static void main(String[] args) throws IOException {
		List<String> lines = new ArrayList<String>();
		for(int i=0;i<100;i++) {
			lines.add("테스트 문자열" + (i+1));
		}
		File file = new File("c:/temp/test.txt");
		FileUtils.writeLines(file, lines, true);
	}
}
