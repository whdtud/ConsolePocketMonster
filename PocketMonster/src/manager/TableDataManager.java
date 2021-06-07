package manager;

import table.MonsterTable;
import table.SkillTable;

public class TableDataManager {
	
	public MonsterTable monsterTable;
	public SkillTable skillTable;
	
	private static TableDataManager instance;
	public static TableDataManager getInstance() {
		if (instance == null) {
			instance = new TableDataManager();
		}
		return instance;
	}
	
	private TableDataManager() {
		monsterTable = new MonsterTable();
		skillTable = new SkillTable();
	}
	
	public void loadJson() {
		monsterTable.loadJon();
		skillTable.loadJon();
	}
}
