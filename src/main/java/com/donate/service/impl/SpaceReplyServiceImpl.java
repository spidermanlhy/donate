package com.donate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.donate.dto.reqdto.SpaceReplyReqDTO;
import com.donate.dto.respdto.SpaceReplyRespDTO;
import com.donate.dto.respdto.SpaceRespDTO;
import com.donate.entity.AppUserEntity;
import com.donate.entity.SpaceEntity;
import com.donate.entity.SpaceReplyEntity;
import com.donate.mapper.AppUserMapper;
import com.donate.mapper.SpaceMapper;
import com.donate.mapper.SpaceReplyMapper;
import com.donate.service.ISpaceReplyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Service
public class SpaceReplyServiceImpl implements ISpaceReplyService {
  @Autowired
  SpaceReplyMapper spaceReplyMapper;

  @Autowired
  SpaceMapper spaceMapper;

  @Autowired
  AppUserMapper appUserMapper;

  @Override
  public Map<String, Object> getSpaceReplyList(SpaceReplyReqDTO reqDTO,Integer userId) {
    Map<String,Object> result=new HashMap<>();
    QueryWrapper<SpaceReplyEntity> queryWrapper=new QueryWrapper<>();

    queryWrapper.eq("space_id",reqDTO.getSpaceId());

    List<SpaceReplyEntity> list=new ArrayList<>();
    if(reqDTO.getPage()!=null&&reqDTO.getPage()>0){
      IPage page=new Page(reqDTO.getPage(), reqDTO.getSize());
      list=spaceReplyMapper.selectPage(page,queryWrapper).getRecords();
    }else{
      list=spaceReplyMapper.selectList(queryWrapper);
    }

    List<SpaceReplyRespDTO> data=new ArrayList<>();
    list.forEach(item->{
      SpaceReplyRespDTO spaceRespDTO=new SpaceReplyRespDTO();
      BeanUtils.copyProperties(item,spaceRespDTO);
      if(userId==item.getAddUserId()||userId==spaceMapper.selectById(item.getSpaceId()).getAddUserId()){
        spaceRespDTO.setCanEdit(true);
      }else{
        spaceRespDTO.setCanEdit(false);
      }
      if(item.getReplayId()!=null&&item.getReplayId()>0){
        spaceRespDTO.setReplyUserName("回复："+spaceReplyMapper.selectById(item.getReplayId()).getAddUserName());
      }else{
        spaceRespDTO.setReplyUserName("");
      }
      data.add(spaceRespDTO);
    });

    Map<String,Object> dataMap=new HashMap<>();
    dataMap.put("list",data);
    dataMap.put("current",reqDTO.getPage());
    dataMap.put("size",reqDTO.getSize());
    dataMap.put("total",spaceReplyMapper.selectCount(queryWrapper));
    result.put("data",dataMap);
    result.put("code","ACK");
    result.put("message","查询成功");
    result.put("nonBizError",false);
    return result;
  }

  @Override
  public Map<String, Object> addSpaceReply(SpaceReplyEntity spaceEntity) {
    Map<String,Object> result=new HashMap<>();
    AppUserEntity appUserEntity=appUserMapper.selectById(spaceEntity.getAddUserId());
    spaceEntity.setAddUserName(appUserEntity.getUsername());
    spaceEntity.setCreateDate(new Date());
    spaceReplyMapper.insert(spaceEntity);
    result.put("code","ACK");
    result.put("message","添加成功");
    result.put("nonBizError",false);
    return result;
  }

  @Override
  public Map<String, Object> delSpaceReply(int id) {
    Map<String,Object> result=new HashMap<>();
    spaceReplyMapper.deleteById(id);
    QueryWrapper<SpaceReplyEntity> queryWrapper=new QueryWrapper<>();
    queryWrapper.eq("replay_id",id);
    spaceReplyMapper.delete(queryWrapper);
    result.put("code","ACK");
    result.put("message","删除成功");
    result.put("nonBizError",false);
    return result;
  }
}
