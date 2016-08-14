package serviceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import service.MenuService;
import common.ResponseResult;
import dao.Menu;
import daoImpl.MenuDAOImpl;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuDAOImpl menuDAOImpl;

	public ResponseResult getChildrenMenu(Map<String,String> paramMap) {
		// TODO Auto-generated method stub
		int parentId=Integer.parseInt(paramMap.get("node"));
		List<Menu> menuList=menuDAOImpl.findByParentId(parentId);
		return ResponseResult.success(menuList);
	}
	
	@Override
	@Transactional
	public ResponseResult saveMenu(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		ResponseResult responseResult=null;
		String idStr=paramMap.get("id");
		int id=StringUtils.isNumeric(idStr)?Integer.parseInt(idStr):-1;
		
		/**1.同一父菜单，名称不能相同**/
		Menu checkMenu=new Menu();
		checkMenu.setText(paramMap.get("text"));
		checkMenu.setParentId(Integer.parseInt(paramMap.get("parentId")));
		
		List<Menu> checkMenuList=menuDAOImpl.findByExample(checkMenu);
		
		if(checkMenuList!=null&&!checkMenuList.isEmpty()&&checkMenuList.get(0).getId()!=id){
			return ResponseResult.failure("The same parent menu has a menu name, please re - enter the name of the menu!");
		}
		
		/**2.修改时检查不能将菜单的父菜单设置成自己或自己的子菜单**/
		if(id>0){
			List<Integer> parentsIdList=new ArrayList<Integer>();
			Menu oneParentMenu=menuDAOImpl.findById(Integer.parseInt(paramMap.get("parentId")));
			while(oneParentMenu!=null){
				parentsIdList.add(oneParentMenu.getId());
				oneParentMenu=menuDAOImpl.findById(oneParentMenu.getParentId());
			}
			if(parentsIdList.contains(id)){
				return ResponseResult.failure("You can't set the parent menu to yourself or the sub menu.");
			}
		}
		
		/**3.保存菜单数据**/
		Menu menu=id>0?menuDAOImpl.findById(id):new Menu();
		
		menu.setText(paramMap.get("text"));
		menu.setParentId(Integer.parseInt(paramMap.get("parentId")));
		menu.setParentName(paramMap.get("parentName"));
		menu.setUri(paramMap.get("uri"));
		menu.setIcon(paramMap.get("icon"));
		if(StringUtils.isNotEmpty(paramMap.get("sort"))&&StringUtils.isNumeric(paramMap.get("sort")))menu.setSort(Integer.parseInt(paramMap.get("sort")));
		//如果存在子菜单则不是leaf
		String leaf="";
		if(id>0){
			List<Menu> childMenuList=menuDAOImpl.findByParentId(id);
			leaf=childMenuList!=null&&!childMenuList.isEmpty()?"false":"true";
		}
		menu.setLeaf(leaf);
		
		menuDAOImpl.save(menu);
		
		/**4.保存父菜单数据,主要是设置为不是末端节点。**/
		Menu parentMenu=menuDAOImpl.findById(menu.getParentId());
		if(parentMenu!=null&&"true".equals(parentMenu.getLeaf())){
			parentMenu.setLeaf("false");
			menuDAOImpl.save(parentMenu);
		}
		
		return ResponseResult.success("Save menu success!",menu);
	}

	@Override
	@Transactional
	public ResponseResult deleteMenu(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		ResponseResult responseResult=null;
		
		int id=Integer.parseInt(paramMap.get("id"));
		Menu menu=menuDAOImpl.findById(id);
		if(menu!=null){
			List<Menu> list=menuDAOImpl.findByParentId(id);
			if(list.size()>0){
				responseResult=ResponseResult.failure("This menu contains the sub menu, can not be deleted");
			}else{
				//如果父菜单没有子菜单了，设置为是末端节点
				Menu parentMenu=menuDAOImpl.findById(menu.getParentId());
				List childMenuList=menuDAOImpl.findByParentId(parentMenu.getId());
				if(childMenuList.size()<=1){
					parentMenu.setLeaf("true");
				}
				menuDAOImpl.save(parentMenu);
				
				//删除菜单
				menuDAOImpl.delete(menu);
				
				responseResult=ResponseResult.success("Delete menu success");
			}
		}else{
			responseResult=ResponseResult.failure("Menu does not exist");
		}
		return responseResult;
	}

	@Override
	public ResponseResult getIcons(HttpServletRequest request,Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		
		String rootPath=request.getServletContext().getRealPath("/");
		
		String imagePath="images/icon/";
		
		File file = new File(rootPath+imagePath);
		
		File [] files = file.listFiles();
		
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		for(File imgeFile:files){
			Map<String,String> map=new HashMap<String,String>();
			
			String fileName=imgeFile.getName();
			map.put("url",imagePath+fileName);
			map.put("name",fileName);
			list.add(map);
		 
        }
		
		return ResponseResult.success(list);
	}
		
}
