package manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import enums.PageType;
import page.*;

public class PageManager {

	private HashMap<PageType, Page> pageMap = new HashMap<PageType, Page>();

	private Page currentPage;
	
	private static PageManager instance;
	public static PageManager getInstance() {
		if (instance == null) {
			instance = new PageManager(); 
		}
		return instance;
	}
	
	private PageManager() {
		pageMap.put(PageType.PROLOGUE, new PagePrologue());
		pageMap.put(PageType.WORLD, new PageWorld());
		pageMap.put(PageType.BATTLE_ZONE, new PageBattleZone());
		pageMap.put(PageType.POCKET_MON_CENTER, new PagePocketMonCenter());
		pageMap.put(PageType.EXIT, new PageExit());
	}
	
	public Page getCurrentPage() {
		return currentPage;
	}

	public void changePage(int index) {
		PageType type = PageType.values()[index]; 
		changePage(type);
	}
	
	public void changePage(PageType type) {
		changePage(pageMap.get(type));
	}
	
	public void changePage(Page area) {
		currentPage = area;
		currentPage.onChanged();
	}
	
	public ArrayList<Page> getOtherPageList() {
		ArrayList<Page> list = new ArrayList<Page>();
		
		for (Map.Entry<PageType, Page> entry : pageMap.entrySet()) {
			if (entry.getKey() != currentPage.getType()) {
				list.add(entry.getValue());
			}
		}

		Collections.sort(list, new Comparator<Page>() {
			@Override
			public int compare(Page p1, Page p2) {
				return p1.getType().getValue() - p2.getType().getValue();
			}
		});
		
		return list;
	}
}
