package kr.or.ddit.springmvc.file;

import org.apache.commons.io.FilenameUtils;

public class FilenameTest {
	public static void main(String[] args) {
		String filePath = "c:/temp/sample.java";
		
		
		System.out.println(FilenameUtils.getExtension(filePath)); //java
		System.out.println(FilenameUtils.getBaseName(filePath)); //sample
		System.out.println(FilenameUtils.getName(filePath)); // sample.java
		System.out.println(FilenameUtils.getPath(filePath)); //temp/
		System.out.println(FilenameUtils.getFullPath(filePath)); //c:/temp/
		
		String encFileName = FilenameUtils.getFullPath(filePath) 
				+ FilenameUtils.getBaseName(filePath)
				+"_enc."
				+ FilenameUtils.getExtension(filePath);
		
		String decFileName = FilenameUtils.getFullPath(filePath) 
				+ FilenameUtils.getBaseName(filePath)
				+"_dec."
				+ FilenameUtils.getExtension(filePath);
		
		System.out.println(encFileName);
		System.out.println(decFileName);
	}
}
