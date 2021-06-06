package manager;

import enums.PageType;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import common.Player;
import common.PocketMon;

public class GameManager {

	public void init() {
		loadJson();
	}
	
	public void release() {
		InputManager.getInstance().release();
		
		saveJson();
	}
	
	public void start() {
		if (Player.getInstance().pocketMonList.size() <= 0) {
			PageManager.getInstance().changePage(PageType.PROLOGUE);	
		} else {
			PageManager.getInstance().changePage(PageType.WORLD);
		}
	}
	
	private void saveJson() {
		String dirPath = System.getProperty("user.dir") + "/data";
		String filePath = dirPath + "/save.json";
		
		File dir = new File(dirPath);
		if (dir.exists() == false) {
			dir.mkdir();
		}
		
		JSONObject obj = Player.getInstance().getJsonData();
		
		try (FileWriter fw = new FileWriter(filePath)) {
			fw.write(obj.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void loadJson() {
		String path = System.getProperty("user.dir") + "/data/save.json";
		
		JSONParser parser = new JSONParser();
		
		try (Reader reader = new FileReader(path)) {
			JSONObject rootObj = (JSONObject)parser.parse(reader);
			
			JSONObject playerObj = (JSONObject)rootObj.get("player");
			String playerName = (String)playerObj.get("name");
			Player.getInstance().setName(playerName);
			
			JSONArray pocketMonList = (JSONArray)playerObj.get("pocketMonList");
			Iterator<JSONObject> iter = pocketMonList.iterator();
			while (iter.hasNext()) {
				JSONObject pocketMonObj = iter.next();
				String name = (String)pocketMonObj.get("name");
				PocketMon pocketMon = new PocketMon(name);
				pocketMon.level = ((Long)pocketMonObj.get("level")).intValue();
				pocketMon.hp = ((Long)pocketMonObj.get("hp")).intValue();
				pocketMon.maxHp = ((Long)pocketMonObj.get("maxHp")).intValue();
				pocketMon.sp = ((Long)pocketMonObj.get("sp")).intValue();
				pocketMon.maxSp = ((Long)pocketMonObj.get("maxSp")).intValue();
				
				Player.getInstance().addPocketMon(pocketMon);
			}
		} catch (IOException e) {
			// do nothing.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}