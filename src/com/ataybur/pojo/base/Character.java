package com.ataybur.pojo.base;

public abstract class Character {

	private Integer hp;
	private Integer attackPoint;

	public Integer getHp() {
		return hp;
	}

	public void setHp(Integer hp) {
		this.hp = hp;
	}

	public Integer getAttackPoint() {
		return attackPoint;
	}

	public void setAttackPoint(Integer attackPoint) {
		this.attackPoint = attackPoint;
	}

	@Override
	public String toString() {
		return "Character [hp=" + hp + ", attackPoint=" + attackPoint + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attackPoint == null) ? 0 : attackPoint.hashCode());
		result = prime * result + ((hp == null) ? 0 : hp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Character other = (Character) obj;
		if (attackPoint == null) {
			if (other.attackPoint != null)
				return false;
		} else if (!attackPoint.equals(other.attackPoint))
			return false;
		if (hp == null) {
			if (other.hp != null)
				return false;
		} else if (!hp.equals(other.hp))
			return false;
		return true;
	}
}
