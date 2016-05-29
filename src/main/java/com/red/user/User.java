package com.red.user;

import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.red.book.Book;

@Document
public class User {

	@Id
	private ObjectId id;

	private String userName;
	private String userId;
	private String authToken;
	private String key;

	private double[] loc;
	
	@DBRef
	private List<Book> books;
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userId=" + userId + ", authId=" + authToken + ", key="
				+ key + ", loc=" + Arrays.toString(loc) + ", books=" + books + "]";
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public double[] getLoc() {
		return loc;
	}

	public void setLoc(double[] loc) {
		this.loc = loc;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}
