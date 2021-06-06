package page;

import enums.PageType;

public abstract class Page {

	protected String name;
	
	public abstract PageType getType();
	
	protected void printName() {
		System.out.printf("[==%s==]\n", name);
	}
	
	protected abstract void printAction();
	
	public void onChanged() {
		printName();
		printAction();
	}
}
