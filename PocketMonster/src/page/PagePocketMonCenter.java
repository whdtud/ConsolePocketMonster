package page;

import enums.PageType;

public class PagePocketMonCenter extends Page {

	public PagePocketMonCenter() {
		name = "포켓몬 센터";
	}
	
	@Override
	public PageType getType() {
		return PageType.POCKET_MON_CENTER;
	}
	
	@Override
	public void printAction() {
		
	}
}
