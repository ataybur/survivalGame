package com.ataybur.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.BiConsumer;

import com.ataybur.constants.Constants;
import com.ataybur.constants.MessageConstants;
import com.ataybur.pojo.Context;
import com.ataybur.pojo.Enemy;
import com.ataybur.pojo.Field;
import com.ataybur.pojo.Hero;

public class ContextUtils {
	public static void setRange(String rangeString) throws InstantiationException, IllegalAccessException {
		Context context = Context.getInstance();
		Field field = Utils.getter(context::getField, Field.class);
		Integer range = Utils.convertIntToString(rangeString);
		field.setRange(range);
		context.setField(field);
	}

	public static <T extends com.ataybur.pojo.base.Character> void setCharacterPoint(String[] characterInfo,
			BiConsumer<T, Integer> setter) throws InstantiationException, IllegalAccessException {
		Context context = Context.getInstance();
		List<Enemy> enemyList = Utils.getter(context::getEnemyList, ArrayList.class);
		String characterType = characterInfo[0];
		Integer point = Utils.convertIntToString(characterInfo[1]);
		if (StringUtils.isNotEmpty(characterType)) {
			if (characterType.equalsIgnoreCase(Constants.HERO)) {
				Hero hero = Utils.getter(context::getHero, Hero.class);
				setter.accept((T) hero, point);
				context.setHero(hero);
			} else {
				Optional<Enemy> optEnemy = enemyList //
						.stream() //
						.filter((enemy) -> characterType.equalsIgnoreCase(enemy.getSpecies())) //
						.findFirst();
				boolean optEnemyIsPresent = optEnemy.isPresent();
				if (!optEnemyIsPresent) {
					optEnemy = Optional.of(new Enemy());
				}
				Enemy enemy = optEnemy.get();
				enemy.setSpecies(characterType);
				setter.accept((T) enemy, point);
				if (!optEnemyIsPresent) {
					enemyList.add(optEnemy.get());
				}
			}
		}
		context.setEnemyList(enemyList);
	}

	public static void setEnemyPosition(String[] characterInfo) throws InstantiationException, IllegalAccessException {
		Context context = Context.getInstance();
		List<Enemy> enemyList = Utils.getter(context::getEnemyList, ArrayList.class);
		Field field = Utils.getter(context::getField, Field.class);
		SortedMap<Integer, Enemy> enemyMap = Utils.getter(field::getEnemyMap, TreeMap.class);
		String characterType = characterInfo[0];
		Integer position = Utils.convertIntToString(characterInfo[1]);
		if (StringUtils.isNotEmpty(characterType)) {
			Optional<Enemy> optEnemy = enemyList //
					.stream() //
					.filter((enemy) -> characterType.equalsIgnoreCase(enemy.getSpecies())) //
					.findFirst();
			boolean optEnemyIsPresent = optEnemy.isPresent();
			if (!optEnemyIsPresent) {
				String formattedMessage = String.format(MessageConstants.MESSAGE_6, characterType);
				throw new RuntimeException(formattedMessage);
			}
			Enemy enemy = optEnemy.get();
			enemyMap.put(position, enemy);
		}
		field.setEnemyMap(enemyMap);
	}

	public static void setEnemy(String characterType) throws InstantiationException, IllegalAccessException {
		Context context = Context.getInstance();
		List<Enemy> enemyList = Utils.getter(context::getEnemyList, ArrayList.class);
		if (StringUtils.isNotEmpty(characterType)) {
			Optional<Enemy> optEnemy = enemyList //
					.stream() //
					.filter((enemy) -> characterType.equalsIgnoreCase(enemy.getSpecies())) //
					.findFirst();
			boolean optEnemyIsPresent = optEnemy.isPresent();
			if (!optEnemyIsPresent) {
				optEnemy = Optional.of(new Enemy());
			}
			Enemy enemy = optEnemy.get();
			enemy.setSpecies(characterType);
			if (!optEnemyIsPresent) {
				enemyList.add(enemy);
			}
		}
		context.setEnemyList(enemyList);
	}

	public static void addToConsole(String str, Object... args) throws InstantiationException, IllegalAccessException {
		Context context = Context.getInstance();
		List<String> console = Utils.getter(context::getConsole, ArrayList.class);
		String formattedMessage = String.format(str, args);
		console.add(formattedMessage);
		context.setConsole(console);
	}

	public static void printConsole() throws InstantiationException, IllegalAccessException {
		Context context = Context.getInstance();
		List<String> console = Utils.getter(context::getConsole, ArrayList.class);
		console.forEach(System.out::println);
	}

}
