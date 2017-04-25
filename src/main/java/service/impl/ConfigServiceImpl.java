package service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.ConfigService;

import common.MapResult;
import repository.Config;

@Service
public class ConfigServiceImpl implements ConfigService {

  @Autowired
  private Config config;

  public Map<String, Object> getConfig() {
    List<Config> configs = config.findByProperty(Config.class, "param", "systemName");
    config = configs == null || configs.isEmpty() ? config : configs.get(0);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("systemName", config != null ? config.getValue() : "");
    return MapResult.success(map);
  }

}
