package com.coding.puzzle.util.parsing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.coding.puzzle.exceptions.ResourceNotFoundException;

/**
 * @author majidali
 *
 */
public class FileUtilTest {

	@ClassRule
	public static TemporaryFolder tempFolder = new TemporaryFolder();
	
	@Test(expected=ResourceNotFoundException.class)
	public void testReadDataWithInvalidFile() throws IOException {
		FileUtil.readData("abc.csv");
	}
	
	@Test
	public void testReadDataWithValidFile() throws IOException {
		final File tempFile = tempFolder.newFile("tempFile.csv");
		List<String> lines = new ArrayList<>();
		lines.add("id,name");
		lines.add("1, Majid Ali");
		FileUtils.writeLines(tempFile, lines);
		List<String> response = FileUtil.readData(tempFolder.getRoot().getPath()+"/tempFile.csv");
		Assert.assertTrue(response.size() == 1);
		Assert.assertEquals(response.get(0), "1, Majid Ali");
	}

}
