package action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import service.MenuService;
import common.ResponseResult;

@Controller
@RequestMapping("/menuAction")  
public class MenuAction {
	
	@Autowired
	private MenuService menuService;
	
	@ResponseBody
	@RequestMapping("/getChildrenMenu.action")
	public ResponseResult login(@RequestParam Map<String,String> paramMap){
		return menuService.getChildrenMenu(paramMap);
	}
	
	@ResponseBody
	@RequestMapping("/deleteMenu.action")
	public ResponseResult deleteMenu(@RequestParam Map<String,String> paramMap){
		return menuService.deleteMenu(paramMap);
	}
	
	@ResponseBody
	@RequestMapping("/saveMenu.action")
	public ResponseResult saveMenu(@RequestParam Map<String,String> paramMap){
		return menuService.saveMenu(paramMap);
	}
}
