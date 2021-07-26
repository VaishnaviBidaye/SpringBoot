package com.vaish.SpringBootRestService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.vaish.SpringBootRestService.controller.Library;
import com.vaish.SpringBootRestService.repository.LibraryRepository;



@SpringBootApplication
public class SpringBootRestServiceApplication {

	@Autowired
	LibraryRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestServiceApplication.class, args);
	}
	
//	@Override
//	public void run(String[] args)
//	{
//		// Display specific one 
//		Library lib = repository.findById("fdsefr3").get();
//		System.out.println(lib.getAuthor());
//		
//		// Insert
//		Library add = new Library();
//		add.setAisle(123);
//		add.setAuthor("Vaishnavi");
//		add.setBook_name("Devops");
//		add.setIsbn("lkhs");
//		add.setId("lkhs123");
//		repository.save(add);
//		
//		// Display All
//		List<Library> allRecords = repository.findAll();
//		for(Library item: allRecords)
//		{
//			System.out.println(item.getBook_name());
//		}
//		
//		// Delete
//		repository.delete(add);
//		
//	}

}
