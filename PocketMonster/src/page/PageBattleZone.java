package page;

import java.util.ArrayList;

import common.Player;
import common.PocketMon;
import common.Skill;
import enums.PageType;
import enums.TeamType;
import manager.PageManager;
import manager.InputManager;
import manager.TableDataManager;

public class PageBattleZone extends Page {

	private PocketMon mainPocketMon;
	private PocketMon wildPocketMon;
	
	public PageBattleZone() {
		name = "전투 지역";
	}

	@Override
	public PageType getType() {
		return PageType.BATTLE_ZONE;
	}
	
	@Override
	public void onChanged() {
		mainPocketMon = Player.getInstance().getMainPocketMon();
		wildPocketMon = TableDataManager.getInstance().monsterTable.spawnRandom();
		wildPocketMon.teamType = TeamType.ENEMY;
		
		super.onChanged();
	}
	
	@Override
	public void printAction() {
		System.out.printf("앗! 야생의 %s(이)가 나타났다!\n", wildPocketMon.name);
		
		while (true) {
			System.out.println("[1] 싸우다 [2] 포켓몬 교체 [3] 가방 [4] 도망치다");
			
			int input = InputManager.getInstance().getScanner().nextInt();
			switch (input) {
			case 1:
				battle();
				break;
			case 2:
				changePocketMon();
				break;
			case 3:
				openInventory();
			case 4:
				escape();
				return;
			}	
		}
	}
	
	private void battle() {
		ArrayList<Skill> skillList = mainPocketMon.skillList; 
		
		System.out.println("[스킬 목록]");
		for(int i = 0; i < skillList.size(); i++) {
			System.out.printf("[%d] %s ", i + 1, skillList.get(i).name);
		}
		System.out.printf("[%d] %s\n", skillList.size() + 1, "취소");

		int input = InputManager.getInstance().getScanner().nextInt();
		int skillIndex = input - 1;
		if (skillIndex < 0 || skillIndex >= skillList.size()) {
			return;
		}
		
		Skill skill = skillList.get(skillIndex);
		skill.fire(mainPocketMon, wildPocketMon);
		
		if (wildPocketMon.isAlive() == false) {
			return;
		}
		
		wildPocketMon.fireSkillByRandom(mainPocketMon);
	}
	
	private void changePocketMon() {
		
	}
	
	private void openInventory() {
		
	}
	
	private void escape() {
		System.out.println("성공적으로 도망쳤다.");
		System.out.println();
		
		PageManager.getInstance().changePage(PageType.WORLD);
	}
}
