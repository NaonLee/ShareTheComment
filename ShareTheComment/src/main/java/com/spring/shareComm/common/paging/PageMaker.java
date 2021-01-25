package com.spring.shareComm.common.paging;

public class PageMaker {
	
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int displayPage = 10;	//number of display page 
	private Criteria criteria;
	
	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	public Criteria getCriteria() {
		return criteria;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calData();
	}
	
	public int getTotalCount() {
		return totalCount;
	}


	private void calData() {
		endPage = (int) (Math.ceil(criteria.getPage() / (double) displayPage) * displayPage);
		startPage = (endPage - displayPage) + 1;
		int tempEndPage = (int) (Math.ceil(totalCount / (double) criteria.getPerPageNum()));
		
		if (endPage > tempEndPage) {
			endPage = tempEndPage; 
		} 
		prev = startPage == 1 ? false : true;
		next = endPage * criteria.getPerPageNum() >= totalCount ? false : true;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPage() {
		return displayPage;
	}

	public void setDisplayPage(int displayPage) {
		this.displayPage = displayPage;
	}

}
