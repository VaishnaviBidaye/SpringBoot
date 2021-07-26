package com.vaish.SpringBootRestService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vaish.SpringBootRestService.controller.Library;

public interface LibraryRepository extends JpaRepository<Library, String>, LibraryRepositoryCustom {

	List<Library> findAllByAuthor(String authorname);

	
	
}
