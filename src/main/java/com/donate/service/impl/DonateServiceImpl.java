package com.donate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.donate.dto.reqdto.RecodeReqDTO;
import com.donate.dto.respdto.BulletinRespDTO;
import com.donate.entity.DtAppealEntity;
import com.donate.entity.DtBulletinEntity;
import com.donate.entity.DtRecordEntity;
import com.donate.mapper.AppUserMapper;
import com.donate.mapper.DtAppealMapper;
import com.donate.mapper.DtApplyMapper;
import com.donate.mapper.DtRecordMapper;
import com.donate.service.IDonateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Service
public class DonateServiceImpl implements IDonateService {
  @Autowired
  DtRecordMapper dtRecordMapper;

  @Autowired
  AppUserMapper appUserMapper;

  @Autowired
  DtApplyMapper dtApplyMapper;

  @Autowired
  DtAppealMapper dtAppealMapper;

  /**
   * 获取捐献记录
   *
   * @param reqDTO
   * @return
   */
  @Override
  public Map<String, Object> getRecordList(RecodeReqDTO reqDTO) {
    Map<String,Object> result=new HashMap<>();
    QueryWrapper<DtRecordEntity> queryWrapper=new QueryWrapper<>();
    queryWrapper.eq("is_deleted",0);
    queryWrapper.orderByDesc("gmt_created");
    queryWrapper.isNull("pay_order");
    List<DtRecordEntity> list=new ArrayList<>();
    if(reqDTO.getPage()!=null&&reqDTO.getPage()>0){
      IPage page=new Page(reqDTO.getPage(), reqDTO.getSize());
      list=dtRecordMapper.selectPage(page,queryWrapper).getRecords();
    }else{
      list=dtRecordMapper.selectList(queryWrapper);
    }

    List<DtRecordEntity> data=new ArrayList<>();
    list.forEach(item->{
      DtRecordEntity dtRecordEntity=new DtRecordEntity();
      BeanUtils.copyProperties(item,dtRecordEntity);
      data.add(dtRecordEntity);
    });
    Map<String,Object> dataMap=new HashMap<>();
    dataMap.put("list",data);
    dataMap.put("current",reqDTO.getPage());
    dataMap.put("size",reqDTO.getSize());
    dataMap.put("total",dtRecordMapper.selectCount(queryWrapper));
    result.put("data",dataMap);
    result.put("code","ACK");
    result.put("message","查询成功");
    result.put("nonBizError",false);
    return result;
  }

  @Override
  public Map<String, Object> donate(DtRecordEntity recordEntity) {
    Map<String,Object> result=new HashMap<>();
    recordEntity.setGmtCreated(new Date());
    recordEntity.setGmtModified(new Date());
    recordEntity.setCreatedUserName(appUserMapper.selectById(recordEntity.getCreatedUserId()).getUsername());
    recordEntity.setTitle(dtApplyMapper.selectById(recordEntity.getReceiverId()).getTitle());
    recordEntity.setIsDeleted(0);
    dtRecordMapper.insert(recordEntity);
    QueryWrapper<DtAppealEntity> appealEntityQueryWrapper=new QueryWrapper<>();
    appealEntityQueryWrapper.eq("apply_id",recordEntity.getReceiverId());
    DtAppealEntity dtAppealEntity=dtAppealMapper.selectOne(appealEntityQueryWrapper);
    dtAppealEntity.setAppealCount(dtAppealEntity.getAppealCount()+1);
    dtAppealEntity.setCompleted(dtAppealEntity.getCompleted()+ recordEntity.getMoney());
    dtAppealMapper.updateById(dtAppealEntity);
    result.put("code","ACK");
    result.put("message","捐赠成功");
    result.put("nonBizError",false);
    return result;
  }
}
