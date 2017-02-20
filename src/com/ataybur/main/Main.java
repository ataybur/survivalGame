package com.ataybur.main;

import java.io.IOException;

import com.ataybur.constants.Constants;
import com.ataybur.pojo.Context;
import com.ataybur.utils.FileUtils;
import com.ataybur.utils.GameUtils;

public class Main {
	private static Context context = Context.getInstance();

	public static void main(String[] args) {
		String fileNameOutput = null;
		try {
			String fileNameInput = FileUtils.retrieveInputFileName(args);
			fileNameOutput = FileUtils.retrieveOutputFileName(args);
			FileUtils.readFile(fileNameInput);
			GameUtils.startGame();
			FileUtils.writeConsoleToFile(fileNameOutput);
		} catch (RuntimeException | IOException | InstantiationException | IllegalAccessException e) {
			try {
				if (fileNameOutput == null) {
					fileNameOutput = Constants.ERROR_LOG;
				}
				FileUtils.writeErrorToFile(e, fileNameOutput);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
