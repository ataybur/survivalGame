package com.ataybur.enums;

import com.ataybur.constants.RegexConstants;

public enum LineTypes {
	RANGE(RegexConstants.REGEX_3), //
	HP(RegexConstants.REGEX_5), //
	ATTACK_POINT(RegexConstants.REGEX_2), //
	ENEMY(RegexConstants.REGEX_6), //
	ENEMY_POSITION(RegexConstants.REGEX_1);
	
	private String regex;

	private LineTypes(String regex) {
		this.regex = regex;
	}
	
	public String getRegex() {
		return regex;
	}
}
