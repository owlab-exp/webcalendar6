package edu.cmu.tsp6.client;

import java.util.HashMap;

import com.google.gwt.user.client.ui.Panel;

public class Globals {
	private static HashMap<String, Panel> panels = new HashMap<String, Panel>();

	public static void putPanel(String name, Panel panel){
		panels.put(name, panel);
	}
	
	public static Panel getPanel(String name) {
		return panels.get(name);
	}
}
