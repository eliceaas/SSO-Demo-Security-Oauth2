package com.brt360.memoryDB;

import java.util.HashMap;
import java.util.Map;

public class UserMemoryMap {
	
	public static Map<String, String> map;
	
	static {
		map = new HashMap<String, String>();
		
		//初始用户root
		map.put("root", "root");
	}

}
