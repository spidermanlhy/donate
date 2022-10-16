package com.donate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.donate.dto.reqdto.SpaceReqDTO;
import com.donate.dto.respdto.ApplyDetailRespDTO;
import com.donate.dto.respdto.NewsRespDTO;
import com.donate.dto.respdto.SpaceRespDTO;
import com.donate.entity.*;
import com.donate.mapper.AppUserMapper;
import com.donate.mapper.SpaceMapper;
import com.donate.service.ISpaceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Service
public class SpaceServiceImpl implements ISpaceService {

  @Autowired
  SpaceMapper spaceMapper;

  @Autowired
  AppUserMapper appUserMapper;

  @Override
  public Map<String, Object> getSpaceList(SpaceReqDTO reqDTO,Integer userId) {
    Map<String,Object> result=new HashMap<>();
    QueryWrapper<SpaceEntity> queryWrapper=new QueryWrapper<>();
    if(!ObjectUtils.isEmpty(reqDTO.getTitle())){
      queryWrapper.like("title",reqDTO.getTitle());
    }

    List<SpaceEntity> list=new ArrayList<>();
    if(reqDTO.getPage()!=null&&reqDTO.getPage()>0){
      IPage page=new Page(reqDTO.getPage(), reqDTO.getSize());
      list=spaceMapper.selectPage(page,queryWrapper).getRecords();
    }else{
      list=spaceMapper.selectList(queryWrapper);
    }

    List<SpaceRespDTO> data=new ArrayList<>();
    list.forEach(item->{
      SpaceRespDTO spaceRespDTO=new SpaceRespDTO();
      BeanUtils.copyProperties(item,spaceRespDTO);
      if(userId==item.getAddUserId()){
        spaceRespDTO.setCanEdit(true);
      }else{
        spaceRespDTO.setCanEdit(false);
      }
      data.add(spaceRespDTO);
    });

    Map<String,Object> dataMap=new HashMap<>();
    dataMap.put("list",data);
    dataMap.put("current",reqDTO.getPage());
    dataMap.put("size",reqDTO.getSize());
    dataMap.put("total",spaceMapper.selectCount(queryWrapper));
    result.put("data",dataMap);
    result.put("code","ACK");
    result.put("message","查询成功");
    result.put("nonBizError",false);
    return result;
  }

  @Override
  public Map<String, Object> addSpace(SpaceEntity spaceEntity) {
    Map<String,Object> result=new HashMap<>();
    AppUserEntity appUserEntity=appUserMapper.selectById(spaceEntity.getAddUserId());
    spaceEntity.setAddUserName(appUserEntity.getUsername());
    spaceEntity.setCreateDate(new Date());
    spaceMapper.insert(spaceEntity);
    result.put("code","ACK");
    result.put("message","添加成功");
    result.put("nonBizError",false);
    return result;
  }

  @Override
  public Map<String, Object> delSpace(int id) {
    Map<String,Object> result=new HashMap<>();
    spaceMapper.deleteById(id);
    result.put("code","ACK");
    result.put("message","删除成功");
    result.put("nonBizError",false);
    return result;
  }
}
