package com.hszs.stb.model.home;

import java.util.List;

import com.github.pagehelper.PageInfo;

public class TableResult {

	private List data;
	private PageInfo page;
	private Long recordsTotal;
	private Long recordsFiltered;
	
	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public PageInfo getPage() {
		return page;
	}

	public void setPage(PageInfo page) {
		this.page = page;
	}

	public Long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(Long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	
	
}
