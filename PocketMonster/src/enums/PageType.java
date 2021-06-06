package enums;

public enum PageType {
	NONE(0),
	BATTLE_ZONE(1),
	POCKET_MON_CENTER(2),
	WORLD(3),
	PROLOGUE(4),
	EXIT(5);
	
	private int value;
	
	PageType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
