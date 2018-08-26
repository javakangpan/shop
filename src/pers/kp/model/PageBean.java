package pers.kp.model;

import java.util.List;

public class PageBean<T> {
		
	private List<T> list;
	private int currentPage;
	private int pageCount;
	private int totalCount;
	private	int pageSize;
	
	
	public PageBean() {
		super();
	}
	

	public PageBean(List<T> list, int currentPage, int pageCount, int totalCount, int pageSize) {
		super();
		this.list = list;
		this.currentPage = currentPage;
		this.pageCount = pageCount;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
	}


	public List<T> getList() {
		return list;
	}


	public void setList(List<T> list) {
		this.list = list;
	}

	
	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public int getPageCount() {
		return (this.totalCount	-1)/this.pageSize+1;
	}


	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}


	public int getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	@Override
	public String toString() {
		return "PageBean [list=" + list + ", currentPage=" + currentPage + ", pageCount=" + pageCount + ", totalCount="
				+ totalCount + ", pageSize=" + pageSize + "]";
	}


	
	

	
	
}
