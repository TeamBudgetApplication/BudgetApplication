package com.budgetly.application.entities;

public class SearchRequest {
	
	private String keyword;
	
	public SearchRequest() {
	}

	public SearchRequest(String keyword) {
		super();
		this.keyword = keyword;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "SearchRequest [keyword=" + keyword + "]";
	}
	

}
