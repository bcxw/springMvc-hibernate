package service;

import java.util.Map;

import common.ResponseResult;

public interface MenuService {
	
	public ResponseResult getChildrenMenu(Map<String,String> paramMap);
	
	public ResponseResult saveMenu(Map<String,String> paramMap);
	
	public ResponseResult deleteMenu(Map<String,String> paramMap);
}
