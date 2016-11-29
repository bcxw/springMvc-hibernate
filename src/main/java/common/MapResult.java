package common;

import java.util.HashMap;
import java.util.Map;

public class MapResult {
  private static final String RESULT_SUCCESS = "success";
  private static final String RESULT_MESSAGE = "message";
  private static final String RESULT_DATA = "data";
  private static final String RESULT_TOTAL = "total";

  public static Map<String, Object> success() {
    Map<String, Object> returnMap = new HashMap<String, Object>();
    returnMap.put(RESULT_SUCCESS, true);
    return returnMap;
  }

  public static Map<String, Object> success(String message) {
    Map<String, Object> returnMap = new HashMap<String, Object>();
    returnMap.put(RESULT_SUCCESS, true);
    returnMap.put(RESULT_MESSAGE, message);
    return returnMap;
  }

  public static Map<String, Object> success(Object data) {
    Map<String, Object> returnMap = new HashMap<String, Object>();
    returnMap.put(RESULT_SUCCESS, true);
    returnMap.put(RESULT_DATA, data);
    return returnMap;
  }

  public static Map<String, Object> success(Object data, int total) {
    Map<String, Object> returnMap = new HashMap<String, Object>();
    returnMap.put(RESULT_SUCCESS, true);
    returnMap.put(RESULT_DATA, data);
    returnMap.put(RESULT_TOTAL, total);
    return returnMap;
  }

  public static Map<String, Object> failure() {
    Map<String, Object> returnMap = new HashMap<String, Object>();
    returnMap.put(RESULT_SUCCESS, false);
    return returnMap;
  }

  public static Map<String, Object> failure(String message) {
    Map<String, Object> returnMap = new HashMap<String, Object>();
    returnMap.put(RESULT_SUCCESS, false);
    returnMap.put(RESULT_MESSAGE, message);
    return returnMap;
  }

  public static Map<String, Object> failure(Object data) {
    Map<String, Object> returnMap = new HashMap<String, Object>();
    returnMap.put(RESULT_SUCCESS, false);
    returnMap.put(RESULT_DATA, data);
    return returnMap;
  }

  public static Map<String, Object> failure(Object data, int total) {
    Map<String, Object> returnMap = new HashMap<String, Object>();
    returnMap.put(RESULT_SUCCESS, false);
    returnMap.put(RESULT_DATA, data);
    returnMap.put(RESULT_TOTAL, total);
    return returnMap;
  }

}
