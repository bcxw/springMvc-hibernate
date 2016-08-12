package service;

import java.util.Map;

import common.ResponseResult;

public interface LoginService {
	public ResponseResult login(Map<String,String> paramMap);
}
