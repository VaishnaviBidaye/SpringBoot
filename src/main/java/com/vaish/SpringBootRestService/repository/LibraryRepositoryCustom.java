package com.vaish.SpringBootRestService.repository;

import java.util.List;

import com.vaish.SpringBootRestService.controller.Library;

public interface LibraryRepositoryCustom {

	List<Library> findAllByAuthor(String authorName);
	
}
