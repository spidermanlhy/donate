package com.donate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.donate.dto.reqdto.NewsReqDTO;
import com.donate.dto.respdto.NewsRespDTO;
import com.donate.entity.AppUserEntity;
import com.donate.entity.NewsCategoryEntity;
import com.donate.entity.NewsEntity;
import com.donate.mapper.NewsCategoryMapper;
import com.donate.service.INewsCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsCategoryServiceImpl implements INewsCategoryService {

  @Autowired
  NewsCategoryMapper newsCategoryMapper;

  @Override
  public Map<String, Object> getNewsCategoryList(NewsReqDTO reqDTO) {
    Map<String,Object> result=new HashMap<>();
    QueryWrapper<NewsCategoryEntity> queryWrapper=new QueryWrapper<>();

    List<NewsCategoryEntity> list=new ArrayList<>();
    if(reqDTO.getPage()!=null&&reqDTO.getPage()>0){
      IPage page=new Page(reqDTO.getPage(), reqDTO.getSize());
      list=newsCategoryMapper.selectPage(page,queryWrapper).getRecords();
    }else{
      list=newsCategoryMapper.selectList(queryWrapper);
    }

    Map<String,Object> dataMap=new HashMap<>();
    dataMap.put("list",list);
    dataMap.put("current",reqDTO.getPage());
    dataMap.put("size",reqDTO.getSize());
    dataMap.put("total",newsCategoryMapper.selectCount(queryWrapper));
    result.put("data",dataMap);
    result.put("code","ACK");
    result.put("message","查询成功");
    result.put("nonBizError",false);
    return result;
  }

  @Override
  public Map<String, Object> addNewsCategoty(NewsCategoryEntity newsCategoryEntity) {
    Map<String,Object> result=new HashMap<>();
    newsCategoryMapper.insert(newsCategoryEntity);
    result.put("code","ACK");
    result.put("message","添加成功");
    result.put("nonBizError",false);
    return result;
  }

  @Override
  public Map<String, Object> updateNewsCategory(NewsCategoryEntity newsCategoryEntity) {
    Map<String,Object> result=new HashMap<>();
    newsCategoryMapper.updateById(newsCategoryEntity);
    result.put("code","ACK");
    result.put("message","修改成功");
    result.put("nonBizError",false);
    return result;
  }

  @Override
  public Map<String, Object> delNewCategory(int id) {
    Map<String,Object> result=new HashMap<>();
    newsCategoryMapper.deleteById(id);
    result.put("code","ACK");
    result.put("message","删除成功");
    result.put("nonBizError",false);
    return result;
  }
}
