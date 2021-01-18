package kr.or.ddit.springmvc.file;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.validator.GenericValidator;

public class Utils {
	public static String makeEncFileName(String filePath) {
		
		String result = "";
		if(GenericValidator.isBlankOrNull(filePath)) {
		
		}else {
			result = FilenameUtils.getFullPath(filePath) 
					+ FilenameUtils.getBaseName(filePath)
					+"_enc."
					+ FilenameUtils.getExtension(filePath);
		}
		
		return result;
	}
	
	public static String makeDecFileName(String filePath) {
		
		String result = "";
		if(GenericValidator.isBlankOrNull(filePath)) {
			
		}else {
			result = FilenameUtils.getFullPath(filePath) 
					+ FilenameUtils.getBaseName(filePath)
					+"_dec."
					+ FilenameUtils.getExtension(filePath);
		}
		
		return result;
	}
}
