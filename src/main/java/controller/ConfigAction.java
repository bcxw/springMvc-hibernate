package controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.ResultUtil;
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
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		if (authentication.isAuthenticated()) {
			Map<String, String> map = (Map<String, String>) result.get("data");
			map.put("userName", authentication.getName());
			result = ResultUtil.success(map);
		}
		return result;
	}

}
