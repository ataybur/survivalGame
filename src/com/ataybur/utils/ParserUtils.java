package com.ataybur.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ataybur.enums.LineTypes;
import com.ataybur.pojo.base.Character;

public class ParserUtils {
	public static void parseIntoContext(String line) throws InstantiationException, IllegalAccessException {
		LineTypes lineTypes = parseForLineType(line);
		String[] parsedLine = parseLine(line, lineTypes);
		putParsedLineIntoContext(parsedLine, lineTypes);
	}

	private static LineTypes parseForLineType(String line) {
		LineTypes result = null;
		if (StringUtils.isNotEmpty(line)) {
			for (LineTypes lineTypes : LineTypes.values()) {
				if (isFound(line, lineTypes.getRegex())) {
					result = lineTypes;
					break;
				}
			}
		}
		return result;
	}

	private static String[] parseLine(String line, LineTypes lineTypes) {
		String[] result = null;
		Pattern pattern = Pattern.compile(lineTypes.getRegex());
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()) {
			int size = matcher.groupCount();
			result = new String[size];
			for (int i = 1; i <= size; i++) {
				result[i - 1] = matcher.group(i);
			}
		}
		return result;
	}

	private static void putParsedLineIntoContext(String[] parsedLine, LineTypes lineTypes) throws InstantiationException, IllegalAccessException {
		switch (lineTypes) {
		case RANGE:
			if (parsedLine != null && parsedLine.length == 1) {
				ContextUtils.setRange(parsedLine[0]);
			}
			break;
		case HP:
			if (parsedLine != null && parsedLine.length == 2) {
				ContextUtils.setCharacterPoint(parsedLine, Character::setHp);
			}
			break;
		case ATTACK_POINT:
			if (parsedLine != null && parsedLine.length == 2) {
				ContextUtils.setCharacterPoint(parsedLine, Character::setAttackPoint);
			}
			break;
		case ENEMY:
			if (parsedLine != null && parsedLine.length == 1) {
				ContextUtils.setEnemy(parsedLine[0]);
			}
			break;
		case ENEMY_POSITION:
			if (parsedLine != null && parsedLine.length == 2) {
				ContextUtils.setEnemyPosition(parsedLine);
			}
			break;
		}
	}

	private static boolean isFound(String line, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(line);
		return matcher.find();
	}

}
