package com.red.book;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Book {

	@Id
	private ObjectId id;
	
	private String bookName;
	private String author;
	private String publisher;
	private int cost;
	private String userName;
	
	public Book() {
		super();
	}

	public Book(String bookName, String author, String publisher, int cost, String userName) {
		this.bookName = bookName;
		this.author = author;
		this.publisher = publisher;
		this.cost = cost;
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", author=" + author + ", publisher=" + publisher
				+ ", cost=" + cost + "]";
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
