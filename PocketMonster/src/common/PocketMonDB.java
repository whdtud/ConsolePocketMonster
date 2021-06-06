package common;

import java.util.ArrayList;

public class PocketMonDB {
	
	private ArrayList<PocketMon> db = new ArrayList<PocketMon>();
	
	public int size() {
		return db.size();
	}
	
	public void add(PocketMon data) {
		db.add(data);
	}
	
	public PocketMon findByIndex(int index) {
		if (index >= db.size()) {
			return null;
		}
		return db.get(index);
	}
	
	public PocketMon findByName(String name) {
		for (PocketMon pm : db) {
			if (name == pm.name) {
				return pm;
			}
		}
		return null;
	}
}
