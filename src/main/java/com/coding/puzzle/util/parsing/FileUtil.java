package com.coding.puzzle.util.parsing;

import static com.coding.puzzle.util.Constants.ErrorMessages.FILE_NOT_FOUND;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.coding.puzzle.exceptions.InternalServerException;
import com.coding.puzzle.exceptions.ResourceNotFoundException;
/**
 * General file manipulation utilities.
 * 
 * @author majidali
 *
 */
public final class FileUtil {

	public static List<String> readData(String fileName) {
		List<String> list = new ArrayList<>();
		try (BufferedReader br = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource(fileName).toURI()))) {
			// br returns as stream and convert it into a List
			list = br.lines().skip(1).collect(Collectors.toList());
		} catch (IOException | URISyntaxException e) {
			throw new InternalServerException(String.format(FILE_NOT_FOUND, fileName), e);
		}
		return list;
	}
}
