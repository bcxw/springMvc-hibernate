package service.impl;

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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.ResultUtil;
import repository.Menu;

@Service
public class MenuServiceImpl implements MenuService {

  @Autowired
  private Menu menu;

  @Override
  public Map<String, Object> getMenuTree(Map<String, String> paramMap) {
    return ResultUtil.success(getMenuTree(paramMap.get("node")));
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
      return ResultUtil.failure("同一父菜单下以存在此名称，请重新输入菜单名称");
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
        return ResultUtil.failure("不能把自己或者子菜单设置成自己的父菜单");
      }
    }

    /** 3.保存菜单数据 **/
    menu = StringUtils.isNotEmpty(paramMap.get("id")) ? menu.findById(Menu.class,
        paramMap.get("id")) : new Menu();

    menu.setText(paramMap.get("text"));
    menu.setParentId(paramMap.get("parentId"));
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
    return ResultUtil.success(menu, "保存菜单成功");
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
        responseResult = ResultUtil.failure("此菜单包含子菜单，不能删除");
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

        responseResult = ResultUtil.success("删除菜单成功");
      }
    } else {
      responseResult = ResultUtil.failure("菜单不存在");
    }
    return responseResult;
  }

}
