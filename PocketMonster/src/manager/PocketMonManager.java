package manager;

import common.PocketMon;
import common.PocketMonDB;

public class PocketMonManager {
	
	private PocketMonDB db;
	
	private static PocketMonManager instance;
	public static PocketMonManager getInstance() {
		if (instance == null) {
			instance = new PocketMonManager();
		}
		return instance;
	}
	
	private PocketMonManager() {
		db = new PocketMonDB();
		db.add(new PocketMon("피카츄"));
		db.add(new PocketMon("파이리"));
		db.add(new PocketMon("꼬부기"));
		db.add(new PocketMon("이상해씨"));
		db.add(new PocketMon("구구"));
		db.add(new PocketMon("피죤"));
		db.add(new PocketMon("또가스"));
		db.add(new PocketMon("아보"));
		db.add(new PocketMon("냐옹"));
		db.add(new PocketMon("단데기"));
	}
	
	public PocketMon get(String name) {
		PocketMon pocketMon = null;
		try {
			pocketMon = db.findByName(name).clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return pocketMon;
	}
	
	public PocketMon get(int index) {
		PocketMon pocketMon = null;
		try {
			pocketMon = db.findByIndex(index).clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return pocketMon;
	}
	
	public PocketMon getRandom() {
		int random = (int)(Math.random() * db.size());

		return get(random);
	}
}
