package com.ataybur.utils;

import java.util.SortedMap;

import com.ataybur.constants.MessageConstants;
import com.ataybur.pojo.Context;
import com.ataybur.pojo.Enemy;
import com.ataybur.pojo.Field;
import com.ataybur.pojo.Hero;
import com.ataybur.pojo.base.Character;

public class GameUtils {
	public static void startGame() throws InstantiationException, IllegalAccessException {
		Context context = Context.getInstance();
		Hero hero = context.getHero();
		Field field = context.getField();
		SortedMap<Integer, Enemy> enemyMap = field.getEnemyMap();
		ContextUtils.addToConsole(MessageConstants.MESSAGE_1, hero.getHp());
		for (Integer i = 1; i <= field.getRange(); i++) {
			Enemy currentEnemy = enemyMap.get(i);
			if (currentEnemy != null) {
				if (!isHeroAlive(hero, currentEnemy)) {
					ContextUtils.addToConsole(MessageConstants.MESSAGE_2, i);
					return;
				}
			}
		}
		ContextUtils.addToConsole(MessageConstants.MESSAGE_5);
	}

	private static boolean isHeroAlive(Hero hero, Enemy currentEnemy) throws InstantiationException, IllegalAccessException {
		Double heroHpDouble = getCharacterRemainingHp(hero, currentEnemy);
		boolean result;
		if (heroHpDouble > 0d) {
			hero.setHp(heroHpDouble.intValue());
			result = true;
			ContextUtils.addToConsole(MessageConstants.MESSAGE_3, currentEnemy.getSpecies(),
					heroHpDouble.intValue());
		} else {
			Double enemyHpDouble = getCharacterRemainingHp(currentEnemy, hero);
			ContextUtils.addToConsole(MessageConstants.MESSAGE_4, currentEnemy.getSpecies(),
					enemyHpDouble.intValue());
			result = false;
		}
		return result;
	}

	private static <T extends Character, E extends Character> Double getCharacterRemainingHp(T main, E rival) {
		Integer mainHp = main.getHp();
		Double mainHpDouble = mainHp.doubleValue();
		Integer mainAttackPoint = main.getAttackPoint();
		Integer rivalHp = rival.getHp();
		Integer rivalAttackPoint = rival.getAttackPoint();
		Integer addition = rivalHp % mainAttackPoint;
		if (addition != 0) {
			addition = mainAttackPoint - addition;
		}
		Double rivalHpWithAddition = Double.valueOf(rivalHp + addition);
		Double multiplier = rivalHpWithAddition / mainAttackPoint.doubleValue();
		mainHpDouble -= rivalAttackPoint.doubleValue() * multiplier;
		return mainHpDouble;
	}
}
