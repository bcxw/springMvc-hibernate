package serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.ConfigService;
import common.ResponseResult;
import dao.Config;
import daoImpl.ConfigDAOImpl;

@Service
public class ConfigServiceImpl implements ConfigService{
	
	@Autowired
	private ConfigDAOImpl configDAOImpl;
	
	public ResponseResult getConfig() {
		// TODO Auto-generated method stub
		Config config=(Config) configDAOImpl.findById(0);
		Map<String,String> map=new HashMap<String,String>();
		map.put("systemName", config.getSystemName());
		return ResponseResult.success(map);
	}
	
	
	
	
	
}
