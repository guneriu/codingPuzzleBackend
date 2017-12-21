package com.coding.puzzle.util.parsing;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.coding.puzzle.exceptions.InternalServerException;
import com.coding.puzzle.util.Constants;

/**
 * @author majidali
 *
 */
public class FileUtilTest {

	@Test(expected=InternalServerException.class)
	public void testReadDataWithInValidFile() throws IOException {
		FileUtil.readData("/resources.csv");
	}
	
	@Test
	public void testReadDataWithValidFile() throws IOException {
		List<String> response = FileUtil.readData(Constants.GAME_LEVELS_FILE_NAME);
		Assert.assertTrue(response.size() == 1);
		Assert.assertEquals("1,You are at the {locationName} your target is to kill Enemies here,FIGHT,2,1,1", response.get(0));
	}

}
