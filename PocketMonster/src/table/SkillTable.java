package table;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import enums.SkillType;
import table.data.SkillData;

public class SkillTable {

	private HashMap<Integer, SkillData> map = new HashMap<Integer, SkillData>();
	
	public SkillData get(int id) {
		return map.get(id);
	}
	
	public ArrayList<SkillData> getSkillList(int[] skillIds) {
		ArrayList<SkillData> skillList = new ArrayList<SkillData>();
		for (int id : skillIds) {
			skillList.add(get(id));
		}
		return skillList;
	}
	
	@SuppressWarnings("unchecked")
	public void loadJon() {
		String path = System.getProperty("user.dir") + "/data/SkillTable.json";
		
		JSONParser parser = new JSONParser();
		
		try (Reader reader = new FileReader(path)) {
			JSONObject rootObj = (JSONObject)parser.parse(reader);
			
			JSONArray skills = (JSONArray)rootObj.get("array");
			
			Iterator<JSONObject> iter = skills.iterator();
			while (iter.hasNext()) {
				JSONObject obj = iter.next();
				
				SkillData data = new SkillData();
				data.id = ((Long)obj.get("id")).intValue();
				data.name = (String)obj.get("name");
				data.type = SkillType.valueOf((String)obj.get("type"));
				data.level = ((Long)obj.get("level")).intValue();
				data.value = ((Long)obj.get("value")).intValue();
				data.sp = ((Long)obj.get("sp")).intValue();
				
				map.put(data.id, data);
			}
		} catch (IOException e) {
			// do nothing.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
