package manager;

import enums.PageType;

import java.io.File;
import java.io.FileWriter;

import org.json.simple.JSONObject;

import common.Player;

public class GameManager {

	public void init() {
	}
	
	public void release() {
		InputManager.getInstance().release();
		saveJson();
	}
	
	public void start() {
		PageManager.getInstance().changePage(PageType.LOADING);
	}

	private void saveJson() {
		String dirPath = System.getProperty("user.dir") + "/data";
		String filePath = dirPath + "/Save.json";
		
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
}