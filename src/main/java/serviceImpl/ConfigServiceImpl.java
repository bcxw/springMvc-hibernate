package serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.ConfigService;

import common.ResponseResult;

import dao.Config;

@Service
public class ConfigServiceImpl implements ConfigService {

	@Autowired
	private Config config;

	public ResponseResult getConfig() {
		config = config.findById(Config.class, "a");
		Map<String, String> map = new HashMap<String, String>();
		map.put("systemName", config.getSystemName());
		return ResponseResult.success(map);
	}

}
