package com.masterdevskills.cha1.ext1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FileProcessorTest {
	private FileProcessor fileProcessor;
	private File readFrom;
	private File writeTo;

	@BeforeEach
	void setUp() throws IOException {
		fileProcessor = new FileProcessor();
		readFrom = File.createTempFile("filename", ".txt");
		var text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla\n" +
						"interdum nisl nec dui volutpat sodales. Vestibulum ac consequat justo, vel fermentum velit. Integer purus metus,\n " +
						"volutpat eu neque ut, commodo commodo dui. Mauris scelerisque tempor placerat. Aliquam non felis ut dui suscipit mattis\n" +
						"\n" +
						"\n" +
						"in in quam. Phasellus luctus ut nulla vel volutpat. Sed consequat ultrices commodo. Donec vel quam mollis, pellentesque \n" +
						"nulla quis, semper elit. Ut mollis, eros vel aliquet tincidunt, leo sem interdum risus, sed accumsan massa sem sit amet \n" +
						"felis. Maecenas aliquet, tellus a posuere tincidunt, arcu metus commodo enim, ac gravida libero diam eu ante. \n" +
						"\n" +
						"\n" +
						"Aenean malesuada tincidunt feugiat. Ut mollis lectus vitae massa maximus tempor. Mauris volutpat scelerisque sollicitudin.\n" +
						"Morbi sem nisi, vestibulum aliquam tincidunt nec, facilisis sed tellus. Fusce porta luctus neque, vel hendrerit dui \n" +
						"imperdiet at. Nulla iaculis euismod orci, sed dictum arcu fringilla in.\n";
		Files.writeString(Paths.get(readFrom.getPath()), text);

		writeTo = File.createTempFile("filename", ".txt");
	}

	@AfterEach
	void tearDown() {
		readFrom.deleteOnExit();
		writeTo.deleteOnExit();
	}

	@Test
	void testReadFileFrom() {
		System.out.println(readFrom.getPath());
		var lines = fileProcessor.readFileFrom(readFrom.getPath());

		assertEquals(lines.size(), 9);
	}

	@Test
	void testWriteToFile() {
		var lines = fileProcessor.readFileFrom(readFrom.getPath());
		fileProcessor.writeToFile(lines, writeTo.getPath());
		var writtenLine = fileProcessor.readFileFrom(writeTo.getPath());

		assertEquals(writtenLine.size(), lines.size());
	}
}