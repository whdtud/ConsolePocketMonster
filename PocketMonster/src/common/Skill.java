package common;

import enums.TeamType;

enum SkillType {
	ATTACK,
	DEFENSE,
	RECOVERY
}

public class Skill {
	
	public String name;
	
	private SkillType type;
	private int value;
	
	public Skill(String name) {
		this.name = name;
		this.type = SkillType.ATTACK;
		this.value = 1;
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
