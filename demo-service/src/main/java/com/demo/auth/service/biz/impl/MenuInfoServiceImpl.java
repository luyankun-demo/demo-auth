package com.demo.auth.service.biz.impl;

import com.demo.auth.bean.domain.MenuInfo;
import com.demo.auth.bean.model.MenuGradeInfo;
import com.demo.auth.dao.mapper.MenuInfoMapper;
import com.demo.auth.service.biz.MenuInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MenuInfoServiceImpl implements MenuInfoService {

    @Autowired
    private MenuInfoMapper menuInfoMapper;

    /**
     * 查询全部菜单集合(带层级关系)
     * @return
     */
    @Override
    public List<MenuGradeInfo> getMenuInfoAllList() {
        // 获取所有菜单数据
        List<MenuGradeInfo> list = menuInfoMapper.getMenuInfoAllList();
        // 获取最小的父级id
        int parentId = list.stream().map(MenuGradeInfo::getParentId).min(Integer::compareTo).get();
        return getList(list, parentId, null);
    }

    /**
     * 处理菜单树形结构
     * @param list
     * @param parentId
     * @param parent
     * @return
     */
    private List<MenuGradeInfo> getList(List<MenuGradeInfo> list, int parentId, MenuGradeInfo parent){
        List<MenuGradeInfo> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            MenuGradeInfo menuGradeInfo = list.get(i);
            log.info("当前父级ID：{}，当前菜单信息：{}", parentId, menuGradeInfo);
            if (menuGradeInfo.getParentId() == parentId) {
                newList.add(menuGradeInfo);
                list.remove(menuGradeInfo);
                --i;
                getList(list, menuGradeInfo.getId(), menuGradeInfo);
                if (parent != null && newList.size() > 0) {
                    newList = newList.stream().sorted(Comparator.comparing(MenuInfo::getSort)).collect(Collectors.toList());
                    parent.setChildren(newList);
                }
            }
        }
        return newList;
    }

    @Override
    public void addMenuInfo(MenuInfo menuInfo) {
        menuInfoMapper.insert(menuInfo);
    }

    @Override
    public void editMenuInfo(MenuInfo menuInfo) {
        menuInfoMapper.update(menuInfo);
    }

    @Override
    public MenuInfo getMenuInfo(Integer id) {
        return menuInfoMapper.getById(id);
    }
}
