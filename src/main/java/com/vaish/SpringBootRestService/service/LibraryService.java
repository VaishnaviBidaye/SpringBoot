package com.vaish.SpringBootRestService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaish.SpringBootRestService.repository.LibraryRepository;
import com.vaish.SpringBootRestService.controller.Library;

@Service
public class LibraryService {
	
	@Autowired
	LibraryRepository repository;
	
	public String buildId(String isbn, int aisle) {
		
		if(isbn.startsWith("Z"))
		{
			return "OLD"+isbn+aisle;
		}
		
		return isbn+aisle;
	}
	
	public boolean checkBookAlreadyExist(String id) {
		
		// Optional is to know the data is present or not. "isPresent()" is method of Optional.
		 Optional<Library> lib = repository.findById(id);
		 if(lib.isPresent()) 
			 return true;
		 else	
			 return false;
		
		
	}
	
	public Library getBookById(String id) 
	{
		return repository.findById(id).get();
	}

}
