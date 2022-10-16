package com.donate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.donate.dto.reqdto.BulletinReqDTO;
import com.donate.dto.respdto.ApplyDetailRespDTO;
import com.donate.dto.respdto.BulletinRespDTO;
import com.donate.entity.DtAppealEntity;
import com.donate.entity.DtApplyEntity;
import com.donate.entity.DtBulletinEntity;
import com.donate.mapper.DtBulletinMapper;
import com.donate.service.IBulletinService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Service
public class BulletinServiceImpl implements IBulletinService {

  @Autowired
  DtBulletinMapper dtBulletinMapper;
  /**
   * 获取通知公告列表
   *
   * @param reqDTO
   * @return
   */
  @Override
  public Map<String, Object> getBulletInList(BulletinReqDTO reqDTO) {
    Map<String,Object> result=new HashMap<>();
    QueryWrapper<DtBulletinEntity> queryWrapper=new QueryWrapper<>();
    queryWrapper.eq("is_deleted",0);
    if(!ObjectUtils.isEmpty(reqDTO.getIsShow())){
      queryWrapper.eq("is_show",reqDTO.getIsShow());
    }
    if(!ObjectUtils.isEmpty(reqDTO.getSearch())){
      queryWrapper.like("title",reqDTO.getSearch());
    }
    List<DtBulletinEntity> list=new ArrayList<>();
    if(reqDTO.getPage()!=null&&reqDTO.getPage()>0){
      IPage page=new Page(reqDTO.getPage(), reqDTO.getSize());
      list=dtBulletinMapper.selectPage(page,queryWrapper).getRecords();
    }else{
      list=dtBulletinMapper.selectList(queryWrapper);
    }

    List<BulletinRespDTO> data=new ArrayList<>();
    list.forEach(item->{
      BulletinRespDTO bulletinRespDTO=new BulletinRespDTO();
      BeanUtils.copyProperties(item,bulletinRespDTO);
      data.add(bulletinRespDTO);
    });
    Map<String,Object> dataMap=new HashMap<>();
    dataMap.put("list",data);
    dataMap.put("current",reqDTO.getPage());
    dataMap.put("size",reqDTO.getSize());
    dataMap.put("total",dtBulletinMapper.selectCount(queryWrapper));
    result.put("data",dataMap);
    result.put("code","ACK");
    result.put("message","查询成功");
    result.put("nonBizError",false);
    return result;
  }

  @Override
  public Map<String, Object> addBulletInList(DtBulletinEntity bulletinEntity) {
    bulletinEntity.setIsDeleted(0);
    bulletinEntity.setGmtCreated(new Date());
    bulletinEntity.setGmtModified(new Date());
    dtBulletinMapper.insert(bulletinEntity);
    Map<String,Object> result=new HashMap<>();
    result.put("code","ACK");
    result.put("message","添加成功");
    result.put("nonBizError",false);
    return result;
  }

  @Override
  public Map<String, Object> delBulletIn(int id) {
    Map<String,Object> result=new HashMap<>();
    dtBulletinMapper.deleteById(id);
    result.put("code","ACK");
    result.put("message","删除成功");
    result.put("nonBizError",false);
    return result;
  }

  @Override
  public Map<String, Object> changeBulletIn(int id, int status) {
    Map<String,Object> result=new HashMap<>();
    DtBulletinEntity dtBulletinEntity=dtBulletinMapper.selectById(id);
    dtBulletinEntity.setIsShow(status);
    dtBulletinMapper.updateById(dtBulletinEntity);
    result.put("code","ACK");
    result.put("message","修改成功");
    result.put("nonBizError",false);
    return result;
  }
}
