package service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import service.MenuService;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.MapResult;
import repository.Menu;

@Service
public class MenuServiceImpl implements MenuService {

  @Autowired
  private Menu menu;

  @Override
  public Map<String, Object> getMenuTree(Map<String, String> paramMap) {
    return MapResult.success(getMenuTree(paramMap.get("node")));
  }

  public List<Map<String, Object>> getMenuTree(String parentId) {
    List<Menu> menuList = menu.findByProperty(Menu.class, "parentId", parentId);
    List<Map<String, Object>> returnList = new ObjectMapper().convertValue(menuList,
        new TypeReference<List<Map<String, Object>>>() {
        });
    for (int i = 0; i < menuList.size(); i++) {
      Menu oneMenu = menuList.get(i);
      List<Map<String, Object>> childMenuList = getMenuTree(oneMenu.getId());
      returnList.get(i).put("data", childMenuList);
    }
    return returnList;
  }

  @Override
  @Transactional
  public Map<String, Object> saveMenu(Map<String, String> paramMap) {

    /** 1.同一父菜单，名称不能相同 **/
    Map<String, Object> checkConditions = new HashMap<String, Object>();
    checkConditions.put("text", paramMap.get("text"));
    checkConditions.put("parentId", paramMap.get("parentId"));
    List<Menu> checkMenuList = menu.findByProperties(Menu.class, checkConditions);

    if (checkMenuList != null && !checkMenuList.isEmpty()
        && !checkMenuList.get(0).getId().equals(paramMap.get("id"))) {
      return new WeakHashMap<String, Object>() {
        {
          put("success", false);
          put("message",
              "The same parent menu has a menu name, please re - enter the name of the menu!");
        }
      };
    }

    /** 2.修改时检查不能将菜单的父菜单设置成自己或自己的子菜单 **/
    if (StringUtils.isNotEmpty(paramMap.get("id"))) {
      List<String> parentsIdList = new ArrayList<String>();
      Menu oneParentMenu = menu.findById(Menu.class, paramMap.get("parentId"));
      while (oneParentMenu != null) {
        parentsIdList.add(oneParentMenu.getId());
        oneParentMenu = menu.findById(Menu.class, oneParentMenu.getParentId());
      }
      if (parentsIdList.contains(paramMap.get("id"))) {
        return new WeakHashMap<String, Object>() {
          {
            put("success", false);
            put("message", "You can't set the parent menu to yourself or the sub menu.");
          }
        };
      }
    }

    /** 3.保存菜单数据 **/
    menu = StringUtils.isNotEmpty(paramMap.get("id")) ? menu.findById(Menu.class,
        paramMap.get("id")) : new Menu();

    menu.setText(paramMap.get("text"));
    menu.setParentId(paramMap.get("parentId"));
    menu.setParentName(paramMap.get("parentName"));
    menu.setUri(paramMap.get("uri"));
    menu.setIcon(paramMap.get("icon"));
    if (StringUtils.isNotEmpty(paramMap.get("sort")) && StringUtils.isNumeric(paramMap.get("sort")))
      menu.setSort(Integer.parseInt(paramMap.get("sort")));
    // 如果存在子菜单则不是leaf
    String leaf = "";
    if (StringUtils.isNotEmpty(paramMap.get("id"))) {
      List<Menu> childMenuList = menu.findByProperty(Menu.class, "parentId", paramMap.get("id"));
      leaf = childMenuList != null && !childMenuList.isEmpty() ? "false" : "true";
    }
    menu.setLeaf(leaf);

    menu.save(menu);

    /** 4.保存父菜单数据,主要是设置为不是末端节点。 **/
    Menu parentMenu = menu.findById(Menu.class, menu.getParentId());
    if (parentMenu != null && "true".equals(parentMenu.getLeaf())) {
      parentMenu.setLeaf("false");
      menu.save(parentMenu);
    }

    return new WeakHashMap<String, Object>() {
      {
        put("success", true);
        put("message", "Save menu success!");
      }
    };
  }

  @Override
  @Transactional
  public Map<String, Object> deleteMenu(Map<String, String> paramMap) {
    Map<String, Object> responseResult = null;

    String id = paramMap.get("id");
    menu = menu.findById(Menu.class, id);
    if (menu != null) {
      List<Menu> list = menu.findByProperty(Menu.class, "parentId", id);
      if (list.size() > 0) {
        responseResult = new WeakHashMap<String, Object>() {
          {
            put("success", false);
            put("message", "This menu contains the sub menu, can not be deleted");
          }
        };
      } else {
        // 如果父菜单没有子菜单了，设置为是末端节点
        Menu parentMenu = menu.findById(Menu.class, menu.getParentId());
        List<Menu> childMenuList = menu.findByProperty(Menu.class, "parentId", parentMenu.getId());
        if (childMenuList.size() <= 1) {
          parentMenu.setLeaf("true");
        }
        menu.save(parentMenu);

        // 删除菜单
        menu.remove(menu);

        responseResult = new WeakHashMap<String, Object>() {
          {
            put("success", true);
            put("message", "Delete menu success");
          }
        };
      }
    } else {
      responseResult = new WeakHashMap<String, Object>() {
        {
          put("success", false);
          put("message", "Menu does not exist");
        }
      };
    }
    return responseResult;
  }

  @Override
  public Map<String, Object> getIcons(HttpServletRequest request, Map<String, String> paramMap) {
    String rootPath = request.getServletContext().getRealPath("/");
    String imagePath = "images/icon/";
    File file = new File(rootPath + imagePath);
    File[] files = file.listFiles();
    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
    for (File imgeFile : files) {
      Map<String, String> map = new HashMap<String, String>();
      String fileName = imgeFile.getName();
      map.put("url", imagePath + fileName);
      map.put("name", fileName);
      list.add(map);

    }
    return new WeakHashMap<String, Object>() {
      {
        put("success", true);
        put("data", "list");
      }
    };
  }

}
