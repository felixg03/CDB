package com.excilys.cdb.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.excilys.cdb.enums.OrderByAttribute;

public class CustomPage<E> {
	
	public static class CustomPageBuilder<E> {
		private int size;
		private int number;
		private List<E> content = new ArrayList<>();
		private String search = "";
		private OrderByAttribute orderByAttribute = OrderByAttribute.ID;
		private Direction direction = Direction.ASC;
		
		public CustomPageBuilder() {
			
		}
		
		public CustomPageBuilder<E> setSize( int size ) {
			this.size = size;
			return this;
		}
		
		public CustomPageBuilder<E> setNumber( int number ) {
			this.number = number;
			return this;
		}
		
		public CustomPageBuilder<E> setContent( List<E> content ) {
			this.content = content;
			return this;
		}
		
		public CustomPageBuilder<E> setSearch( String search ) {
			this.search = search;
			return this;
		}
		
		public CustomPageBuilder<E> setOrderByAttribute(OrderByAttribute orderByAttribute ) {
			this.orderByAttribute = orderByAttribute;
			return this;
		}
		
		public CustomPageBuilder<E> setDirection(Direction direction ) {
			this.direction = direction;
			return this;
		}
		
		public CustomPage<E> build() {
			CustomPage<E> customPage = new CustomPage<>();
			customPage.size = this.size;
			customPage.number = this.number;
			customPage.content = this.content;
			customPage.search = this.search;
			customPage.orderByAttribute = this.orderByAttribute;
			customPage.direction = this.direction;
			return customPage;
		}
	}
	
	
	
	
	
	private int size;
	private int number;
	private List<E> content;
	private String search;
	private OrderByAttribute orderByAttribute;
	private Direction direction;
	
	private CustomPage() { }
	
	
	public int getSize() {
		return size;
	}

	public int getNumber() {
		return number;
	}
	
	public String getSearch() {
		return this.search;
	}
	
	public void setSearch( String search ) {
		this.search = search;
	}

	public void setContent(List<E> content) {
		this.content = content;
	}

	public List<E> getContent() {
		return content;
	}
	
	
	public OrderByAttribute getOrderByAttribute() {
		return this.orderByAttribute;
	}
	
	public void changeOrderByAttribute( OrderByAttribute newOrder ) {
		if ( newOrder != this.orderByAttribute ) {
			this.orderByAttribute = newOrder;
			this.direction = Direction.ASC;
		}
	}
	
	
	
	
	
	public Direction getDirection() {
		return this.direction;
	}
	public void setOrderByDirection( Direction direction ) {
		this.direction = direction;
	}
}
