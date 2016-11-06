package service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repository.Config;
import service.ConfigService;

@Service
public class ConfigServiceImpl implements ConfigService {

	@Autowired
	private Config config;

	public Map<String, Object> getConfig() {
		List<Config> configs = config.findByProperty(Config.class, "param", "systemName");
		config=configs==null||configs.isEmpty()?config:configs.get(0);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("systemName", config!=null?config.getValue():"");
		return new WeakHashMap<String,Object>(){{put("success",true);put("data",map);}};
	}

}
