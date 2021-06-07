package common;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import enums.TeamType;

public class Player extends Character {

	public ArrayList<PocketMon> pocketMonList = new ArrayList<PocketMon>();
	
	private final int MAIN_SLOT_INDEX = 0;

	private static Player instance;
	public static Player getInstance() {
		if (instance == null) {
			instance = new Player();
		}
		return instance;
	}
	
	private Player() {
		teamType = TeamType.FRIENDLY;
	}
	
	public boolean wasInit() {
		if (pocketMonList.size() > 0) {
			return true;
		}
		return false;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if (name == null || name.isEmpty()) {
			return;
		}
		
		this.name = name;
	}
	
	public PocketMon getMainPocketMon() {
		return pocketMonList.get(MAIN_SLOT_INDEX);
	}
	
	public void addPocketMon(PocketMon pocketMon) {
		pocketMonList.add(pocketMon);
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getJsonData() {
		JSONObject innerObj = new JSONObject();
		innerObj.put("name", name);
		
		JSONArray arr = new JSONArray();
		for (PocketMon pm : pocketMonList) {
			JSONObject obj = new JSONObject();
			obj.put("name", pm.name);
			obj.put("level", pm.level);
			obj.put("hp", pm.hp);
			obj.put("maxHp", pm.maxHp);
			obj.put("sp", pm.sp);
			obj.put("maxSp", pm.maxSp);

			arr.add(obj);
		}
		innerObj.put("pocketMonList", arr);
		
		JSONObject outerObj = new JSONObject();
		outerObj.put("player", innerObj);
		
		return outerObj;
	}
}
