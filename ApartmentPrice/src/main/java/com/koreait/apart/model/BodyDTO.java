package com.koreait.apart.model;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "body")
public class BodyDTO {
	
	@JacksonXmlProperty(localName = "items")
	private List<ItemDTO> items;
	@JacksonXmlProperty(localName = "numOfRows")
	private int numOfRows;
	@JacksonXmlProperty(localName = "pageNo")
	private int pageNo;
	@JacksonXmlProperty(localName = "totalCount")
	private int totalCount;
	
	public List<ItemDTO> getItems() {
		return items;
	}
	public void setItems(List<ItemDTO> items) {
		this.items = items;
	}
	public int getNumOfRows() {
		return numOfRows;
	}
	public void setNumOfRows(int numOfRows) {
		this.numOfRows = numOfRows;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
