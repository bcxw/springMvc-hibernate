package action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	@RequestMapping("/getMenuTree.action")
	public ResponseResult getMenuTree(@RequestParam Map<String,String> paramMap){
		return menuService.getMenuTree(paramMap);
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
	
	/**
	 * 获取菜单所有小图标，作为下拉选项
	 * @param paramMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getIcons.action")
	public ResponseResult getIcons(HttpServletRequest request,@RequestParam Map<String,String> paramMap){
		return menuService.getIcons(request,paramMap);
	}
}
