package com.coding.puzzle.util.parsing;

import static com.coding.puzzle.util.Constants.ErrorMessages.FILE_NOT_FOUND;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.coding.puzzle.exceptions.InternalServerException;

/**
 * General file manipulation utilities.
 * 
 * @author majidali
 *
 */
public final class FileUtil {

	public static List<String> readData(String fileName) {
		InputStream inputStream = FileUtil.class.getClassLoader().getResourceAsStream(fileName);
		if (inputStream == null) {
			throw new InternalServerException(String.format(FILE_NOT_FOUND, fileName));
		}
		List<String> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			// br returns as stream and convert it into a List
			list = br.lines().skip(1).collect(Collectors.toList());
		} catch (IOException e) {
			throw new InternalServerException(e.getMessage(), e);
		}
		return list;
	}
}
