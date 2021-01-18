package kr.or.ddit.springmvc.base.vo;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.or.ddit.springmvc.board.vo.BoardVO;

public class BaseVO implements Serializable {
	
	/** 검색조건 */
	private String searchCondition = "";

	/** 검색Keyword */
	private String searchKeyword = "";

	/** 검색사용여부 */
	private String searchUseYn = "";

	/** 현재페이지 */
	private int pageIndex = 1;

	/** 페이지갯수 */
	private int pageUnit = 10;

	/** 페이지사이즈 */
	private int pageSize = 10;

	/** firstIndex */
	private int firstIndex = 1;

	/** lastIndex */
	private int lastIndex = 1;

	/** recordCountPerPage */
	private int recordCountPerPage = 10;
	
	
	private String saveToken;
	
	

	public String getSaveToken() {
		return saveToken;
	}

	public void setSaveToken(String saveToken) {
		this.saveToken = saveToken;
	}

	public void preparePaginationInfo(PaginationInfo paginationInfo) {
		paginationInfo.setCurrentPageNo(this.getPageIndex());
		paginationInfo.setRecordCountPerPage(this.getPageUnit());
		paginationInfo.setPageSize(this.getPageSize());

		this.setFirstIndex(paginationInfo.getFirstRecordIndex());
		this.setLastIndex(paginationInfo.getLastRecordIndex());
		this.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	}
	
	// 페이징 검색조건과 현재페이지 유지
	public void copySearchCondition(BaseVO baseVO)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Map<String, String> describeMap = BeanUtils.describe(baseVO);
		Iterator<String> describeIterator=describeMap.keySet().iterator();
		while(describeIterator.hasNext()) {
			String key = describeIterator.next();			
			if(key.startsWith("search")
					||key.startsWith("page")) {
				BeanUtils.copyProperty(this, key, describeMap.get(key));
			}
		}
	}

	public String getSearchCondition() {
		return searchCondition;
	}



	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}



	public String getSearchKeyword() {
		return searchKeyword;
	}



	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}



	public String getSearchUseYn() {
		return searchUseYn;
	}



	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}



	public int getPageIndex() {
		return pageIndex;
	}



	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}



	public int getPageUnit() {
		return pageUnit;
	}



	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}



	public int getPageSize() {
		return pageSize;
	}



	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}



	public int getFirstIndex() {
		return firstIndex;
	}



	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}



	public int getLastIndex() {
		return lastIndex;
	}



	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}



	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}



	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}



	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}