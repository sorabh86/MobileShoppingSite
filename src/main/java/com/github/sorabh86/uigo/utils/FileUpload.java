package com.github.sorabh86.uigo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	
	public static void savefile(String uploadDir, String fileName, MultipartFile file) throws IOException {
		Path uploadPath = Paths.get(uploadDir);
		
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		try(InputStream stream = file.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(stream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new IOException("File could not saved.");
		}
	}
	
	public static void deletefile(String directory) throws IOException {
		Path dir = Paths.get(directory);
		
		try {
			Stream<Path> paths = Files.list(dir);
			paths.forEach(file->{
				if(!Files.isDirectory(file)) {
					System.out.println(file.toString());
					try {
						Files.delete(file);
					} catch (IOException e) {
						System.out.println("Couldn't delete file: "+file);
					}
				}
			});
		} catch (IOException e) {
//			e.printStackTrace();
//			throw new IOException("Could not list directory");
			System.out.println("Directory doesn't exist at: "+directory);
		}
		
	}
}
