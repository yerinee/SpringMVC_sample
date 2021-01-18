package kr.or.ddit.springmvc.board.vo;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public class BaseVo {

	/**
	 *  serialVersion UID
	 */
	private static final long serialVersionUID = -858838578081269359L;

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
	
	

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	public void preparepaginInfo(PaginationInfo paginationInfo) {
		paginationInfo.setCurrentPageNo(this.getPageIndex());
		paginationInfo.setRecordCountPerPage(this.getPageUnit());
		paginationInfo.setPageSize(this.getPageSize());

		this.setFirstIndex(paginationInfo.getFirstRecordIndex());
		this.setLastIndex(paginationInfo.getLastRecordIndex());
		this.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	}
	
	// 검색조건 유지
	public void copySearchCondition(BaseVo baseVo) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Map<String, String> describeMap = BeanUtils.describe(baseVo);
		Iterator<String> describeIterator = describeMap.keySet().iterator();
		while(describeIterator.hasNext()) {
			String key = describeIterator.next();
			
			if(key.startsWith("search") || key.startsWith("page")) {
				BeanUtils.copyProperty(this, key, describeMap.get(key));
			}
		}
	}
}
