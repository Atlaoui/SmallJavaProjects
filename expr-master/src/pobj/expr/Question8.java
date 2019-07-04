package pobj.expr;

import java.util.HashMap;
import java.util.Map;

public class Question8 {
	public static Map<String,Integer> env1() {
		
		return new HashMap<String,Integer>();
	}

	public static Map<String, Integer> env2() {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("x", 10);
		map.put("y", 20);
		return map;
	}

	public static Map<String, Integer> env3() {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("z", 9);
		return map;
	}
	
}
