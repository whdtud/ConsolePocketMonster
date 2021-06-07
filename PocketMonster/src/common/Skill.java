package common;

import enums.SkillType;
import enums.TeamType;

public class Skill {
	
	public int id;
	public String name;
	public SkillType type;
	public int level;
	public int value;
	public int sp;
	
	public Skill(int id, String name, SkillType type, int level, int value, int sp) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.level = level;
		this.value = value;
		this.sp = sp;
	}
	
	public void fire(PocketMon caster, PocketMon target) {
		if (caster.teamType == TeamType.ENEMY) {
			System.out.print("적 ");	
		}
		System.out.printf("%s의 %s!!\n", caster.name, name);
		
		switch (type) {
		case ATTACK:
			System.out.printf("%d의 데미지를 입혔다!!\n", value);
			target.onDamaged(value);
			break;
		case DEFENSE:
			break;
		case RECOVERY:
			caster.onRecovery(value);
			break;
		}
	}
}
