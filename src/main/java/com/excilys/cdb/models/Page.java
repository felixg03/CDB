package com.excilys.cdb.models;

import java.util.ArrayList;
import java.util.List;

public class Page<E> {
	private int size;
	private int number;
	private String table;
	private List<E> content;
	private Page<E> nextPage;
	private Page<E> previousPage;

	public Page(int size, int number, String table) {
		content = new ArrayList<>();
		this.size = size;
		this.number = number;
		this.table = table;
	}

	public int getSize() {
		return size;
	}

	public int getNumber() {
		return number;
	}

	public void setContent(List<E> content) {
		this.content = content;
	}

	public String getTable() {
		return table;
	}

	public List<E> getContent() {
		return content;
	}

	public Page<E> getPreviousPage() {
		Page<E> previousPage = this;
		if (this.previousPage == null) {
			if (this.number > 0) {
				previousPage = new Page<E>(this.size, this.number - 1, this.table);
			}
		} 
		else {
			previousPage = this.previousPage;
		}
		return previousPage;
	}

	public Page<E> getNextPage() {
		Page<E> nextPage;
		if (this.nextPage == null) {
				nextPage = new Page<E>(this.size, this.number + 1, this.table);
		} 
		else {
			nextPage = this.nextPage;
		}
		return nextPage;
	}
	
	public void setNextPage(Page<E> nextPage) {
		this.nextPage = nextPage;
	}

	public void setPreviousPage(Page<E> previousPage) {
		this.previousPage = previousPage;
	}
}
