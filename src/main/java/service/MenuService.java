package service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface MenuService {

	public Map<String, Object> getMenuTree(Map<String, String> paramMap);

	public Map<String, Object> saveMenu(Map<String, String> paramMap);

	public Map<String, Object> deleteMenu(Map<String, String> paramMap);

	/**
	 * 获取image/icon中所有的小图标作为下拉选项
	 * 
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> getIcons(HttpServletRequest request,
			Map<String, String> paramMap);
}
