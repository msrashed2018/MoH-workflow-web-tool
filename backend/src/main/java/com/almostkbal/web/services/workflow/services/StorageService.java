package com.almostkbal.web.services.workflow.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.almostkbal.web.services.workflow.entities.DocumentType;

@Service
public class StorageService {
	@Value(value = "${documents.directory}")
	private String documentsPath;
	
	
	
	Logger log = LoggerFactory.getLogger(this.getClass().getName());

	public String store(long requestId, DocumentType documentType, String fileName, MultipartFile file) {
		try {
			String storedPath = documentsPath + "/" + String.valueOf(requestId) + "/" + documentType.getName();
			log.info("storedPath :"+storedPath);
			
			
			File directory = new File(storedPath);
		    if (! directory.exists()){
		        directory.mkdirs();
		        // If you require it to make the entire directory path including parents,
		        // use directory.mkdirs(); here instead.
		    }
			
			Path rootLocation = Paths.get(storedPath);
			Files.copy(file.getInputStream(), rootLocation.resolve(fileName));
			
			return storedPath;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException("FAIL! to store file "+ fileName);
		}
	}

	public Resource loadFile(String path, String filename) {
		try {
			Path rootLocation = Paths.get(path);
			Path file = rootLocation.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException("FAIL!");
		}
	}

	public void deleteAll() {
		Path rootLocation = Paths.get(documentsPath);
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	public void init() {
		try {
			Path rootLocation = Paths.get(documentsPath);
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException("Could not initialize storage!");
		}
	}
}