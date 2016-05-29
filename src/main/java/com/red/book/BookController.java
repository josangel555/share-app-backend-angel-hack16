package com.red.book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.red.user.User;
import com.red.user.UserRepository;

@RestController
@RequestMapping("/api")
public class BookController {
	
//	@Autowired
//	private BookRepository bookRepo;
	
	@Autowired
	private UserRepository userRepo;

	@RequestMapping("/book/add")
	public ResponseEntity<String> addBook(@RequestHeader(defaultValue="") String id,
			@RequestHeader(defaultValue="") String userId,
			@RequestHeader(defaultValue="") String authToken,
			@RequestHeader(defaultValue="") String bookName){
		
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping("/book/find")
	public ResponseEntity<Map<String, List<Book>>> findBooks(@RequestHeader(defaultValue="") String userName,
			@RequestHeader(defaultValue="") String id,
			@RequestHeader(defaultValue="") String userId,
			@RequestHeader(defaultValue="") String authToken,
			@RequestHeader(defaultValue="") String loc,
			@RequestHeader(defaultValue="") String filter){
		
		List<Book> books = new ArrayList<>();
		fillDummy(books);
		if(filter.isEmpty()){
			
			List<User> users = userRepo.findAll();
			for(User user: users){
				if(user.getBooks() == null || user.getUserName().equals(userName)){
					continue;
				}
				
				books.addAll(user.getBooks());
			}
			
		} else {
			
			List<User> users = userRepo.findAll();
			for(User user: users){
				if(user.getBooks() == null || user.getUserName().equals(userName)){
					continue;
				}
				
				for(Book book: user.getBooks()){
					if(book.getBookName().matches(".*?" + filter + ".*?")){
						books.add(book);
					}
				}
			}
		}
		
		Map<String, List<Book>> returnMap = new HashMap<>();
		returnMap.put("Books", books);
		return new ResponseEntity<>(returnMap, HttpStatus.OK);
	}
	
	private void fillDummy(List<Book> books){
		books.add(new Book("Alice In Wonderland", "Some Auth 1", "Oriely", 500, "josangel"));
		books.add(new Book("Sherlock", "Some Auth 2", "Oriely", 500, "josangel"));
		books.add(new Book("Harry Potter", "Some Auth 3", "Oriely", 500, "josangel"));
		books.add(new Book("Engineering Mathematics", "Some Auth 4", "Oriely", 500, "josangel555"));
	}
}
