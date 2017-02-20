package com.ataybur.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.ataybur.constants.Constants;
import com.ataybur.constants.MessageConstants;
import com.ataybur.lambda.ConsumerThrowing;
import com.ataybur.pojo.Context;

public class FileUtils {
	
	public static void readFile(String fileName) throws IOException {
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			ConsumerThrowing<String> consumer = ParserUtils::parseIntoContext;
			stream.forEach(consumer);
		}
	}

	public static String retrieveInputFileName(String[] args) {
		String fileName = null;
		if (args != null && args.length >= 1) {
			fileName = args[0];
		} else {
			throw new RuntimeException(MessageConstants.MESSAGE_7);
		}
		return fileName;
	}

	public static String retrieveOutputFileName(String[] args) {
		String fileName = null;
		if (args != null && args.length >= 2) {
			fileName = args[1];
		} else {
			throw new RuntimeException(MessageConstants.MESSAGE_8);
		}
		return fileName;
	}

	public static void writeErrorToFile(Exception exception, String fileName) throws IOException {
		String formattedMessage = String.format(MessageConstants.MESSAGE_9, fileName);
		System.err.println(formattedMessage);
		Path path = Paths.get(fileName);
		List<String> errorLog = new ArrayList<String>();
		errorLog.add(Constants.ERROR);
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		errorLog.add(sw.toString());
		StringBuffer sb = new StringBuffer();
		errorLog.forEach((line) -> sb //
				.append(line) //
				.append(System.lineSeparator()) //
				);
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			writer.write(sb.toString());
		}
	}
	
	public static void writeConsoleToFile(String fileName) throws IOException {
		Context context = Context.getInstance();
		Path path = Paths.get(fileName);
		List<String> console = context.getConsole();
		StringBuffer sb = new StringBuffer();
		console.forEach((line) -> sb //
				.append(line) //
				.append(System.lineSeparator()) //
				);
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			writer.write(sb.toString());
		}
	}
}
