package com.github.sorabh86.uigo.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.StringUtils;
import com.github.sorabh86.uigo.constants.Constants;

public class TestFileUpload {
	
	private String uploadDir = "upload/";
	private String id = "2";
	private MockMultipartFile multipartFile = new MockMultipartFile("file", "filename.txt", 
			"text/plain", "some we love text".getBytes());
	
	@Test
	public void createFolder() throws IOException {
		String folder = Constants.PHONE_DIR+"4";
		System.out.println(folder);
		Path folderPath = Paths.get(folder);
		Files.createDirectories(folderPath);
		System.out.println(Files.exists(folderPath));
	}
	
	@Test
	public void savefile() throws IOException {
		System.out.println("file name: "+multipartFile.getOriginalFilename());
		String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		FileUpload.savefile(uploadDir+id, filename, multipartFile);
		System.out.println("file is uploaded");
		
		Path filePath = Paths.get(uploadDir+id);
		assertThat(Files.exists(filePath)).isTrue();
	}
	
	@Test
	public void deletefile() throws IOException {
		FileUpload.deletefile(uploadDir+id);
		System.out.println("file is deleted");
		
		Path filePath = Paths.get(uploadDir);
		assertThat(Files.list(filePath).findAny().isPresent()).isTrue();
	}
	
	@Test
	public void createfile() throws IOException {
		File myfile = new File("abc.txt");
		myfile.createNewFile();
		System.out.println("Created: "+myfile.getAbsolutePath());
	}
	
	@Test
	public void writeTexttoFile() throws IOException {
		FileWriter myfile = new FileWriter("abc.txt");
		myfile.write("This is a text file \nWe are working a test to write on file");
		myfile.close();
		System.out.println("Write text to a file");
	}
	
	@Test
	public void appendTexttoFile() throws IOException {
		Date date = new Date();
		FileWriter myfile = new FileWriter("abc.txt", true);
		myfile.write("\n"+date.toString()+": World is wonderfull");
		myfile.close();
		System.out.println("Append text to a file");
	}
	
	@Test
	public void readFile() throws FileNotFoundException {
		File myfile = new File("abc.txt");
		Scanner scan = new Scanner(myfile);
		String str = "";
		while(scan.hasNextLine()) {
			str += scan.nextLine()+"\n";
		}
		System.out.println(str);
		scan.close();
	}
}
