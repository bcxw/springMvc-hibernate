package action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.ConfigService;
import common.ResponseResult;

@Controller  
@RequestMapping("/configAction")  
public class ConfigAction {
	
	@Autowired
	private ConfigService configService;
	
	@ResponseBody
	@RequestMapping("/getConfig.action")
	public ResponseResult getConfig(HttpSession httpSession){
		ResponseResult result=configService.getConfig();
		String userName=(String)httpSession.getAttribute("userName");
		if(userName!=null&&!userName.isEmpty()){
			Map<String,String> map=(Map<String, String>) result.getData();
			map.put("userName", userName);
			result=ResponseResult.success(map);
		}
		return result;
	}
	
	
}
