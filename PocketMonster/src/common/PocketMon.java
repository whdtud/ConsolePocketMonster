package common;

import java.util.ArrayList;

import enums.TeamType;

public class PocketMon extends Character implements Cloneable {

	public int id;
	public int level;
	public int hp;
	public int maxHp;
	public int sp;
	public int maxSp;
	
	public ArrayList<Skill> skillList = new ArrayList<Skill>();
	
	public PocketMon(int id, String name, int level, int hp, int sp, ArrayList<Skill> skillList) {
		this.id = id;
		this.name = name;
		this.teamType = TeamType.NEUTRAL;
		this.level = level;
		this.maxHp = hp;
		this.hp = maxHp;
		this.maxSp = sp;
		this.sp = maxSp;
		
		this.skillList = skillList;
	}

	public PocketMon clone() throws CloneNotSupportedException {
		return (PocketMon)super.clone();
	}
	
	public void onDamaged(int value) {
		hp -= value;
	}
	
	public void onRecovery(int value) {
		hp = Math.min(hp + value, maxHp);
	}
	
	public boolean isAlive() {
		if (hp <= 0) {
			return false;
		}
		return true;
	}
	
	public void fireSkill(int skillIndex, PocketMon target) {
		skillList.get(skillIndex).fire(this, target);
	}
	
	public void fireSkillByRandom(PocketMon target) {
		int randomIndex = (int)(Math.random() * skillList.size());
		fireSkill(randomIndex, target);
	}
}
