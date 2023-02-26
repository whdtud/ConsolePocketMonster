package util;

public class FileUtil {

	public static String ROOT_DIR = System.getProperty("user.dir"); 
	
	public static String getSaveFilePath() {
		return ROOT_DIR + "/data/Save.json";
	}
	
	public static String getSkillFilePath() {
		return ROOT_DIR + "/data/SkillTable.json";
	}
	
	public static String getMonsterFilePath() {
		return ROOT_DIR + "/data/MonsterTable.json";
	}
}
