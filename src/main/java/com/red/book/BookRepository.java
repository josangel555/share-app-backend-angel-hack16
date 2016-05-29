package com.red.book;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, ObjectId>{

	// public List<Book> findByBookNameLike(String bookName);
}
