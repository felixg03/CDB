package com.excilys.cdb.models;

import java.util.ArrayList;
import java.util.List;

public class CustomPage<E> {
	private int size;
	private int number;
	private List<E> content;
	private CustomPage<E> nextPage;
	private CustomPage<E> previousPage;

	public CustomPage(int size, int number) {
		content = new ArrayList<>();
		this.size = size;
		this.number = number;
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

	public List<E> getContent() {
		return content;
	}

	public CustomPage<E> getPreviousPage() {
		CustomPage<E> previousPage = this;
		if (this.previousPage == null) {
			if (this.number > 0) {
				previousPage = new CustomPage<E>(this.size, this.number - 1);
			}
		} 
		else {
			previousPage = this.previousPage;
		}
		return previousPage;
	}

	public CustomPage<E> getNextPage() {
		CustomPage<E> nextPage;
		if (this.nextPage == null) {
				nextPage = new CustomPage<E>(this.size, this.number + 1);
		} 
		else {
			nextPage = this.nextPage;
		}
		return nextPage;
	}
	
	public void setNextPage(CustomPage<E> nextPage) {
		this.nextPage = nextPage;
	}

	public void setPreviousPage(CustomPage<E> previousPage) {
		this.previousPage = previousPage;
	}

}
