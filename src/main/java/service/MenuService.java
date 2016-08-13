package service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import common.ResponseResult;

public interface MenuService {
	
	public ResponseResult getChildrenMenu(Map<String,String> paramMap);
	
	public ResponseResult saveMenu(Map<String,String> paramMap);
	
	public ResponseResult deleteMenu(Map<String,String> paramMap);
	
	/**
	 * 获取image/icon中所有的小图标作为下拉选项
	 * @param paramMap
	 * @return
	 */
	public ResponseResult getIcons(HttpServletRequest request,Map<String,String> paramMap);
}
