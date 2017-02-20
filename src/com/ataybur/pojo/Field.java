package com.ataybur.pojo;

import java.util.SortedMap;

public class Field {
	
	private Integer range;
	private SortedMap<Integer, Enemy> enemyMap;
	
	public Integer getRange() {
		return range;
	}
	public void setRange(Integer range) {
		this.range = range;
	}
	
	public SortedMap<Integer, Enemy> getEnemyMap() {
		return enemyMap;
	}
	
	public void setEnemyMap(SortedMap<Integer, Enemy> enemyMap) {
		this.enemyMap = enemyMap;
	}
	@Override
	public String toString() {
		return "Field [range=" + range + ", enemyMap=" + enemyMap + "]";
	}
	
}
