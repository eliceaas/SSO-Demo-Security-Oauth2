package com.brt360.memoryDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleMemoryMap {
	
	public static Map<String, List<String>> map;
	
	static {
		map = new HashMap<String, List<String>>();
		
		//初始用户root的权限
		List<String> roles = new ArrayList<String>();
		roles.add("ROLE_ADMIN");
		map.put("root", roles);
	}

}
