package controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import service.MenuService;

@Controller
@RequestMapping("/menuAction")  
public class MenuAction {
	
	@Autowired
	private MenuService menuService;
	
	@ResponseBody
	@RequestMapping("/getMenuTree.action")
	public Map<String, Object> getMenuTree(@RequestParam Map<String,String> paramMap){
		return menuService.getMenuTree(paramMap);
	}
	
	@ResponseBody
	@RequestMapping("/deleteMenu.action")
	public Map<String, Object> deleteMenu(@RequestParam Map<String,String> paramMap){
		return menuService.deleteMenu(paramMap);
	}
	
	@ResponseBody
	@RequestMapping("/saveMenu.action")
	public Map<String, Object> saveMenu(@RequestParam Map<String,String> paramMap){
		return menuService.saveMenu(paramMap);
	}
	
}
