package com.coding.puzzle.models;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.coding.puzzle.util.input.InputReader;
import com.coding.puzzle.util.input.InputReaderFactory;
import com.coding.puzzle.util.logging.Logger;
import com.coding.puzzle.util.logging.LoggerFactory;

/**
 * @author majidali
 *
 * @param <T>
 */
public class Menu<T extends Enum> {

	private static Logger logger = LoggerFactory.getLogger();

	private static final int MENU_ITEM_OFFSET = 1;

	private static final Function<? super Enum, String> ENUM_TO_STRING = someEnum -> someEnum.ordinal()
			+ MENU_ITEM_OFFSET + ". " + someEnum;

	private final InputReader reader = InputReaderFactory.getInputReader();

	private final String title;

	private final T[] items;

	private final Runnable showErrorMessage = () -> {
		printMenuFooter(true);
	};

	private final UserInputRange inputRange;

	@SafeVarargs
	public Menu(String title, T... items) {
		if (items.length == 0) {
			throw new IllegalArgumentException("There are no configured menu items");
		}
		this.title = title;
		this.items = items;

		this.inputRange = new UserInputRange(1, items.length);
	}

	public void draw() {
		logger.log(title);
		Stream.of(items).map(ENUM_TO_STRING).forEach(logger::log);
	}

	public T chooseItem() {
		printMenuFooter(false);
		return items[readItemIndex()];
	}

	private void printMenuFooter(boolean showError) {
		if (showError) {
			logger.error("Invalid operation Number. Please, type correct one.");
		} else {
			logger.log("Please type operation number:");
		}
	}

	private int readItemIndex() {
		return reader.readIntegerUntil(itemIsInAcceptableRange(), showErrorMessage) - MENU_ITEM_OFFSET;
	}

	private Predicate<String> itemIsInAcceptableRange() {
		return line -> inputRange.contains(Integer.parseInt(line));
	}
}
