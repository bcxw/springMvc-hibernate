package controller;

import java.util.Map;
import java.util.WeakHashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.ConfigService;

@Controller
@RequestMapping("/configAction")
public class ConfigAction {

	@Autowired
	private ConfigService configService;

	@ResponseBody
	@RequestMapping("/getConfig.action")
	@SuppressWarnings("unchecked")
	public Map<String, Object> getConfig(HttpSession httpSession) {
		Map<String, Object> result = configService.getConfig();
		String userName = (String) httpSession.getAttribute("userName");
		if (userName != null && !userName.isEmpty()) {
			Map<String, String> map = (Map<String, String>) result.get("data");
			map.put("userName", userName);
			result = new WeakHashMap<String,Object>(){{put("success",true);put("data",map);}};
		}
		return result;
	}

}