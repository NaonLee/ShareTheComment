package com.spring.shareComm.common.paging;

public class Criteria {
	private int page;
	private int perPageNum;
	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		if(perPageNum <= 0 || perPageNum > 100) {		//maximum 100 pages
			this.perPageNum = 10;
			return;
		}
		return;
	}
	
	public int getPageStart() {
		return (this.page - 1) * perPageNum;
	}
	
	@Override public String toString() { return "Criteria [page=" + page + ", perPageNum="+ perPageNum+"]" ; }

}
