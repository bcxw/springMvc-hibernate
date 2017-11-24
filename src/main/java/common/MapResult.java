package common;

import java.util.HashMap;
import java.util.Map;

public class MapResult {
	private static final String SUCCESS = "success";
	private static final String MESSAGE = "message";
	private static final String DATA = "data";
	private static final String TOTAL = "total";

	public static Map<String, Object> success() {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put(SUCCESS, true);
		return returnMap;
	}

	public static Map<String, Object> success(String message) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put(SUCCESS, true);
		returnMap.put(MESSAGE, message);
		return returnMap;
	}

	public static Map<String, Object> success(Object data) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put(SUCCESS, true);
		returnMap.put(DATA, data);
		return returnMap;
	}

	public static Map<String, Object> success(Object data, String message) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put(SUCCESS, true);
		returnMap.put(DATA, data);
		returnMap.put(MESSAGE, message);
		return returnMap;
	}

	public static Map<String, Object> success(Object data, int total) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put(SUCCESS, true);
		returnMap.put(DATA, data);
		returnMap.put(TOTAL, total);
		return returnMap;
	}

	public static Map<String, Object> success(Object data, int total, String message) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put(SUCCESS, true);
		returnMap.put(DATA, data);
		returnMap.put(TOTAL, total);
		returnMap.put(MESSAGE, message);
		return returnMap;
	}

	public static Map<String, Object> failure() {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put(SUCCESS, false);
		return returnMap;
	}

	public static Map<String, Object> failure(String message) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put(SUCCESS, false);
		returnMap.put(MESSAGE, message);
		return returnMap;
	}

	public static Map<String, Object> failure(Object data) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put(SUCCESS, false);
		returnMap.put(DATA, data);
		return returnMap;
	}

	public static Map<String, Object> failure(Object data, String message) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put(SUCCESS, false);
		returnMap.put(DATA, data);
		returnMap.put(MESSAGE, message);
		return returnMap;
	}

	public static Map<String, Object> failure(Object data, int total) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put(SUCCESS, false);
		returnMap.put(DATA, data);
		returnMap.put(TOTAL, total);
		return returnMap;
	}
	
	public static Map<String, Object> failure(Object data, int total, String message) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put(SUCCESS, false);
		returnMap.put(DATA, data);
		returnMap.put(TOTAL, total);
		returnMap.put(MESSAGE, message);
		return returnMap;
	}

}
