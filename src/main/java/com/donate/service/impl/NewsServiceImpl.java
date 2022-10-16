package com.donate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.donate.dto.reqdto.NewsReqDTO;
import com.donate.dto.respdto.ApplyDetailRespDTO;
import com.donate.dto.respdto.NewsRespDTO;
import com.donate.entity.*;
import com.donate.mapper.AppUserMapper;
import com.donate.mapper.NewsCategoryMapper;
import com.donate.mapper.NewsMapper;
import com.donate.service.INewsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Service
public class NewsServiceImpl implements INewsService {

  @Autowired
  NewsMapper newsMapper;

  @Autowired
  NewsCategoryMapper newsCategoryMapper;

  @Autowired
  AppUserMapper appUserMapper;

  @Override
  public Map<String, Object> getNewsList(NewsReqDTO reqDTO) {

    Map<String,Object> result=new HashMap<>();
    QueryWrapper<NewsEntity> queryWrapper=new QueryWrapper<>();
    if(!ObjectUtils.isEmpty(reqDTO.getTitle())){
      queryWrapper.like("title",reqDTO.getTitle());
    }

    if(!ObjectUtils.isEmpty(reqDTO.getType())&&reqDTO.getType()>0){
      queryWrapper.eq("type",reqDTO.getType());
    }

    List<NewsEntity> list=new ArrayList<>();
    if(reqDTO.getPage()!=null&&reqDTO.getPage()>0){
      IPage page=new Page(reqDTO.getPage(), reqDTO.getSize());
      list=newsMapper.selectPage(page,queryWrapper).getRecords();
    }else{
      list=newsMapper.selectList(queryWrapper);
    }

    List<NewsRespDTO> data=new ArrayList<>();
    list.forEach(item->{
      NewsRespDTO newsRespDTO=new NewsRespDTO();
      BeanUtils.copyProperties(item,newsRespDTO);
      NewsCategoryEntity newsCategoryEntity=newsCategoryMapper.selectById(item.getType());
      if(newsCategoryEntity!=null){
        newsRespDTO.setTypeName(newsCategoryEntity.getName());
      }else{
        newsRespDTO.setTypeName("其他新闻");
      }
      data.add(newsRespDTO);
    });

    Map<String,Object> dataMap=new HashMap<>();
    dataMap.put("list",data);
    dataMap.put("current",reqDTO.getPage());
    dataMap.put("size",reqDTO.getSize());
    dataMap.put("total",newsMapper.selectCount(queryWrapper));
    result.put("data",dataMap);
    result.put("code","ACK");
    result.put("message","查询成功");
    result.put("nonBizError",false);
    return result;
  }

  @Override
  public Map<String, Object> addNews(NewsEntity newsEntity) {
    Map<String,Object> result=new HashMap<>();
    AppUserEntity appUserEntity=appUserMapper.selectById(newsEntity.getAddUserId());
    newsEntity.setAddUserName(appUserEntity.getUsername());
    newsEntity.setCreateDate(new Date());
    newsMapper.insert(newsEntity);
    result.put("code","ACK");
    result.put("message","添加成功");
    result.put("nonBizError",false);
    return result;
  }

  @Override
  public Map<String, Object> delNews(int id) {
    Map<String,Object> result=new HashMap<>();
    newsMapper.deleteById(id);
    result.put("code","ACK");
    result.put("message","删除成功");
    result.put("nonBizError",false);
    return result;
  }
}
