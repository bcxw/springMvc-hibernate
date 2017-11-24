package service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface MenuService {

	public Map<String, Object> getMenuTree(Map<String, String> paramMap);

	public Map<String, Object> saveMenu(Map<String, String> paramMap);

	public Map<String, Object> deleteMenu(Map<String, String> paramMap);

}
