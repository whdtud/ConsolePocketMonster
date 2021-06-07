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
import enums.TeamType;
import manager.PageManager;
import manager.TableDataManager;

public class PageLoading extends Page {

	
	public PageLoading() {
		name = null;
	}
	
	@Override
	public PageType getType() {
		return PageType.LOADING;
	}
	
	@Override
	public void init() {
		// do nothing.
	}

	@Override
	public void printAction() {
		loadJson();
		
		if (Player.getInstance().wasInit()) {
			PageManager.getInstance().setCurrentPage(PageType.WORLD);	
		} else {
			PageManager.getInstance().setCurrentPage(PageType.PROLOGUE);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void loadJson() {
		TableDataManager.getInstance().loadJson();
		
		String path = System.getProperty("user.dir") + "/data/Save.json";
		
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
				
				int id = ((Long)pocketMonObj.get("id")).intValue();
				
				PocketMon pocketMon = TableDataManager.getInstance().monsterTable.spawn(id);
				pocketMon.level = ((Long)pocketMonObj.get("level")).intValue();
				pocketMon.hp = ((Long)pocketMonObj.get("hp")).intValue();
				pocketMon.maxHp = ((Long)pocketMonObj.get("maxHp")).intValue();
				pocketMon.sp = ((Long)pocketMonObj.get("sp")).intValue();
				pocketMon.maxSp = ((Long)pocketMonObj.get("maxSp")).intValue();
				pocketMon.teamType = TeamType.FRIENDLY;

				Player.getInstance().addPocketMon(pocketMon);
			}
		} catch (IOException e) {
			// do nothing.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
