package com.donate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.donate.dto.reqdto.ChildCreateReqDTO;
import com.donate.dto.reqdto.ChildReqDTO;
import com.donate.dto.respdto.ApplyDetailRespDTO;
import com.donate.dto.respdto.ChildRespDTO;
import com.donate.entity.ChildListEntity;
import com.donate.entity.DtAppealEntity;
import com.donate.entity.DtApplyEntity;
import com.donate.mapper.ChildListMapper;
import com.donate.mapper.DtApplyMapper;
import com.donate.service.IChildService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ChildServiceImpl implements IChildService {

  @Autowired
  ChildListMapper childListMapper;

  @Autowired
  DtApplyMapper dtApplyMapper;

  SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");

  /**
   * 查询儿童列表
   *
   * @param reqDTO
   * @return
   */
  @Override
  public Map<String, Object> getChildList(ChildReqDTO reqDTO,Integer userId) {
    Map<String,Object> result=new HashMap<>();
    QueryWrapper<ChildListEntity> queryWrapper=new QueryWrapper<>();
    if(!ObjectUtils.isEmpty(reqDTO.getUserId())){
      queryWrapper.eq("user_id",reqDTO.getUserId());
    }

    if(!ObjectUtils.isEmpty(reqDTO.getChildName())){
      queryWrapper.like("child_name",reqDTO.getChildName());
    }

    if(!ObjectUtils.isEmpty(reqDTO.getChildCode())){
      queryWrapper.like("child_code",reqDTO.getChildCode());
    }

    if(!ObjectUtils.isEmpty(reqDTO.getStatus())&&reqDTO.getStatus()>=0){
      queryWrapper.eq("status",reqDTO.getStatus());
    }
    if(reqDTO.getCity()!=null&&reqDTO.getCity().size()>0){
      queryWrapper.eq("city",String.join("-",reqDTO.getCity()));
    }

    queryWrapper.eq("del_flag",0);
    List<ChildListEntity> list=new ArrayList<>();
    if(reqDTO.getPage()!=null&&reqDTO.getPage()>0){
      IPage page=new Page(reqDTO.getPage(), reqDTO.getSize());
      list=childListMapper.selectPage(page,queryWrapper).getRecords();
    }else{
      list=childListMapper.selectList(queryWrapper);
    }

    List<ChildRespDTO> data=new ArrayList<>();
    list.forEach(item->{
      ChildRespDTO childRespDTO=new ChildRespDTO();
      BeanUtils.copyProperties(item,childRespDTO);

      if(item.getStatus()==0){
        childRespDTO.setStatusName("审核中");
      }else if(item.getStatus()==1){
        childRespDTO.setStatusName("通过");
      }else if(item.getStatus()==2){
        childRespDTO.setStatusName("未通过");
      }

      QueryWrapper<DtApplyEntity> queryWrapper1=new QueryWrapper<>();
      queryWrapper1.eq("child_id",item.getId());
      queryWrapper1.eq("is_deleted",0);
      DtApplyEntity applyEntity=dtApplyMapper.selectOne(queryWrapper1);
      if(applyEntity!=null){
        childRespDTO.setApplyId(applyEntity.getId());
      }else{
        childRespDTO.setApplyId(0);
      }
      data.add(childRespDTO);
    });

    Map<String,Object> dataMap=new HashMap<>();
    dataMap.put("list",data);
    dataMap.put("current",reqDTO.getPage());
    dataMap.put("size",reqDTO.getSize());
    dataMap.put("total",childListMapper.selectCount(queryWrapper));
    dataMap.put("login",userId!=0);
    result.put("data",dataMap);
    result.put("code","ACK");
    result.put("message","查询成功");
    result.put("nonBizError",false);
    return result;
  }

  /**
   * 创建儿童档案
   *
   * @param reqDTO
   * @return
   */
  @Override
  public Map<String, Object> createChild(ChildCreateReqDTO reqDTO) {
    Map<String,Object> result=new HashMap<>();
    ChildListEntity childListEntity=new ChildListEntity();
    BeanUtils.copyProperties(reqDTO,childListEntity);
    childListEntity.setDelFlag(0);
    childListEntity.setCity(String.join("-",reqDTO.getCity()));
    childListEntity.setCityText(String.join("-", reqDTO.getCityText()));
    childListEntity.setChildCode(format.format(new Date()));
    childListMapper.insert(childListEntity);
    result.put("code","ACK");
    result.put("message","添加成功");
    result.put("nonBizError",false);
    return result;
  }

  /**
   * 根据ID获取儿童信息
   *
   * @param id
   * @return
   */
  @Override
  public Map<String, Object> getChildById(int id) {
    Map<String,Object> result=new HashMap<>();
    ChildListEntity childListEntity=childListMapper.selectById(id);
    ChildCreateReqDTO childCreateReqDTO=new ChildCreateReqDTO();
    BeanUtils.copyProperties(childListEntity,childCreateReqDTO);
    String[] city=childListEntity.getCity().split("-");
    List<String> cityList=new ArrayList<>();
    for(int i=0;i<city.length;i++){
      cityList.add(city[i]);
    }
    childCreateReqDTO.setCity(cityList);

    String[] cityText=childListEntity.getCityText().split("-");
    List<String> cityTextList=new ArrayList<>();
    for(int i=0;i<cityText.length;i++){
      cityTextList.add(cityText[i]);
    }
    childCreateReqDTO.setCityText(cityTextList);
    result.put("code","ACK");
    result.put("data",childCreateReqDTO);
    return result;
  }

  @Override
  public Map<String, Object> updateChild(ChildCreateReqDTO reqDTO) {
    Map<String,Object> result=new HashMap<>();
    ChildListEntity childListEntity=new ChildListEntity();
    BeanUtils.copyProperties(reqDTO,childListEntity);
    childListEntity.setDelFlag(0);
    childListEntity.setCity(String.join("-",reqDTO.getCity()));
    childListEntity.setCityText(String.join("-", reqDTO.getCityText()));
    childListEntity.setStatus(0);
    childListMapper.updateById(childListEntity);
    result.put("code","ACK");
    result.put("message","修改成功");
    result.put("nonBizError",false);
    return result;
  }

  @Override
  public Map<String, Object> delChild(int id) {
    Map<String,Object> result=new HashMap<>();
    childListMapper.deleteById(id);

    QueryWrapper<DtApplyEntity> queryWrapper=new QueryWrapper<>();
    queryWrapper.eq("child_id",id);
    List<DtApplyEntity> list=dtApplyMapper.selectList(queryWrapper);
    list.forEach(item->{
      item.setIsDeleted(1);
      dtApplyMapper.updateById(item);
    });
    result.put("code","ACK");
    result.put("message","删除成功");
    result.put("nonBizError",false);
    return result;
  }

  @Override
  public Map<String, Object> approve(int id, int status) {
    Map<String,Object> result=new HashMap<>();
    ChildListEntity childListEntity=childListMapper.selectById(id);
    childListEntity.setStatus(status);
    childListMapper.updateById(childListEntity);


    result.put("code","ACK");
    result.put("message","审批成功");
    result.put("nonBizError",false);
    return result;
  }
}
