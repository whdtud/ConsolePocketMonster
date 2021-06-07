package page;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import common.Player;
import common.PocketMon;
import enums.PageType;
import manager.PageManager;

public class PageLoading extends Page {

	
	public PageLoading() {
		name = "로딩";
	}
	
	@Override
	public PageType getType() {
		return PageType.LOADING;
	}
	
	@Override
	public void onChanged() {
		printAction();
	}
	
	@Override
	protected void printAction() {
		loadJson();
		
		if (Player.getInstance().wasInit()) {
			PageManager.getInstance().changePage(PageType.WORLD);	
		} else {
			PageManager.getInstance().changePage(PageType.PROLOGUE);
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
