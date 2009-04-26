package edu.cmu.tsp6.client;

import java.util.HashMap;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class Globals {
	private static HashMap<String, Panel> panels = new HashMap<String, Panel>();
	private static HashMap<String, Widget> widgets = new HashMap<String, Widget>();
	
	public static void putPanel(String name, Panel panel){
		panels.put(name, panel);
	}
	
	public static Panel getPanel(String name) {
		return panels.get(name);
	}
	
	public static void putPanel(String name, Widget widget){
		widgets.put(name, widget);
	}
	
	public static Widget getWidget(String name) {
		return widgets.get(name);
	}
}
