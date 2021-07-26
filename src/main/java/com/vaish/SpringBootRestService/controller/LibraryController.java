package com.vaish.SpringBootRestService.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import com.vaish.SpringBootRestService.repository.LibraryRepository;
import com.vaish.SpringBootRestService.service.LibraryService;

@RestController
public class LibraryController {
	
	@Autowired
	LibraryRepository repository;
	
	@Autowired
	LibraryService libraryService;
	
	private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);
	
	// Query Parameter
	// Add Book
	@PostMapping("/addBook")
	public ResponseEntity addBookImplementation(@RequestBody Library library) 
	{
		
		String id = libraryService.buildId(library.getIsbn(), library.getAisle());	// dependency mock
		AddResponse ad = new AddResponse();
		
		if(!libraryService.checkBookAlreadyExist(id))	// mock
		{	
			logger.info("Book do not exist so creating one");
			
			library.setId(id);
			repository.save(library);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("unique",id);
			
			ad.setMsg("Success! Book is added.");
			ad.setId(id);
			
			// return ad;
			return new ResponseEntity<AddResponse>(ad, headers, HttpStatus.CREATED);
			
		}
		else 
		{
			logger.info("Book exist so skipping creation");
			
			// Book already exist
			ad.setMsg("Book already exits!");
			ad.setId(id);
			return new ResponseEntity<AddResponse>(ad, HttpStatus.ACCEPTED);
			
		}
		
	}
	
	
	
	// Path Parameter
	// Get Book by ID 
	@GetMapping("/getBooks/{bookId}")
	public Library getBookById(@PathVariable(value="bookId") String id)
	{
		try {
			Library lib = repository.findById(id).get();
			return lib;
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	
	// Query Parameter
	// Get Books by Author name
	@GetMapping("/getBooks/author")
	public List<Library> getBookByAuthorName(@RequestParam(value="authorname") String authorname)
	{
		return repository.findAllByAuthor(authorname);	// mock
	}

	
	
	// Path Parameter
	// Update Book Info
	@PutMapping("/updateBook/{id}")
	public ResponseEntity<Library> updateBook(@PathVariable(value="id")String id, @RequestBody Library library)
	{
		// Library existingBook = repository.findById(id).get();
		Library existingBook = libraryService.getBookById(id);	// mock
			
		existingBook.setAisle(library.getAisle());
		existingBook.setAuthor(library.getAuthor());
		existingBook.setBook_name(library.getBook_name());
		
		repository.save(existingBook);
		
		return new ResponseEntity<Library>(existingBook, HttpStatus.OK);
		
	}
	
	
	// Query Parameter
	// Delete Book Info
	@DeleteMapping("/deleteBook")
	public ResponseEntity<String> deleteBookId(@RequestBody Library library)
	{
		// Library libdelete = repository.findById(library.getId()).get();
		Library libdelete = libraryService.getBookById(library.getId());	// mock
		
		repository.delete(libdelete);
		
		logger.info("Book is deleted");
		return new ResponseEntity<>("Book is deleted!", HttpStatus.CREATED);
	}


}
