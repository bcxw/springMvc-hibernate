package serviceImpl;

import java.util.List;
import java.util.Map;

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
		
		//同一父菜单，名称不能相同
		Menu checkMenu=new Menu();
		checkMenu.setText(paramMap.get("text"));
		checkMenu.setParentId(Integer.parseInt(paramMap.get("parentId")));
		
		List<Menu> checkMenuList=menuDAOImpl.findByExample(checkMenu);
		
		if(checkMenuList==null||checkMenuList.isEmpty()||checkMenuList.get(0).getId()==id){
			
			//保存菜单数据
			Menu menu=id>0?menuDAOImpl.findById(id):new Menu();
			
			menu.setText(paramMap.get("text"));
			menu.setParentId(Integer.parseInt(paramMap.get("parentId")));
			menu.setParentName(paramMap.get("parentName"));
			menu.setUri(paramMap.get("uri"));
			menu.setIcon(paramMap.get("icon"));
			if(StringUtils.isNotEmpty(paramMap.get("sort"))&&StringUtils.isNumeric(paramMap.get("sort")))menu.setSort(Integer.parseInt(paramMap.get("sort")));
			menu.setLeaf(paramMap.get("leaf"));
			
			menuDAOImpl.save(menu);
			
			//保存父菜单数据,主要是设置为不是末端节点。
			Menu parentMenu=menuDAOImpl.findById(menu.getParentId());
			if(parentMenu!=null&&"true".equals(parentMenu.getLeaf())){
				parentMenu.setLeaf("false");
				menuDAOImpl.save(parentMenu);
			}
			
			responseResult=ResponseResult.success("Save menu success!",menu);
		}else{
			responseResult=ResponseResult.failure("The same parent menu has a menu name, please re - enter the name of the menu!");
		}
	
		return responseResult;
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
		
}
